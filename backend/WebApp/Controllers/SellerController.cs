using Domain.DTO.Seller;
using Logic.Seller.Handlers;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;


[ApiController]
[Route("[controller]")]
public class SellerController: ControllerBase
{
    private readonly GetSellersListQueryHandler _getSellersListQueryHandler;

    public SellerController(GetSellersListQueryHandler getSellersListQueryHandler)
    {
        _getSellersListQueryHandler = getSellersListQueryHandler;
    }

    [HttpGet("GetSellers")]
    public async Task<IActionResult> GetSellers(string? subStringQuery)
    {
        var result = await _getSellersListQueryHandler.Execute(subStringQuery);
        return Ok(result);
    }
   
}