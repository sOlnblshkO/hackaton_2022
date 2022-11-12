using Context.Enums;
using Context.Models;
using Infrastructure.CQRS;
using Logic.Payment.DTO;
using Logic.QIWI;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using AppContext = Context.AppContext;

namespace Logic.Payment;

public class GetBillForUserCommandHandler : ICommand<GetBillCommand>
{
    private readonly AppContext _appContext;
    private readonly UserManager<User> _userManager;
    private readonly IQiwiService _qiwiService;

    public GetBillForUserCommandHandler(AppContext appContext, UserManager<User> userManager, IQiwiService qiwiService)
    {
        _appContext = appContext;
        _userManager = userManager;
        _qiwiService = qiwiService;
    }

    public async Task Handle(GetBillCommand command)
    {
        var user =  await _userManager.GetUserAsync(command.ClaimsPrincipal);
        var requestId = Guid.NewGuid().ToString();
        await _qiwiService.SentSms(user.PhoneNumber, requestId, user.Id);
        var seller = await _appContext.Sellers.FirstOrDefaultAsync(x => x.Id == (long)Convert.ToDouble(command.SellerId));
        var Payment = new Context.Models.Payment
        {
            Amount =  Math.Round(Convert.ToDouble(command.Amout), 2, MidpointRounding.ToZero),
            ExpirationDateTime =  DateTime.Now.AddDays(1),
            PaymentState = PaymentState.Active,
            RequestId = requestId,
            User = user,
            
        };
        await _appContext.SaveChangesAsync();
    }
}