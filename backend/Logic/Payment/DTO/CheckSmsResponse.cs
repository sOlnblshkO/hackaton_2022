namespace Logic.Payment.DTO;

public class CheckSmsResponse
{
    public string? PaymentId { get; set; }
    public string Amount { get; set; }
    public string Currency { get; set; }
    public string ExpDate { get; set; }
    public string SellerLegalName { get; set; }
}