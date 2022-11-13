using Context.Abstractions;
using Context.Enums;

namespace Context.Models;

public class Payment : BaseEntity<long>
{
    public User User { get; set; }
    public string PaymentToken { get; set; }
    public DateTime ExpirationDateTime { get; set; }
    public string RequestId { get; set; }
    public double Amount { get; set; }
    public PaymentState PaymentState { get; set; } 
    public Seller Seller { get; set; }
    
    public string Currency { get; set; }
  
}