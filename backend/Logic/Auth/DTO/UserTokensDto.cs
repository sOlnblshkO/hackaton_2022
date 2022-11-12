namespace Logic.Auth.DTO;

public class UserTokensDto
{
    public string RequestId { get; set; }
    public StatusDto Status { get; set; }
    public Token Token { get; set; } 
}
public class StatusDto
{
    public string Value { get; set; }
}

public class Token
{
    public string Value { get; set; }
    public DateTime ExpireDate { get; set; }
}