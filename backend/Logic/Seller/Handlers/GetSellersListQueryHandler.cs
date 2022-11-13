using Domain.DTO.Seller;
using Infrastructure.CQRS;
using Microsoft.EntityFrameworkCore;
using AppContext = Context.AppContext;

namespace Logic.Seller.Handlers;

public class GetSellersListQueryHandler : IQuery<string, List<GetSellerListDtoResponse>>
{
    private readonly AppContext _dbContext;

    public GetSellersListQueryHandler(AppContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<List<GetSellerListDtoResponse>> Execute(string? query)
    {
        if (query is null)
        {
            return await _dbContext.Sellers.Select(x => new GetSellerListDtoResponse()
            {
                Id = x.Id,
                Name = x.Name,
                Category = x.Caregory,
                AvatarUrl = x.AvatarUrl
            }).ToListAsync();
        }

        var filteredNames = _dbContext.Sellers
            .Select(x => x.Name.ToLower())
            .Where(x => x.Contains(query.ToLower()));
        
        return await _dbContext.Sellers
            .Where(x => filteredNames.Contains(x.Name.ToLower()))
            .Select(x =>
                new GetSellerListDtoResponse()
                {
                    Id = x.Id,
                    Name = x.Name,
                    Category = x.Caregory,
                    AvatarUrl = x.AvatarUrl
                })
            .ToListAsync();
    }
}