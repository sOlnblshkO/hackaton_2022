using Domain.DTO.Seller;
using Infrastructure.CQRS;

namespace Logic.Seller.Handlers;

public class GetSellersListQueryHandler : IQuery<string, List<GetSellerListDtoResponse>>
{
    private static List<GetSellerListDtoResponse> _responses = new List<GetSellerListDtoResponse>()
    {
        new GetSellerListDtoResponse()
        {
            Id = 1,
            Name = "МВидео",
            Category = "Техника",
            AvatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Mvideo.svg/1280px-Mvideo.svg.png"
        },
        new GetSellerListDtoResponse()
        {
            Id = 2,
            Name = "Золотое Яблоко",
            Category = "Косметика",
            AvatarUrl =
                "data:image/png;base64,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"
        },
        new GetSellerListDtoResponse()
        {
            Id = 1,
            Name = "Ламода",
            Category = "Интернет магазин",
            AvatarUrl =
                "data:image/jpeg;base64,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"
        }
    };

    public async Task<List<GetSellerListDtoResponse>> Execute(string? query)
    {
        await Task.CompletedTask;
        if (query is null)
        {
            return _responses;
        }

        var filteredNames = _responses.Select(x => x.Name.ToLower()).Where(x => x.Contains(query.ToLower()));
        return _responses.Where(x => filteredNames.Contains(x.Name.ToLower())).ToList();
    }
}