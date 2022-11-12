namespace Logic.Sms.DTO;

public class QiwiBillrequestDto
{
    public string SiteId { get; set; }
    public string BillId { get; set; }
    public BillData Ammount { get; set; }
    public DateTime ExpirationDateTime { get; set; }
    public string comment { get; set; }
    
}

public class BillData
{
    public decimal Value { get; set; }
    public string Currency { get; set; }
}