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
    private readonly RegisterSellerCommandHandler _registerSellerCommandHandler;

    public SellerController(GetSellersListQueryHandler getSellersListQueryHandler, GetSellerInfoQueryHandler getSellerInfoQueryHandler, RegisterSellerCommandHandler registerSellerCommandHandler)
    {
        _getSellersListQueryHandler = getSellersListQueryHandler;
        _getSellerInfoQueryHandler = getSellerInfoQueryHandler;
        _registerSellerCommandHandler = registerSellerCommandHandler;
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

    [HttpPost("Register")]
    public async Task<IActionResult> RegisterSeller(RegisterSellerDTO dto)
    {
       await _registerSellerCommandHandler.Handle(dto);
       return Ok();
    }
   
}