package CSE222_HW5_151044058;

public interface MyPriorityQueue<E>
{
    public abstract boolean offer(E item);

    public abstract E remove();

    public abstract E poll();

    public abstract E peek();

    public abstract E element();

    public abstract int size();
}