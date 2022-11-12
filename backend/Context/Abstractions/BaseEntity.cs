namespace Context.Abstractions;

public abstract class BaseEntity<T>
{
    public T Id { get; set; }
}