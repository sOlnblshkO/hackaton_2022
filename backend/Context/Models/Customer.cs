using Microsoft.AspNetCore.Identity;

namespace Context.Models;

public class Customer : IdentityUser
{
    public string AccountId { get; set; }
}