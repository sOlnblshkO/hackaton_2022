namespace Logic.Sms.DTO;

public class QiwiBillrequestDto : PayRequestQiwiDto
{
    public string RequestId { get; set; }
    
    
}

public class BillData
{
    public string value { get; set; }
    public string currency { get; set; }
}

public class TokenMethod
{
    public string type = "TOKEN";
    public string paymentToken { get; set; }
}

public class BillCustomer
{
    public string account { get; set; }
}

public class PayRequestQiwiDto
{
    public BillData amount { get; set; }
    public TokenMethod paymentMethod { get; set; }
    public BillCustomer customer { get; set; }
}
