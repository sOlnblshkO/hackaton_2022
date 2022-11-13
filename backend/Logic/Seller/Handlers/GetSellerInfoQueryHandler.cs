using Domain.DTO.Seller;
using Infrastructure.CQRS;

namespace Logic.Seller.Handlers;

public class GetSellerInfoQueryHandler: IQuery<int, GetSellerInfoDtoResponse>
{
    private static List<GetSellerInfoDtoResponse> _responses = new List<GetSellerInfoDtoResponse>()
    {
        new ()
        {
            Id = 1,
            Name = "МВидео",
            Category = "Техника",
            AvatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Mvideo.svg/1280px-Mvideo.svg.png",
            LegalName = "ООО МВидео",
            Description = "Гипермаркет электроники",
            Address = "г. Казань пр-т Ибрагимова д. 45"
        },
        new ()
        {
            Id = 2,
            Name = "Золотое Яблоко",
            Category = "Косметика",
            AvatarUrl =
                "https://goldapple.ru/static/version1668028003/global/images/ga_black_logo.png",
            LegalName = "ООО Золотое Яблоко",
            Description = "Магазин с самым большим выбором уходовых средств",
            Address = "г. Казань ул. Пушкина д.2"
        },
        new ()
        {
            Id = 3,
            Name = "Ламода",
            Category = "Интернет магазин",
            AvatarUrl =
                "https://papik.pro/uploads/posts/2022-01/1643600978_2-papik-pro-p-lamoda-logotip-2.jpg",
            LegalName = "ООО Ламода",
            Description = "Самый лучший интернет магазин для ваших покупок",
            Address = "г. Казань ул. Петербургская д. 50"
        }
    };
    

    public async Task<GetSellerInfoDtoResponse?> Execute(int id)
    {
        await Task.CompletedTask;

        return _responses.FirstOrDefault(x => x.Id == id);
    }
}