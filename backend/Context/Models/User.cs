using Microsoft.AspNetCore.Identity;

namespace Context.Models;

public class User : IdentityUser
{
    public UserRole Role { get; set; }

}