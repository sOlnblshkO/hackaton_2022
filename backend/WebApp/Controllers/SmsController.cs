using Logic.Sms.DTO;
using Logic.Sms.Handlers;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]
public class SmsController : ControllerBase
{
    private readonly LoginQueryHandler _loginQueryHandler;
    private readonly SmsCheckQueryHandler _smsCheckQueryHandler;

    public SmsController(LoginQueryHandler loginQueryHandler, SmsCheckQueryHandler smsCheckQueryHandler)
    {
        _loginQueryHandler = loginQueryHandler;
        _smsCheckQueryHandler = smsCheckQueryHandler;
    }

    [HttpPost("login")]
    public IActionResult Login([FromBody]GetCodeForPhoneDto request)
    {
        var result = _loginQueryHandler.Execute(request);
        return Ok(result);
    }
    
    [HttpPost("checkSms")]
    public IActionResult CheckSms([FromBody]SmsDto request)
    {
        var result = _smsCheckQueryHandler.Execute(request);
        return Ok(result);
    }
    
}