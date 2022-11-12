using Infrastructure.CQRS;
using Logic.QIWI;
using Logic.Sms.DTO;
using Newtonsoft.Json;

namespace Logic.Sms.Handlers;

public class GetSmsQueryHandler : IQuery<GetCodeForPhoneDto, GetSmsResponseDto>
{
    private readonly IQiwiService _qiwiService;

    public GetSmsQueryHandler(IQiwiService qiwiService)
    {
        _qiwiService = qiwiService;
    }

    public async Task<GetSmsResponseDto> Execute(GetCodeForPhoneDto request)
    {
       
        return JsonConvert.DeserializeObject<GetSmsResponseDto>(await _qiwiService.SentSms(request.PhoneNumber, request.RequestId) 
                                                                ?? throw new InvalidOperationException()) ?? throw new InvalidOperationException();
    }
}