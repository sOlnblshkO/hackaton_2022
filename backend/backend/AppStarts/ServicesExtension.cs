using Context;
using Infrastructure.Extensions;
using Microsoft.AspNetCore.Identity;

namespace backend.AppStarts;

public static class ServicesExtension
{
    public static void AddService(this IServiceCollection serviceCollection)
    {
        serviceCollection.AddLogic();
        serviceCollection.AddContext();
    }
}