using backend.AppStarts;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddService();

var app = builder.Build();
app.ConfigureWebApp();
app.UseAuthentication();
app.UseAuthorization();
app.MapGet("/", () => "Hello World!");

app.Run();