
using Microsoft.AspNetCore.SignalR;

namespace backend.Hubs;

public class PaymentHub : Microsoft.AspNetCore.SignalR.Hub
{
    public override Task OnConnectedAsync()
    {
        Clients.All.SendAsync("FINISH");
        return base.OnConnectedAsync();
    }

    public async Task FinishPayment(string requestId)
    {
        await Clients.All.SendAsync("FINISH", requestId);
    }
}