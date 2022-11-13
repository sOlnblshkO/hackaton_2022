using System.Security.Claims;

namespace Logic.Payment.DTO;

public class QrCheckQuery
{
    public string PaymentId { get; set; }
    public ClaimsPrincipal ClaimsPrincipal { get; set; }  
}