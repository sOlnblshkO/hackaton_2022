using Microsoft.AspNetCore.Identity;

namespace Context.Models;

public class User: IdentityUser
{
    public string AccountId { get; set; }
    public Role Role { get; set; }
}