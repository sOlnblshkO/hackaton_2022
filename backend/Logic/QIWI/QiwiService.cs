using System.Net.Http.Headers;
using Logic.QIWI.GetClient;
using Logic.Sms.DTO;
using Newtonsoft.Json;

namespace Logic.QIWI;

public class QiwiService : IQiwiService
{
    private readonly IGetQiwiClient _qiwiClient;
    
    const string MoqedSiteId  = "sa3khn-09";

    public QiwiService(IGetQiwiClient qiwiClient)
    {
        _qiwiClient = qiwiClient;
    }

    public async Task<string> SentSms(string phoneNum, string requestId)
    {
        var client = _qiwiClient.Get();
        var requestBody = JsonConvert.SerializeObject(
            new QIWISentSmsDto
            {
                accountId = "qwe12",
                phone = phoneNum,
                requestId = requestId
            });
        StringContent httpContent = new StringContent(requestBody, System.Text.Encoding.UTF8, "application/json");
        HttpResponseMessage response = client.PostAsync("payin-tokenization-api/v1/sites/sa3khn-09/token-requests", 
            httpContent).Result;
        
        
        return await response.Content.ReadAsStringAsync();
    }

    public async Task<string> CheckSms(SmsDto dto)
    {
        var client = _qiwiClient.Get();
        var requestBody = JsonConvert.SerializeObject(dto);
        StringContent httpContent = new StringContent(requestBody, System.Text.Encoding.UTF8, "application/json");
        HttpResponseMessage response = client.PostAsync("payin-tokenization-api/v1/sites/sa3khn-09/token-requests/complete", 
            httpContent).Result;
        
        
        return await response.Content.ReadAsStringAsync();
    }

    public async Task<string> CreateBill(QiwiBillrequestDto dto)
    {
        var client = _qiwiClient.Get();
        var requestBody = JsonConvert.SerializeObject(dto);
        StringContent httpContent = new StringContent(requestBody, System.Text.Encoding.UTF8, "application/json");
        HttpResponseMessage response = client.PutAsync("payin/v1/sites/sa3khn-09/bills/123w123",
            httpContent).Result;
        
        
        return await response.Content.ReadAsStringAsync();
    }
}