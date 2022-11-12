using Context.Abstractions;

namespace Context.Models;

public class Shop : BaseEntity<long>
{
    public string Name { get; set; }
    public string FullName { get; set; }
    
    public List<User> Employees { get; set; }

}