using Infrastructure.CQRS;
using Logic.Customer.DTO;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using AppContext = Context.AppContext;

namespace Logic.Customer.Handlers;

public class RegisterCustomerCommand: ICommand<RegisterCustomerDto>
{
    
    
    private readonly UserManager<Context.Models.User> _userManager;

    public RegisterCustomerCommand(UserManager<Context.Models.User> userManager)
    {
        _userManager = userManager;
    }

    public async Task Handle(RegisterCustomerDto dto)
    {
        
        var newUser = new Context.Models.User
        {
            PhoneNumber = dto.Phone,
            UserName = dto.Name,
            AccountId = dto.AccountId,
            Email = "qweqw"
        };
        
        var hashedPass =_userManager.PasswordHasher.HashPassword(newUser, dto.Password);
        
        var x =_userManager.CreateAsync(newUser, hashedPass).Result;
    }
}