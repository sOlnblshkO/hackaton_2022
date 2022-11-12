using Logic.Sms.DTO;

namespace Logic.QIWI;

public interface IQiwiService
{
    public Task<string> SentSms(string phoneNum, string requestId);

    public Task<string> CheckSms(SmsDto dto);

    public string CreateBill();
}