using Context.Enums;
using Context.Models;
using Infrastructure.CQRS;
using Logic.Payment.DTO;
using Logic.QIWI;
using Microsoft.EntityFrameworkCore;
using AppContext = Context.AppContext;

namespace Logic.Payment;

public class GetSmsCommandHandler : ICommand<GetBillCommand>
{
    private readonly AppContext _appContext;
    private readonly IQiwiService _qiwiService;

    public GetSmsCommandHandler(AppContext appContext, IQiwiService qiwiService)
    {
        _appContext = appContext;
        _qiwiService = qiwiService;
    }

    public async Task Handle(GetBillCommand command)
    {
        var userName =  command.ClaimsPrincipal.Identity.Name;
        var user =  _appContext.Set<User>().FirstOrDefault(x => x.UserName == userName);
        
        var requestId = Guid.NewGuid().ToString();
        
        await _qiwiService.SentSms(user.PhoneNumber, requestId, user.Id);
        var seller = await _appContext.Set<Context.Models.Seller>().FirstOrDefaultAsync(x => x.Id == (long)Convert.ToDouble(command.SellerId));

        var existPayment = _appContext.Set<Context.Models.Payment>()
            .FirstOrDefault(x => (x.User == user && x.Seller == seller && x.PaymentState == PaymentState.New));
        
        if (existPayment != null)
        {
            _appContext.Remove(existPayment);
            await _appContext.SaveChangesAsync(); 
            throw new ArgumentException();
        }
        
        var payment = new Context.Models.Payment
        {
            Amount =  Math.Round(Convert.ToDouble(command.Amount), 2, MidpointRounding.ToZero),
            ExpirationDateTime =  DateTime.Now.AddDays(1),
            PaymentState = PaymentState.New,
            RequestId = requestId,
            User = user,
            Seller = seller,
            Currency = command.Currency
        };
        _appContext.Add(payment);
        await _appContext.SaveChangesAsync();
    }
}