using System.Net.Http.Headers;
using Newtonsoft.Json;

namespace Logic.QIWI;

public class QiwiService : IQiwiService
{
    const string QiwiUrl  = "https://api.qiwi.com/partner/";
    const string MoqedSiteId  = "sa3khn-09";
    const string MoqedAccountId  = "04361f47-e4ff-41c7-bd37-dd473f472187";

    public async Task<string> SentSms(string phoneNum)
    {
        HttpClient client = new HttpClient();
        client.BaseAddress = new Uri(QiwiUrl);
        client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));
        var requestBody = JsonConvert.SerializeObject(
            new QIWISentSmsDto
            {
                accountId = "qwe12",
                phone = phoneNum,
                requestId = "asd1232q77w1e3212"
            });
        StringContent httpContent = new StringContent(requestBody, System.Text.Encoding.UTF8, "application/json");
        client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", MoqedAccountId);
        HttpResponseMessage response = client.PostAsync("payin-tokenization-api/v1/sites/sa3khn-09/token-requests", 
            httpContent).Result;
        
        
        return await response.Content.ReadAsStringAsync();
    }
}