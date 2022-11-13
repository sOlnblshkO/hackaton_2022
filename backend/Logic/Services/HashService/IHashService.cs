namespace Logic.Services.HashService;

public interface IHashService
{
    public string? Hash(string value);

    public string Convert(string? value);
}