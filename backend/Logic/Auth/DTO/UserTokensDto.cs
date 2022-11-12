namespace Logic.Auth.DTO;

public class UserTokensDto
{
    public string AccessToken { get; set; }
    public string RefreshToken { get; set; }
    public DateTime ExpireDate { get; set; } 
}