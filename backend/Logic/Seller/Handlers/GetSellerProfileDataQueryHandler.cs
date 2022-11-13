using Domain.DTO.Seller;
using Infrastructure.CQRS;
using Microsoft.EntityFrameworkCore;

namespace Logic.Seller.Handlers;

public class GetSellerProfileDataQueryHandler: IQuery<string, SellerProfileDataResponseDto>
{
    private readonly Context.AppContext _dbContext;

    public GetSellerProfileDataQueryHandler(Context.AppContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<SellerProfileDataResponseDto> Execute(string customerName)
    {
        var seller = await _dbContext.Sellers.FirstOrDefaultAsync(x=>x.Name == customerName);
        return new SellerProfileDataResponseDto()
        {
            Id = seller.Id,
            Address = seller.Address,
            AvatarUrl = seller.AvatarUrl,
            Category = seller.Caregory,
            Description = seller.Caregory,
            LegalName = seller.LegalName,
            Name = seller.Name,
            Phone = seller.User.PhoneNumber
        };
    }
}