using Context.Abstractions;

namespace Context.Models;

public class ShoppingHistory : BaseEntity<long>
{
    public User Buyer { get; set; }
    
    public User Seller { get; set; }
    
    public Shop Shop { get; set; }

    public DateTime Time { get; set; }
    
}