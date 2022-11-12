using Infrastructure.CQRS;
using Logic.Customer.DTO;
using Microsoft.AspNetCore.Identity;

namespace Logic.Customer.Handlers;

public class RegisterCustomerCommand: ICommand<RegisterCustomerDto>
{
    
    
    private readonly UserManager<Context.Models.Customer> _userManager;

    public RegisterCustomerCommand(UserManager<Context.Models.Customer> userManager)
    {
        _userManager = userManager;
    }

    public Task Handle(RegisterCustomerDto dto)
    {
        
        var newUser = new Context.Models.Customer
        {
            PhoneNumber = dto.Phone,
            UserName = dto.Name,
            AccountId = dto.AccountId,
            Email = "test",
        };
        var hashedPass =_userManager.PasswordHasher.HashPassword(newUser, dto.Password);
        return _userManager.CreateAsync(newUser, hashedPass);
    }
}