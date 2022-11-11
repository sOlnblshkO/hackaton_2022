namespace Infrastructure.CQRS;

public interface IQuery<in TQ, T>
{
    Task<T> Execute(TQ query);
}