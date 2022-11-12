namespace Logic.QIWI;

public interface IQiwiService
{
    public Task<string> SentSms(string phoneNum);
}