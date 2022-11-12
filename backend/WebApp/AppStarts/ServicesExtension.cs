using Context;
using Logic;
using Logic.QIWI;
using Logic.QIWI.GetClient;
using Microsoft.AspNetCore.Identity;

namespace backend.AppStarts;

public static class ServicesExtension
{
    public static void AddService(this IServiceCollection serviceCollection)
    {
        serviceCollection.AddLogic();
        serviceCollection.AddContext();
        serviceCollection.AddControllers();
        serviceCollection.AddHttpClient();
        serviceCollection.AddScoped<IQiwiService, QiwiService>();
        serviceCollection.AddScoped<IGetQiwiClient, GetQiwiClient>();
    }
}