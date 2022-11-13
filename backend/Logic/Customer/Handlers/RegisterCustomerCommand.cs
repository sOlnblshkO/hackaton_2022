using Context.Constants;
using Context.Models;
using Domain.DTO.Customer;
using Infrastructure.CQRS;
using Microsoft.AspNetCore.Identity;

namespace Logic.Customer.Handlers;

public class RegisterCustomerCommand: IQuery<RegisterCustomerDto, IdentityResult>
{
    
    
    private readonly UserManager<Context.Models.User> _userManager;
    private readonly RoleManager<Role> _roleManager;

    public RegisterCustomerCommand(UserManager<Context.Models.User> userManager, RoleManager<Role> roleManager)
    {
        _userManager = userManager;
        _roleManager = roleManager;
    }

    public Task<IdentityResult> Execute(RegisterCustomerDto dto)
    {
        
        var newUser = new Context.Models.User
        {
            PhoneNumber = dto.Phone,
            Name = dto.Name,
            Surname = dto.Surname,
            UserName = dto.Surname + " " + dto.Name,
            Role = _roleManager.Roles.FirstOrDefault(x=>x.Name == UserRoleConstants.CustomerRoleName)
        };
        var res = _userManager.CreateAsync(newUser, dto.Password).Result;
        return Task.FromResult(res);
    }
}