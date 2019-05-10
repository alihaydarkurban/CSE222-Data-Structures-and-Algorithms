public class MyArrayList<E>
{
    private E [] Array;
    private int size = 0;
    private int capacity = 0;
    private final int INITIAL_CAPACITY = 20;

    @SuppressWarnings("unchecked")
    public MyArrayList()
    {
        capacity = INITIAL_CAPACITY;

        Array = (E[] )new Object[capacity];
    }

    public void add(E element)
    {
        if(isFull())
            reallocate();

        Array[size] = element;
        size++;

    }

    private boolean isFull()
    {
        return size == capacity;
    }


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

    public E get(int index)
    {
        if(index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);

        return Array[index];
    }

    public E set(int index, E element)
    {
        if(index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);

        E oldElement = Array[index];
        Array[index] = element;
        return oldElement;
    }

    public E remove(int index)
    {
        if(index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);

        E oldElement = Array[index];
        for(int i = index + 1; i < size; i++)
        {
            Array[i - 1] = Array[i];
        }
        size--;
        return oldElement;
    }

    public int getSize()
    {
        return size;
    }

    public int getCapacity()
    {
        return capacity;
    }

    @Override
    public String toString()
    {
        String temp;
        String result = "";

        for(int i = 0; i < size; ++i)
        {
            temp = Array[i].toString() + " ";

            result = result + temp;
        }
        return result;
    }

    public boolean contain(E o)
    {
        for(int i = 0; i < size; ++i)
        {
            if(o.equals(Array[i]))
                return true;

        }

        return false;
    }

    public int getIndex(E o)
    {
        for(int i = 0; i < size; ++i)
        {
            if(o.equals(Array[i]))
                return i;

        }
        return -1;
    }
}