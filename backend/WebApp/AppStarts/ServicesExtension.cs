using System.Text;
using Context;
using Context.Models;
using Logic;
using Logic.Customer.Handlers;
using Logic.QIWI;
using Logic.QIWI.GetClient;
using Logic.Services.HashService;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Identity;
using Microsoft.IdentityModel.Tokens;
using AppContext = Context.AppContext;

namespace backend.AppStarts;

public static class ServicesExtension
{
    public static void AddService(this IServiceCollection serviceCollection)
    {
        serviceCollection.AddContext();
        
        serviceCollection.AddIdentity<User, Role>(options =>
            {
                options.User.AllowedUserNameCharacters =
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._@+ ";
                options.User.RequireUniqueEmail = false;
                options.Password.RequiredLength = 6;
            })
            .AddEntityFrameworkStores<AppContext>()
            .AddDefaultTokenProviders();
        
        serviceCollection.AddAuthentication(options =>
            {
                options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
                options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
                options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
            })

            // TODO Убрать в appsettings
            .AddJwtBearer(options =>
            {
                options.SaveToken = true;
                options.RequireHttpsMetadata = false;
                options.TokenValidationParameters = new TokenValidationParameters()
                {
                    ValidateIssuer = true,
                    ValidateAudience = true,
                    ValidAudience = "http://localhost:4200",
                    ValidIssuer = "http://localhost:5000",
                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes("JWTAuthenticationHIGHsecuredPasswordVVVp1OH7Xzyr"))
                };
            });

        
        serviceCollection.AddLogic();
        serviceCollection.AddControllers();
        serviceCollection.AddHttpClient();
        serviceCollection.AddAuthentication();
        serviceCollection.AddAuthorization();
        serviceCollection.AddScoped<IQiwiService, QiwiService>();
        serviceCollection.AddScoped<IGetQiwiClient, GetQiwiClient>();
        serviceCollection.AddSingleton<IHashService, HashService>();
    }
}