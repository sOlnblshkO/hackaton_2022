using backend.AppStarts;
using backend.Hubs;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddService();

var app = builder.Build();
app.ConfigureWebApp();
app.UseAuthentication();
app.UseAuthorization();
app.MapGet("/", () => "Hello World!");
app.UseEndpoints(x =>
{
    x.MapHub<PaymentHub>("/hubs/endPayment");
});

app.Run();