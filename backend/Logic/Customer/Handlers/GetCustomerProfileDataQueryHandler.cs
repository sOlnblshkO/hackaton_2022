using Infrastructure.CQRS;
using Logic.Customer.DTO;
using Microsoft.EntityFrameworkCore;
using AppContext = Context.AppContext;

namespace Logic.Customer.Handlers;

public class GetCustomerProfileDataQueryHandler: IQuery<string,CustomerProfileDataResponseDto>
{
    private readonly AppContext _dbContext;

    public GetCustomerProfileDataQueryHandler(AppContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<CustomerProfileDataResponseDto> Execute(string customerName)
    {
        var customer = await _dbContext.Customers.FirstOrDefaultAsync(x=>x.UserName == customerName);
        return new CustomerProfileDataResponseDto()
        {
            Id = customer.Id,
            Name = customer.Name,
            Phone = customer.PhoneNumber,
            Surname = customer.Surname
        };
    }
}