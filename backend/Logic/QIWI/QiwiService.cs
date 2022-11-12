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

    public async Task<string> SentSms(string phoneNum, string requestId, string accountId)
    {
        var client = _qiwiClient.Get();
        var requestBody = JsonConvert.SerializeObject(
            new QIWISentSmsDto
            {
                accountId = accountId,
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

    public async Task<string> Pay(QiwiBillrequestDto dto)
    {
        var client = _qiwiClient.Get();
        var dtoForQiwi = new PayRequestQiwiDto
        {
            amount = new BillData
            {
                currency = dto.amount.currency,
                value = dto.amount.value
            },
            customer = new BillCustomer
            {
                account = dto.customer.account
            },
            paymentMethod = new TokenMethod
            {
                paymentToken = dto.paymentMethod.paymentToken,
                type = dto.paymentMethod.type
            }
        };
        var requestBody = JsonConvert.SerializeObject(dtoForQiwi);
        StringContent httpContent = new StringContent(requestBody, System.Text.Encoding.UTF8, "application/json");
        string url = "payin/v1/sites/sa3khn-09/payments/" + dto.RequestId;
        HttpResponseMessage response = client.PutAsync(url,
            httpContent).Result;
        
        
        return await response.Content.ReadAsStringAsync();
    }
}