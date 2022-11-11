namespace Infrastructure.CQRS;

public interface ICommand<in T>
{
    Task Handle(T command);
}