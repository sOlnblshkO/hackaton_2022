using Domain.DTO.Customer;
using Logic.Customer.Handlers;
using Microsoft.AspNetCore.Authorization;
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
    [AllowAnonymous]
    public IActionResult RegisterUser([FromBody] RegisterCustomerDto dto)
    {
        return Ok(_registerCustomerCommand.Execute(dto));
    }
}