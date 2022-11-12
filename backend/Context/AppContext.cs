using Context.Models;
using Microsoft.EntityFrameworkCore;

namespace Context;

public class AppContext : DbContext
{
    public AppContext(DbContextOptions<AppContext> options)
        : base(options)
    {
    }
    
    public DbSet<User> Users { get; set; }
    public DbSet<UserRole> Roles { get; set; }
    public DbSet<Shop> Shops { get; set; }
    public DbSet<ShoppingHistory> ShoppingHistories { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        
    }
    
}
