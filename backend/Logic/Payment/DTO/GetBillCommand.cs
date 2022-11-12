using System.Security.Claims;

namespace Logic.Payment.DTO;

public class GetBillCommand: GetBillForUserRequestDto
{
    public ClaimsPrincipal ClaimsPrincipal { get; set; } 
}