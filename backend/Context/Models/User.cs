using Microsoft.AspNetCore.Identity;

namespace Context.Models;

public class User: IdentityUser
{
    public Role Role { get; set; }
}