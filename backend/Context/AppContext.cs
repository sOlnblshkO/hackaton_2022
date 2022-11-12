using Context.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace Context;

public class AppContext : IdentityDbContext<User>
{
    public AppContext(DbContextOptions<AppContext> options)
        : base(options)
    {
    }
    
    public DbSet<User> Customers { get; set; }
    public DbSet<Seller> Sellers { get; set; }
    public DbSet<Payment> Payments { get; set; }
    public DbSet<Role> Roles { get; set; }

}
