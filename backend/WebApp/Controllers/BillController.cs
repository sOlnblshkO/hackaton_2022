using Logic.Sms.DTO;
using Logic.Sms.Handlers;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]
public class BillController : ControllerBase
{
    private readonly PayQueryHandler _payQueryHandler;

    public BillController(PayQueryHandler payQueryHandler)
    {
        _payQueryHandler = payQueryHandler;
    }

    [HttpPost("GetBill")]
    public IActionResult GetBill([FromBody] QiwiBillrequestDto dto)
    {
        return Ok(_payQueryHandler.Execute(dto));
    }
}