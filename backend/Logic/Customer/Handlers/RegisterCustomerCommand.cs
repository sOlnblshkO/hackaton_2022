using Infrastructure.CQRS;
using Logic.Customer.DTO;
using Microsoft.AspNetCore.Identity;

namespace Logic.Customer.Handlers;

public class RegisterCustomerCommand: IQuery<RegisterCustomerDto, IdentityResult>
{
    
    
    private readonly UserManager<Context.Models.User> _userManager;

    public RegisterCustomerCommand(UserManager<Context.Models.User> userManager)
    {
        _userManager = userManager;
    }

    public Task<IdentityResult> Execute(RegisterCustomerDto dto)
    {
        
        var newUser = new Context.Models.User
        {
            PhoneNumber = dto.Phone,
            Name = dto.Name,
            Surname = dto.Surname,
            UserName = dto.Surname + " " + dto.Name,
        };
        
        var hashedPass =_userManager.PasswordHasher.HashPassword(newUser, dto.Password);
        var res = _userManager.CreateAsync(newUser, hashedPass).Result;
        return Task.FromResult(res);
    }
}