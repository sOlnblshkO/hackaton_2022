namespace backend.AppStarts;

public static class WebAppBuilderExtension
{
    public static void ConfigureWebApp(this WebApplication webApplication)
    {
        webApplication.MapControllers();
        webApplication.UseRouting();
    }
}