using Rijndael256;

namespace Logic.Services.HashService;

public class HashService : IHashService
{
    private static readonly string SecurityCode = ("Надо сделать по-безопаснее");

    public string? Hash(string value)
    {
        return Rijndael.Encrypt(value, SecurityCode, KeySize.Aes256);
    }

    public string Convert(string? value)
    {
        return Rijndael.Decrypt(value, SecurityCode, KeySize.Aes256);
    }
}