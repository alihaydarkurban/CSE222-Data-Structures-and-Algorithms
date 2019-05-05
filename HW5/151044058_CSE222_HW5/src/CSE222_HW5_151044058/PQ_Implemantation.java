package CSE222_HW5_151044058;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class PQ_Implemantation<E> implements MyPriorityQueue<E>
{
    private MyArrayList<E> theData;
    private Comparator<E> comparator = null;

    @Override
    public int size()
    {
        return theData.getSize();
    }

    private void swap(int i, int j)
    {
        E temp;
        temp = theData.get(i);
        theData.set(i, theData.get(j));
        theData.set(j, temp);
    }

    public PQ_Implemantation(Comparator<E>comp)
    {
        theData = new MyArrayList<E>();
        comparator = comp;
    }

    @Override
    public boolean offer(E item)
    {
        theData.add(item);
        int child = theData.getSize() - 1;
        int parent = (child - 1) / 2;

        while(parent >= 0 && comparator.compare(theData.get(parent), theData.get(child)) > 0)
        {
            swap(parent, child);
            child = parent;
            parent = (child - 1)/2;
        }

        return true;
    }

    @Override
    public E remove()
    {
        if(theData.isEmpty())
        {
            throw new NoSuchElementException();
        }
        E result = theData.get(0);

        if(theData.getSize() == 1)
        {
            theData.remove(0);
            return result;
        }

        theData.set(0, theData.remove(theData.getSize() - 1));

        int parent = 0;

        while(true)
        {
            int leftChild = 2 * parent + 1;
            if(leftChild >= theData.getSize())
            {
                break;
            }

            int rightChild = leftChild + 1;
            int minChild = leftChild;

            if(rightChild < theData.getSize() &&
                    comparator.compare(theData.get(leftChild), theData.get(rightChild)) > 0)
            {
                minChild = rightChild;
            }

            if(comparator.compare(theData.get(parent), theData.get(minChild)) > 0)
            {
                swap(parent, minChild);
                parent = minChild;
            }
            else
            {
                break;
            }
        }
        return result;
    }
    @Override
    public E poll()
    {
        if(theData.isEmpty())
        {
            return null;
        }
        E result = theData.get(0);

        if(theData.getSize() == 1)
        {
            theData.remove(0);
            return result;
        }

        theData.set(0, theData.remove(theData.getSize() - 1));

        int parent = 0;

        while(true)
        {
            int leftChild = 2 * parent + 1;
            if(leftChild >= theData.getSize())
            {
                break;
            }

            int rightChild = leftChild + 1;
            int minChild = leftChild;

            if(rightChild < theData.getSize() &&
                    comparator.compare(theData.get(leftChild), theData.get(rightChild)) > 0)
            {
                minChild = rightChild;
            }

            if(comparator.compare(theData.get(parent), theData.get(minChild)) > 0)
            {
                swap(parent, minChild);
                parent = minChild;
            }
            else
            {
                break;
            }
        }
        return result;
    }
    @Override
    public E peek()
    {
        if(theData.isEmpty())
        {
            return null;
        }
        E result = theData.get(0);

        if(theData.getSize() == 1)
        {
            theData.get(0);
            return result;
        }

        theData.set(0, theData.get(theData.getSize() - 1));

        int parent = 0;

        while(true)
        {
            int leftChild = 2 * parent + 1;
            if(leftChild >= theData.getSize())
            {
                break;
            }

            int rightChild = leftChild + 1;
            int minChild = leftChild;

            if(rightChild < theData.getSize() &&
                    comparator.compare(theData.get(leftChild), theData.get(rightChild)) > 0)
            {
                minChild = rightChild;
            }

            if(comparator.compare(theData.get(parent), theData.get(minChild)) > 0)
            {
                swap(parent, minChild);
                parent = minChild;
            }
            else
            {
                break;
            }
        }
        return result;
    }

    @Override
    public E element()
    {
        if(theData.isEmpty())
        {
            throw new NoSuchElementException();
        }
        E result = theData.get(0);

        if(theData.getSize() == 1)
        {
            theData.get(0);
            return result;
        }

        theData.set(0, theData.get(theData.getSize() - 1));

        int parent = 0;

        while(true)
        {
            int leftChild = 2 * parent + 1;
            if(leftChild >= theData.getSize())
            {
                break;
            }

            int rightChild = leftChild + 1;
            int minChild = leftChild;

            if(rightChild < theData.getSize() &&
                    comparator.compare(theData.get(leftChild), theData.get(rightChild)) > 0)
            {
                minChild = rightChild;
            }

            if(comparator.compare(theData.get(parent), theData.get(minChild)) > 0)
            {
                swap(parent, minChild);
                parent = minChild;
            }
            else
            {
                break;
            }
        }
        return result;
    }
}