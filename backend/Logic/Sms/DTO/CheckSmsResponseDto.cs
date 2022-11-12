namespace Logic.Sms.DTO;

public class CheckSmsResponseDto
{
    public string requestId { get; set; }
    public StatusSmsRequestDto status { get; set; }
    public Token token { get; set; }
}
