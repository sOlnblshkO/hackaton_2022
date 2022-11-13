namespace Domain.DTO.Seller;

public class SellerProfileDataResponseDto
{
    public long Id { get; set; }
    
    public string AvatarUrl { get; set; }
    
    public string Name { get; set; }
    
    public string LegalName { get; set; }
    
    public string Category { get; set; }
    
    public string Description { get; set; }
    
    public string Address { get; set; }
    
    public string Phone { get; set; }
}