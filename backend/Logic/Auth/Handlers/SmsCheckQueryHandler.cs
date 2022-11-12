using Infrastructure.CQRS;
using Logic.Auth.DTO;

namespace Logic.Auth;

public class SmsCheckQueryHandler : IQuery<SmsDto, UserTokensDto>
{
    public Task<UserTokensDto> Execute(SmsDto query)
    {
       
        throw new NotImplementedException();
    }
}