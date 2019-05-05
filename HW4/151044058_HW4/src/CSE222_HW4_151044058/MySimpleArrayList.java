package CSE222_HW4_151044058;

import java.util.Iterator;

/**
 * @author Ali Haydar KURBAN.
 * @param <E> It represent the generic type.
 */
public class MySimpleArrayList<E> implements Iterable
{
    /**
     * It is to store and implement MyStack.
     */
    private E [] Array;

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
    private int capacity;

    /**
     * It represent the current index for iterator.
     */
    int index = 0;

    /**
     * It is a constructor to create a new Array with FIRST_CAPACITY.
     */
    @SuppressWarnings("unchecked")
    public MySimpleArrayList()
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
    public boolean add(E item)
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
     * This method adds a MySimpleArrayList object to other MySimpleArrayList object.
     * This method uses iterator.
     * @param Collection It represent a MySimpleArrayList object.
     */
    public void addArrayList(MySimpleArrayList Collection)
    {
        Iterator<E> CollectionIter = Collection.iterator();

        while(CollectionIter.hasNext())
        {
            this.add(CollectionIter.next());
        }
    }

    /**
     * The methods return true if Array has maximum numbers of objects.
     * @return true if size and capacity equal each others.
     */
    private boolean isFull()
    {
        return size == capacity;
    }

    /**
     * Returns new SpiralArrayIterator.
     * @return iterator.
     */
    @Override
    public Iterator iterator()
    {
        return new SpiralArrayIterator();
    }

    /**
     * Inner SpiralArrayIterator class.
     * @author Ali Haydar KURBAN
     */
    private class SpiralArrayIterator implements Iterator
    {
        /**
         * Checks if there is an element .
         * @return true if there is at least one element, otherwise return false.
         */
        @Override
        public boolean hasNext()
        {
            return index < size;
        }

        /**
         *
         * @return an element which is in index of Array.
         */
        @Override
        public Object next()
        {
            return Array[index++];
        }
    }

    /**
     * This method uses iterator.
     * Print the all list.
     */
    public void printArray()
    {
        SpiralArrayIterator iter = new SpiralArrayIterator();
        while(iter.hasNext())
        {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
}