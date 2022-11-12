using Context;
using Context.Models;
using Logic;
using Logic.Customer.Handlers;
using Logic.QIWI;
using Logic.QIWI.GetClient;
using Microsoft.AspNetCore.Identity;
using AppContext = Context.AppContext;

namespace backend.AppStarts;

public static class ServicesExtension
{
    public static void AddService(this IServiceCollection serviceCollection)
    {
        serviceCollection.AddContext();
        serviceCollection.AddIdentity<User, Role>(options =>
            {
                options.User.RequireUniqueEmail = false;
                options.Password.RequiredLength = 6;
            })
            .AddEntityFrameworkStores<AppContext>()
            .AddDefaultTokenProviders();
        
        serviceCollection.AddLogic();
        serviceCollection.AddControllers();
        serviceCollection.AddHttpClient();
        serviceCollection.AddAuthentication();
        serviceCollection.AddAuthorization();
        serviceCollection.AddScoped<IQiwiService, QiwiService>();
        serviceCollection.AddScoped<IGetQiwiClient, GetQiwiClient>();
    }
}