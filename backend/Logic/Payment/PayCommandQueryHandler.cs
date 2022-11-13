using Context.Enums;
using Infrastructure.CQRS;
using Logic.QIWI;
using Logic.Services.HashService;
using Logic.Sms.DTO;
using AppContext = Context.AppContext;

namespace Logic.Payment;

public class PayCommandQueryHandler: IQuery<string, bool>
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

    public async Task<bool> Execute(string query)
    {
        var paymentId = (long)Convert.ToDouble(_hashService.Convert(query));
        var payment = _appContext.Set<Context.Models.Payment>()
            .FirstOrDefault(x => x.Id == paymentId);
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