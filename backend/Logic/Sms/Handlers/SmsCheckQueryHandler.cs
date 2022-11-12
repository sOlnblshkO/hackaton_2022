using Infrastructure.CQRS;
using Logic.QIWI;
using Logic.Sms.DTO;
using Newtonsoft.Json;

namespace Logic.Sms.Handlers;

public class SmsCheckQueryHandler : IQuery<SmsDto, CheckSmsResponseDto>
{
    private readonly IQiwiService _qiwiService;

    public SmsCheckQueryHandler(IQiwiService qiwiService)
    {
        _qiwiService = qiwiService;
    }

    public async Task<CheckSmsResponseDto> Execute(SmsDto query)
    {
        var result = await _qiwiService.CheckSms(query);
        return JsonConvert.DeserializeObject<CheckSmsResponseDto>(result) ?? throw new InvalidOperationException();
    }
}