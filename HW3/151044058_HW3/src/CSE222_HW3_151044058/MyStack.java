package CSE222_HW3_151044058;

/**
 * @param <E> The generic object
 * @author Ali Haydar KURBAN
 */
public interface MyStack<E>
{
    /**
     * This method pushes item onto the top of stack.
     * @param item The object which is pushed.
     * @return The object pushed.
     */
    public abstract E push(E item);

    /**
     * This method removes the object which is top of the stack.
     * And return the object which is removed.
     * @return The object removed.
     */
    public abstract  E pop();

    /**
     * This method return the object which is top of the stack.
     * There is no removing.
     * @return The object which is top of the stack.
     */
    public abstract E peek();


    /**
     * This method return true is the stack has no element.
     * Otherwise it return false.
     * @return true if the stack has no element.
     */
    public abstract boolean empty();
}
