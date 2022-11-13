using System.Security.Claims;

namespace Logic.Payment.DTO;

public class CheckSmsQuery : CheckSmsDto
{
    public ClaimsPrincipal ClaimsPrincipal { get; set; }  
}