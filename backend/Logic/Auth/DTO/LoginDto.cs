using System.ComponentModel.DataAnnotations;

namespace Logic.Auth.DTO;

public class LoginDto
{
    [Required(ErrorMessage = "User Name is required")]
    public string? PhoneNumber { get; set; }

    [Required(ErrorMessage = "Password is required")]
    public string? Password { get; set; }
}