using Infrastructure.CQRS;
using Logic.Customer.DTO;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using AppContext = Context.AppContext;

namespace Logic.Customer.Handlers;

public class RegisterCustomerCommand: IQuery<RegisterCustomerDto, bool>
{
    
    
    private readonly UserManager<Context.Models.User> _userManager;

    public RegisterCustomerCommand(UserManager<Context.Models.User> userManager)
    {
        _userManager = userManager;
    }

    public Task<bool> Execute(RegisterCustomerDto dto)
    {
        
        var newUser = new Context.Models.User
        {
            PhoneNumber = dto.Phone,
            UserName = dto.Name,
            AccountId = dto.AccountId,
        };
        
        var hashedPass =_userManager.PasswordHasher.HashPassword(newUser, dto.Password);
        var x = _userManager.CreateAsync(newUser, hashedPass).Result.Succeeded;
        return Task.FromResult(x);
    }
}