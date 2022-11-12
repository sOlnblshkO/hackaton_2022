namespace Logic.Sms.DTO;

public class QiwiBillrequestDto
{
    public BillData Amount { get; set; }
    public TokenMethod paymentMethod { get; set; }
    public BillCustomer Customer { get; set; }
    
}

public class BillData
{
    public decimal Value { get; set; }
    public string Currency { get; set; }
}

public class TokenMethod
{
    public string Type = "TOKEN";
    public string PaymentToken { get; set; }
}

public class BillCustomer
{
    public string Account { get; set; }
}
