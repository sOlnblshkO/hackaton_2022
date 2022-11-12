using Logic.Auth.DTO;
using Microsoft.AspNetCore.Mvc;

namespace Logic.Auth;

[ApiController]
[Route("[controller]")]
public class AuthController : ControllerBase
{
    private readonly LoginQueryHandler _loginQueryHandler;
    private readonly SmsCheckQueryHandler _smsCheckQueryHandler;

    public AuthController(LoginQueryHandler loginQueryHandler, SmsCheckQueryHandler smsCheckQueryHandler)
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