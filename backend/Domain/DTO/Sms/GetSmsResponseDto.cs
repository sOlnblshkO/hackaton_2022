namespace Domain.DTO.Sms;

public class GetSmsResponseDto
{
    public string requestId { get; set; }
    public StatusSmsRequestDto Status { get; set; }
}

public class StatusSmsRequestDto : ValueDto
{
    
}