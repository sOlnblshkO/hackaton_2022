using Infrastructure.CQRS;
using Logic.QIWI;
using Logic.Sms.DTO;

namespace Logic.Sms.Handlers;

public class GetBillQueryHandler : IQuery<QiwiBillrequestDto, string>
{
    private readonly IQiwiService _qiwiService;

    public GetBillQueryHandler(IQiwiService qiwiService)
    {
        _qiwiService = qiwiService;
    }

    public Task<string> Execute(QiwiBillrequestDto query)
    {
        var client = _qiwiService.CreateBill(query);
        return client;
    }
}