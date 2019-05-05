package CSE222_HW3_151044058;

import java.util.EmptyStackException;

/**
 * This MyStackImplementing class implements MyStack interface
 * @param <E> The generic object
 * @author Ali Haydar KURBAN
 */
public class MyStackImplementing<E> implements MyStack<E>
{
    /**
     * This method pushes item onto the top of stack.
     * @param item The object which is pushed.
     * @return The object pushed.
     */
    @Override
    public E push(E item)
    {
        add(item);
        topOfStack++;
        return item;
    }

    /**
     * This method removes the object which is top of the stack.
     * And return the object which is removed.
     * @return The object removed.
     */
    @Override
    public E pop()
    {
        E return_Value;
        return_Value = remove();

        topOfStack--;
        return return_Value;
    }

    /**
     * This method return the object which is top of the stack.
     * There is no removing.
     * @return The object which is top of the stack.
     */
    @Override
    public E peek()
    {
        E return_Value;
        return_Value = get();

        return return_Value;
    }

    /**
     * This method return true is the stack has no element.
     * Otherwise it return false.
     * @return true if the stack has no element.
     */
    @Override
    public boolean empty()
    {
        return topOfStack == -1;
    }

    /**
     * It is to store and implement MyStack.
     */
    private E [] Array;
    /**
     * It is an index counter where the last object of MyStack is.
     */
    private int topOfStack = -1;

    /**
     * It is initial capacity of Array which is storage place for MyStack.
     */
    private static final int FIRST_CAPACITY = 20;
    /**
     * It is a number, how many objects are in my Array at a moment.
     */
    private int size = 0;
    /**
     * It is a number, how many objects can stored in my Array.
     */
    private int capacity = 0;

    /**
     * It is a constructor to create a new Array with FIRST_CAPACITY.
     */
    @SuppressWarnings("unchecked")
    public MyStackImplementing()
    {
        capacity = FIRST_CAPACITY;
        Array = (E[]) new Object[capacity];
    }

    /**
     * This methods adds object to at the last index of Array.
     * If the Array is full firstly reallocates and adds the object.
     * @param item The object which is added at the Array[size].
     * @return true.
     */
    private boolean add(E item)
    {
        if(isFull())
            reallocate();

        Array[size] = item;
        size++;
        return true;
    }

    /**
     * This methods reallocates a memory with 2 times capacity.
     * Copies all object from Array to temp_array.
     * And assigns the address of temp_array to Array.
     */
    @SuppressWarnings("unchecked")
    private void reallocate()
    {
        capacity = 2 * capacity;

        E [] temp_array;

        temp_array = (E[]) new Object[capacity];

        for(int i = 0; i < size; ++i)
            temp_array[i] = Array[i];


        Array = temp_array;
    }

    /**
     * This method removes and returns an object which is the last object of Array.
     * @return The object which is removed.
     */
    private E remove()
    {
        E return_Value;
        if(!empty())
        {
            return_Value = Array[size - 1];
            size--;
            return return_Value;
        }
        else
            throw new EmptyStackException();

    }

    /**
     * This method returns an object which is the last object of Array. There is no removing.
     * @return The object which is the last object of Array.
     */
    private E get()
    {
        E return_Value;
        if(!empty())
        {
            return_Value = Array[size - 1];
            return return_Value;
        }
        else
            throw new EmptyStackException();
    }

    /**
     * The methods return true if Array has maximum numbers of objects.
     * @return true if size and capacity equal each others.
     */
    private boolean isFull()
    {
        return size == capacity;
    }
}