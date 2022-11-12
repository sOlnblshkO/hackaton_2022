using Domain.DTO.Seller;
using Infrastructure.CQRS;
using Microsoft.AspNetCore.Identity;
using AppContext = Context.AppContext;

namespace Logic.Seller.Handlers;

public class RegisterSellerCommandHandler: ICommand<RegisterSellerDTO>
{
    private readonly UserManager<Context.Models.User> _userManager;
    private readonly AppContext _dbContext;

    public RegisterSellerCommandHandler(UserManager<Context.Models.User> userManager, AppContext dbContext)
    {
        _userManager = userManager;
        _dbContext = dbContext;
    }

    public async Task Handle(RegisterSellerDTO dto)
    {
        var newUser = new Context.Models.User
        {
            PhoneNumber = dto.Phone,
            Name = dto.Name,
            UserName = dto.Phone,
            Surname = ""
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