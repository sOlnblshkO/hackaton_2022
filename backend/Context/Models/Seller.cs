using Context.Abstractions;
using Microsoft.AspNetCore.Identity;

namespace Context.Models;

public class Seller : IdentityUser
{
    public string AvatarUrl { get; set; }
    public string SiteId { get; set; }
    public string Name { get; set; }
    public string LegalName { get; set; }
}