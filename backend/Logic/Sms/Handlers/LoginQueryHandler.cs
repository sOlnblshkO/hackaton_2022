using Infrastructure.CQRS;
using Logic.QIWI;
using Logic.Sms.DTO;

namespace Logic.Sms.Handlers;

public class LoginQueryHandler : IQuery<GetCodeForPhoneDto, string>
{
    private readonly IQiwiService _qiwiService;

    public LoginQueryHandler(IQiwiService qiwiService)
    {
        _qiwiService = qiwiService;
    }

    public async Task<string> Execute(GetCodeForPhoneDto request)
    {
       
        return  await _qiwiService.SentSms(request.PhoneNumber, request.RequestId);
    }
}