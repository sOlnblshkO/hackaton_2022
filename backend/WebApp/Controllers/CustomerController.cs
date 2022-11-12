using Logic.Customer.DTO;
using Logic.Customer.Handlers;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("[controller]")]

public class CustomerController : ControllerBase
{
    private readonly RegisterCustomerCommand _registerCustomerCommand;

    public CustomerController(RegisterCustomerCommand registerCustomerCommand)
    {
        _registerCustomerCommand = registerCustomerCommand;
    }

    [HttpPost("Register")]
    public IActionResult RegisterUser([FromBody] RegisterCustomerDto dto)
    {
        _registerCustomerCommand.Handle(dto);
        return Ok();
    }
}