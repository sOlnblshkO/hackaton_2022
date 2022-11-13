using Context.Enums;
using Context.Models;
using Infrastructure.CQRS;
using Logic.Payment.DTO;
using Logic.QIWI;
using Logic.Services.HashService;
using Logic.Sms.DTO;
using Microsoft.EntityFrameworkCore;
using AppContext = Context.AppContext;

namespace Logic.Payment;

public class CheckSmsQueryHandler : IQuery<CheckSmsQuery, CheckSmsResponse>
{
    private readonly AppContext _appContext;
    private readonly IQiwiService _qiwiService;
    private readonly IHashService _hashService;

    public CheckSmsQueryHandler(AppContext appContext, IQiwiService qiwiService, IHashService hashService)
    {
        _appContext = appContext;
        _qiwiService = qiwiService;
        _hashService = hashService;
    }

    public async Task<CheckSmsResponse> Execute(CheckSmsQuery query)
    {
        var userName =  query.ClaimsPrincipal.Identity.Name;
        var user =  _appContext.Set<User>().FirstOrDefault(x => x.UserName == userName);
        
      
        var seller = await _appContext.Set<Context.Models.Seller>().FirstOrDefaultAsync(x => x.Id == (long)Convert.ToDouble(query.SellerId));

        var existPayment = _appContext.Set<Context.Models.Payment>()
            .FirstOrDefault(x => (x.User == user && x.Seller == seller && x.PaymentState == PaymentState.New));

        var token = await _qiwiService.CheckSms(new SmsDto { requestId = existPayment.RequestId, smsCode = query.SmsCode });

        var hashedToken = _hashService.Hash(token);

        existPayment.PaymentToken = hashedToken;
        existPayment.PaymentState = PaymentState.Active;

        _appContext.Update(existPayment);
        await _appContext.SaveChangesAsync();
        return new CheckSmsResponse
        {
            PaymentId = _hashService.Hash(existPayment.Id.ToString()),
            Amount = existPayment.Amount.ToString(),
            Currency = existPayment.Currency,
            ExpDate = existPayment.ExpirationDateTime.ToString(),
            SellerLegalName = seller.LegalName
        };

    }
}