using Logic.Sms.DTO;

namespace Logic.QIWI;

public interface IQiwiService
{
    public Task<string> SentSms(string phoneNum, string requestId, string accountId);

    public Task<string> CheckSms(SmsDto dto);

    public Task<string> Pay(QiwiBillrequestDto query);
}