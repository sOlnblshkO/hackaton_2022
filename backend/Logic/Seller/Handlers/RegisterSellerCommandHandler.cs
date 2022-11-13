using Context.Constants;
using Context.Models;
using Domain.DTO.Seller;
using Infrastructure.CQRS;
using Microsoft.AspNetCore.Identity;
using AppContext = Context.AppContext;

namespace Logic.Seller.Handlers;

public class RegisterSellerCommandHandler: ICommand<RegisterSellerDTO>
{
    private readonly UserManager<Context.Models.User> _userManager;
    private readonly AppContext _dbContext;
    private readonly RoleManager<Role> _roleManager;


    public RegisterSellerCommandHandler(UserManager<Context.Models.User> userManager, AppContext dbContext, RoleManager<Role> roleManager)
    {
        _userManager = userManager;
        _dbContext = dbContext;
        _roleManager = roleManager;
    }

    public async Task Handle(RegisterSellerDTO dto)
    {
        var newUser = new Context.Models.User
        {
            PhoneNumber = dto.Phone,
            Name = dto.Name,
            UserName = dto.Name,
            Surname = "",
            Role = _roleManager.Roles.FirstOrDefault(x=>x.Name == UserRoleConstants.SellerRoleName)
        };
        
        var hashedPass =_userManager.PasswordHasher.HashPassword(newUser, dto.Password);
        var res = _userManager.CreateAsync(newUser, hashedPass).Result;

        if (res.Succeeded)
        {
            var seller = new Context.Models.Seller()
            {
                AvatarUrl = dto.AvatarUrl,
                LegalName = dto.LegalName,
                Name = dto.Name,
                SiteId = "sa3khn-09",
                User = newUser,
                Description = dto.Description,
                Address = dto.Address,
                Caregory = dto.Category
            };

            _dbContext.Sellers.Add(seller);
            await _dbContext.SaveChangesAsync();
        }
    }
}