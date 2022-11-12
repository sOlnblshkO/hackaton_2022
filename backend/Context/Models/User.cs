using Microsoft.AspNetCore.Identity;

namespace Context.Models;

public class User: IdentityUser
{
    public string Name { get; set; } 
    public string Surname { get; set; }
    public Role Role { get; set; }
}