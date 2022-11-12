using Infrastructure.CQRS;
using Logic.QIWI;

namespace Logic.Auth;

public class LoginQueryHandler : IQuery<string, string>
{
    private readonly IQiwiService _qiwiService;

    public LoginQueryHandler(IQiwiService qiwiService)
    {
        _qiwiService = qiwiService;
    }

    public async Task<string> Execute(string query)
    {
       
        return  await _qiwiService.SentSms(query);
    }
}