using Context.Models;
using Microsoft.EntityFrameworkCore;

namespace Context;

public static class ModelBuilderExtensions
{
    public static void Seed(this ModelBuilder modelBuilder)
    {
    modelBuilder.Entity<Role>().HasData(
            new Role
            {
                Id = "1",
                Name = "User"
            },
            new Role
            {
                Id = "2",
                Name = "Seller"
            }
        );
    }
}