namespace Logic.Payment.DTO;

public class GetBillForUserRequestDto
{
    public string SellerId { get; set; }
    public string Amount { get; set; }
    
    public string Currency { get; set; }
}