using Context;
using Context.Models;
using Logic;
using Logic.Customer.Handlers;
using Logic.QIWI;
using Logic.QIWI.GetClient;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore.Internal;
using AppContext = Context.AppContext;

namespace backend.AppStarts;

public static class ServicesExtension
{
    public static void AddService(this IServiceCollection serviceCollection)
    {
        serviceCollection.AddContext();
        serviceCollection.AddIdentityCore<Context.Models.Customer>(options =>
            {
                options.User.RequireUniqueEmail = false;
            })
            .AddEntityFrameworkStores<AppContext>()
            .AddDefaultTokenProviders();;
        serviceCollection.AddIdentityCore<Context.Models.Seller>(options =>
            {
                options.User.RequireUniqueEmail = false;
            })
            .AddEntityFrameworkStores<AppContext>()
            .AddDefaultTokenProviders();;
        serviceCollection.AddLogic();
        serviceCollection.AddControllers();
        serviceCollection.AddHttpClient();
        serviceCollection.AddAuthentication();
        serviceCollection.AddScoped<IQiwiService, QiwiService>();
        serviceCollection.AddScoped<IGetQiwiClient, GetQiwiClient>();
    }
}