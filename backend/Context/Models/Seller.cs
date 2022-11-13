using Context.Abstractions;

namespace Context.Models;

public class Seller : BaseEntity<long>
{
    public User User { get; set; }
    public string AvatarUrl { get; set; }
    public string SiteId { get; set; }
    public string Name { get; set; }
    public string LegalName { get; set; }
    
    public string Description { get; set; }
    
    public string Caregory { get; set; }
    
    public string Address { get; set; }
}