using Context.Models;
using Domain.DTO.Payment;
using Logic.Payment;
using Logic.Payment.DTO;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]
public class PaymentController : ControllerBase
{
    private readonly GetSmsCommandHandler _getSmsCommand;
    private readonly CheckSmsQueryHandler _checkSms;
    private readonly PayCommandQueryHandler _payCommandQuery;

    public PaymentController(GetSmsCommandHandler getSmsCommand, CheckSmsQueryHandler checkSms, PayCommandQueryHandler payCommandQuery)
    {
        _getSmsCommand = getSmsCommand;
        _checkSms = checkSms;
        _payCommandQuery = payCommandQuery;
    }

    [Authorize]
    [HttpPost("SentSms")]
    public async Task<IActionResult> SentSms([FromBody]GetBillForUserRequestDto dto)
    {
        await _getSmsCommand.Handle(new GetBillCommand
            { Amount = dto.Amount, ClaimsPrincipal = User, SellerId = dto.SellerId, Currency = dto.Currency});

        return Ok();
    }

    [Authorize]
    [HttpPost("CheckSms")]
    public async Task<IActionResult> CheckSms([FromBody] CheckSmsDto dto)
    {
        var result = await _checkSms.Execute(new CheckSmsQuery 
            { ClaimsPrincipal = User, SellerId = dto.SellerId, SmsCode = dto.SmsCode });

        return Ok(result);
    }

    [Authorize]
    [HttpPost("Pay")]
    public async Task<IActionResult> CheckQr([FromBody] PayDto dto)
    {
        return Ok(await _payCommandQuery.Execute(new QrCheckQuery
        {
            ClaimsPrincipal = User,
            PaymentId = dto.PaymentId
        }));
    }
}