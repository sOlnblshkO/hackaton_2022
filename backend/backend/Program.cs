using backend.AppStarts;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddService();

var app = builder.Build();

app.MapControllers();
app.UseRouting();
app.MapGet("/", () => "Hello World!");

app.Run();