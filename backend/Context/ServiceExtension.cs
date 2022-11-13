using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Microsoft.Extensions.DependencyInjection;

namespace Context;

public static class ServiceExtension
{
    
    public static void AddContext(this IServiceCollection serviceProvider)
    {

        serviceProvider.AddDbContext<AppContext>(options=>
            options
                .UseSqlServer("Server=HT-WS-001;Initial Catalog=km-prod;Persist Security Info=True;Integrated Security=SSPI; Encrypt=False;"));
    }
    
    public class DbContextFactory : IDesignTimeDbContextFactory<AppContext>
    {
        public AppContext CreateDbContext(string[] args)
        {
            // Если будет время переделать на json
            var optionsBuilder = new DbContextOptionsBuilder<AppContext>();
            optionsBuilder
                .UseSqlServer("Server=HT-WS-001;Initial Catalog=km-prod;Persist Security Info=True;Integrated Security=SSPI; Encrypt=False;");

            return new AppContext(optionsBuilder.Options);
        }
    }
}