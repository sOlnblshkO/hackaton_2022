using Infrastructure.CQRS;
using Logic.Auth.DTO;
using Logic.QIWI;
using Newtonsoft.Json;

namespace Logic.Auth;

public class SmsCheckQueryHandler : IQuery<SmsDto, UserTokensDto>
{
    private readonly IQiwiService _qiwiService;

    public SmsCheckQueryHandler(IQiwiService qiwiService)
    {
        _qiwiService = qiwiService;
    }

    public async Task<UserTokensDto> Execute(SmsDto query)
    {
        var result = await _qiwiService.CheckSms(query);
        return JsonConvert.DeserializeObject<UserTokensDto>(result);
    }
}