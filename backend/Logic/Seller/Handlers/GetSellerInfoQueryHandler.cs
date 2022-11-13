using Domain.DTO.Seller;
using Infrastructure.CQRS;
using Microsoft.EntityFrameworkCore;
using AppContext = Context.AppContext;

namespace Logic.Seller.Handlers;

public class GetSellerInfoQueryHandler: IQuery<int, GetSellerInfoDtoResponse>
{
    private readonly AppContext _dbContext;
    
    public GetSellerInfoQueryHandler(AppContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<GetSellerInfoDtoResponse> Execute(int id)
    {
        var seller =await _dbContext.Sellers.FirstOrDefaultAsync(x => x.Id == id);

        if (seller is null)
        {
            return null;
        }

        return new GetSellerInfoDtoResponse()
        {
            Address = seller.Address,
            AvatarUrl = seller.AvatarUrl,
            Category = seller.Caregory,
            Description = seller.Description,
            Id = seller.Id,
            Name = seller.Name,
            LegalName = seller.LegalName
        };
    }
}