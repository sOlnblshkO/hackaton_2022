using System.Reflection;
using Infrastructure.CQRS;
using Microsoft.Extensions.DependencyInjection;

namespace Infrastructure.Extensions;

public static class ServiceExtension
{
    public static void AddLogic(this IServiceCollection serviceProvider)
    {
        var executeType = typeof(ICommand<>);
        var executeTypeWithT = typeof(IQuery<,>);
        var assemblyTypes = Assembly
            .GetExecutingAssembly()
            .GetTypes()
            .ToList();
        var allExecutableTypes = assemblyTypes
            .Where(x => !x.IsAbstract &&
                        x
                            .GetInterfaces()
                            .Any(xx => xx.IsGenericType && (xx.GetGenericTypeDefinition() == executeType ||
                                                            xx.GetGenericTypeDefinition() == executeTypeWithT)))
            .ToList();
        var allNotGenericExecutableTypes = allExecutableTypes.Where(x => !x.IsGenericType);
        foreach (var executableType in allNotGenericExecutableTypes)
        {
            serviceProvider.AddScoped(executableType);
        }
    }
}