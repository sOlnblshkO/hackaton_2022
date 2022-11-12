using System.Net.Http.Headers;

namespace Logic.QIWI.GetClient;

public class GetQiwiClient : IGetQiwiClient
{
    public HttpClient Get()
    {
        const string QiwiUrl  = "https://api.qiwi.com/partner/";
        const string MoqedAccountId  = "04361f47-e4ff-41c7-bd37-dd473f472187";
        
        HttpClient client = new HttpClient();
        client.BaseAddress = new Uri(QiwiUrl);
        client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));
        client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", MoqedAccountId);

        return client;
    }
}