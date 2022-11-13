namespace Domain.DTO.Sms;

public class CheckSmsResponseDto
{
    public string requestId { get; set; }
    public StatusSmsRequestDto status { get; set; }
    public Token token { get; set; }
}
