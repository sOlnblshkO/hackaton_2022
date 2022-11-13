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
                "https://goldapple.ru/static/version1668028003/global/images/ga_black_logo.png"
        },
        new GetSellerListDtoResponse()
        {
            Id = 3,
            Name = "Ламода",
            Category = "Интернет магазин",
            AvatarUrl =
                "https://papik.pro/uploads/posts/2022-01/1643600978_2-papik-pro-p-lamoda-logotip-2.jpg"
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