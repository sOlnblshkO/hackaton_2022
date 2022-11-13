using Context.Enums;
using Context.Models;
using Infrastructure.CQRS;
using Logic.Payment.DTO;
using Logic.QIWI;
using Logic.Services.HashService;
using Logic.Sms.DTO;
using AppContext = Context.AppContext;

namespace Logic.Payment;

public class PayCommandQueryHandler: IQuery<QrCheckQuery, bool>
{
    private readonly AppContext _appContext;
    private readonly IQiwiService _qiwiService;
    private readonly IHashService _hashService;

    public PayCommandQueryHandler(AppContext appContext, IQiwiService qiwiService, IHashService hashService)
    {
        _appContext = appContext;
        _qiwiService = qiwiService;
        _hashService = hashService;
    }

    public async Task<bool> Execute(QrCheckQuery query)
    {
        var paymentId = (long)Convert.ToDouble(_hashService.Convert(query.PaymentId));
        var payment = _appContext.Set<Context.Models.Payment>()
            .FirstOrDefault(x => x.Id == paymentId);
        
        var userName =  query.ClaimsPrincipal.Identity.Name;
        var user =  _appContext.Set<User>().FirstOrDefault(x => x.UserName == userName);
        var seller = _appContext.Set<Context.Models.Seller>()
            .FirstOrDefault(x => x.User == user);
        if (payment == null || payment.Seller != seller)
        {
            // TODO fix pos nre 
            payment.PaymentState = PaymentState.Canceled;
            _appContext.Update(payment);
            await _appContext.SaveChangesAsync();
            return false;
        }
        var result = await _qiwiService.Pay(new QiwiBillrequestDto
        {
            amount = new BillData
            {
                currency = payment.Currency,
                value = payment.Amount.ToString()
            },
            customer = new BillCustomer
            {
                account = payment.User.Id
            },
            paymentMethod = new TokenMethod
            {
                paymentToken = _hashService.Convert(payment.PaymentToken)
            },

        });
        if (result == "true") // TODO Полная несусветная дичь, надо исправить!
        {
            payment.PaymentState = PaymentState.Finished;
            _appContext.Update(payment);
            await _appContext.SaveChangesAsync();
            return true;
        }
        else
        {
            payment.PaymentState = PaymentState.Canceled;
            _appContext.Update(payment);
            await _appContext.SaveChangesAsync();
            return false;
        }
    }
}