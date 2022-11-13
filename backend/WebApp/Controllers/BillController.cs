using backend.Hubs;
using Logic.Sms.DTO;
using Logic.Sms.Handlers;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]
public class BillController : ControllerBase
{
    private readonly PayQueryHandler _payQueryHandler;
    private readonly IHubContext<PaymentHub> _paymentHub;

    public BillController(PayQueryHandler payQueryHandler, IHubContext<PaymentHub> paymentHub)
    {
        _payQueryHandler = payQueryHandler;
        _paymentHub = paymentHub;
    }
    [Authorize]
    [HttpPost("GetBill")]
    public async Task<IActionResult> GetBill([FromBody] QiwiBillrequestDto dto)
    {
        var result = await _payQueryHandler.Execute(dto);
        await _paymentHub.Clients.All.SendAsync($"{dto.RequestId}", "finish");
        return Ok(result);
    }
}