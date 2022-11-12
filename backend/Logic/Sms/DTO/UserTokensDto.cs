namespace Logic.Sms.DTO;

public class UserTokensDto
{
    public string RequestId { get; set; }
    public StatusDto Status { get; set; }
    public Token Token { get; set; } 
    public Customer Customer { get; set; }
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

public class Customer
{
    public string Account { get; set; }
    
}