using System.Security.Claims;
using Logic.Customer.DTO;
using Logic.Customer.Handlers;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]
public class CustomerController : ControllerBase
{
    private readonly RegisterCustomerCommand _registerCustomerCommand;
    private readonly GetCustomerProfileDataQueryHandler _getCustomerProfileDataQueryHandler;

    public CustomerController(RegisterCustomerCommand registerCustomerCommand,
        GetCustomerProfileDataQueryHandler getCustomerProfileDataQueryHandler)
    {
        _registerCustomerCommand = registerCustomerCommand;
        _getCustomerProfileDataQueryHandler = getCustomerProfileDataQueryHandler;
    }

    [HttpPost("Register")]
    [AllowAnonymous]
    public IActionResult RegisterUser([FromBody] RegisterCustomerDto dto)
    {
        return Ok(_registerCustomerCommand.Execute(dto));
    }

    [HttpGet]
    [Authorize]
    public IActionResult GetCustomerProfileData()
    {
        _getCustomerProfileDataQueryHandler.Execute(HttpContext.User.FindFirst(x => x.Type == ClaimTypes.Name).Value);
        return Ok();
    }
}