using Logic.Auth.DTO;
using Microsoft.AspNetCore.Mvc;

namespace Logic.Auth;

[ApiController]
[Route("[controller]")]
public class AuthController : ControllerBase
{
    private readonly LoginQueryHandler _loginQueryHandler;

    public AuthController(LoginQueryHandler loginQueryHandler)
    {   
        _loginQueryHandler = loginQueryHandler;
    }

    [HttpPost("login")]
    public IActionResult Login([FromHeader]string phoneNumber)
    {
        var result = _loginQueryHandler.Execute(phoneNumber);
        return Ok(result);
    }
    
}