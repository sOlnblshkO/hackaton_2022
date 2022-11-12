using Logic.Sms.DTO;
using Logic.Sms.Handlers;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]
public class SmsController : ControllerBase
{
    private readonly GetSmsQueryHandler _getSmsQueryHandler;
    private readonly SmsCheckQueryHandler _smsCheckQueryHandler;

    public SmsController(GetSmsQueryHandler getSmsQueryHandler, SmsCheckQueryHandler smsCheckQueryHandler)
    {
        _getSmsQueryHandler = getSmsQueryHandler;
        _smsCheckQueryHandler = smsCheckQueryHandler;
    }

    [HttpPost("GetSms")]
    public IActionResult GetSms([FromBody]GetCodeForPhoneDto request)
    {
        var result = _getSmsQueryHandler.Execute(request);
        return Ok(result);
    }
    
    [HttpPost("checkSms")]
    public IActionResult CheckSms([FromBody]SmsDto request)
    {
        var result = _smsCheckQueryHandler.Execute(request);
        return Ok(result);
    }
    
}