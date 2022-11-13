using Domain.DTO.Sms;
using Infrastructure.CQRS;
using Logic.QIWI;

namespace Logic.Sms.Handlers;

public class PayQueryHandler : IQuery<QiwiBillrequestDto, string>
{
    private readonly IQiwiService _qiwiService;

    public PayQueryHandler(IQiwiService qiwiService)
    {
        _qiwiService = qiwiService;
    }

    public Task<string> Execute(QiwiBillrequestDto query)
    {
        var client = _qiwiService.Pay(query);
        return client;
    }
}