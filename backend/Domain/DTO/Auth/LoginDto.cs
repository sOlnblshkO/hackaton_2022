using System.ComponentModel.DataAnnotations;

namespace Domain.DTO.Auth;

public class LoginDto
{
    [Required(ErrorMessage = "User Name is required")]
    public string? PhoneNumber { get; set; }

    [Required(ErrorMessage = "Password is required")]
    public string? Password { get; set; }
}