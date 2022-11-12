using Logic.Payment;
using Logic.Payment.DTO;
using Logic.Sms.DTO;
using Logic.Sms.Handlers;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]
public class BillController : ControllerBase
{
    private readonly PayQueryHandler _payQueryHandler;
    private readonly GetBillForUserCommandHandler _billForUserCommandHandler;

    public BillController(PayQueryHandler payQueryHandler, GetBillForUserCommandHandler billForUserCommandHandler)
    {
        _payQueryHandler = payQueryHandler;
        _billForUserCommandHandler = billForUserCommandHandler;
    }
    [Authorize]
    [HttpPost("GetBill")]
    public IActionResult GetBill([FromBody] QiwiBillrequestDto dto)
    {
        return Ok(_payQueryHandler.Execute(dto));
    }

    [Authorize]
    public IActionResult GetBillForUser([FromBody] GetBillForUserRequestDto dto)
    {
        _billForUserCommandHandler.Handle(new GetBillCommand
        {
            Amout = dto.Amout,
            ClaimsPrincipal = this.User,
            SellerId = dto.SellerId,
        });
        return Ok();
    }
}