namespace Domain.DTO.Sms;

public class GetCodeForPhoneDto
{
    public string PhoneNumber { get; set; }
    public string RequestId { get; set; }
}