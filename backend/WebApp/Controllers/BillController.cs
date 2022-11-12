using Logic.Sms.DTO;
using Logic.Sms.Handlers;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]
public class BillController : ControllerBase
{
    private readonly GetBillQueryHandler _getBillQueryHandler;

    public BillController(GetBillQueryHandler getBillQueryHandler)
    {
        _getBillQueryHandler = getBillQueryHandler;
    }

    [HttpPost("GetBill")]
    public IActionResult GetBill([FromBody] QiwiBillrequestDto dto)
    {
        return Ok(_getBillQueryHandler.Execute(dto));
    }
}