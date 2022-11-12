using Domain.DTO.Seller;
using Logic.Seller.Handlers;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;


[ApiController]
[Authorize]
[Route("[controller]")]
public class SellerController: ControllerBase
{
    private readonly GetSellersListQueryHandler _getSellersListQueryHandler;
    private readonly GetSellerInfoQueryHandler _getSellerInfoQueryHandler;

    public SellerController(GetSellersListQueryHandler getSellersListQueryHandler, GetSellerInfoQueryHandler getSellerInfoQueryHandler)
    {
        _getSellersListQueryHandler = getSellersListQueryHandler;
        _getSellerInfoQueryHandler = getSellerInfoQueryHandler;
    }

    [HttpGet("GetSellers")]
    public async Task<IActionResult> GetSellers(string? subStringQuery)
    {
        var result = await _getSellersListQueryHandler.Execute(subStringQuery);
        return Ok(result);
    }
    
    
    [HttpGet("GetSellerInfo/{id}")]
    public async Task<IActionResult> GetSellers(int id)
    {
        var result = await _getSellerInfoQueryHandler.Execute(id);
        return Ok(result);
    }
   
}