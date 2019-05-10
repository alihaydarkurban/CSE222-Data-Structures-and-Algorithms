import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable
{
    private Node head = null;
    private int size = 0;

    public class Node<E>
    {
        private Node next;
        private E data;

        private Node(E item)
        {
            data = item;
            next = null;
        }

        private Node(E item, Node nodeRef)
        {
            data = item;
            next = nodeRef;
        }

        public E getData()
        {
            return data;
        }
    }

    public class MyLinkedListIter implements Iterator
    {
        private Node nextItem;

        private Node lastItemReturned;

        public MyLinkedListIter()
        {
            nextItem = head;
        }

        @Override
        public boolean hasNext()
        {
            return nextItem!= null;
        }

        @Override
        public Node next()
        {
            if(!hasNext())
                throw new NoSuchElementException();

            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            return lastItemReturned;
        }

        @Override
        public void remove(){/*EMPTY*/}
    }

    @Override
    public Iterator iterator()
    {
        return new MyLinkedListIter();
    }

    public int getSize()
    {
        return size;
    }

    public E get(int index)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        Node temp = getNode(index);
        return (E)temp.data;
    }

    public E set(int index, E newValue)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        Node temp = getNode(index);
        E result = (E) temp.data;
        temp.data = newValue;

        return result;
    }

    public E remove()
    {
        return remove(size - 1);
    }

    public boolean add(E item)
    {
        add(size,item);
        return true;
    }

    @Override
    public String toString()
    {
        Node temp = head;
        String result = "";
        String tempString = "";
        while (temp != null)
        {
            tempString = (String) temp.data;
            result = result + tempString + " ";

            temp = temp.next;
        }
        return result;
    }

    private E remove(int index)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);
        E result;
        if(index == 0)
            result =  removeFirst();

        else
        {
            Node temp = getNode(index - 1);
            result = removeAfter(temp);
        }

        return result;
    }

    private void add(int index, E item)
    {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException(index);
        if(index == 0)
            addFirst(item);

        else
        {
            Node temp = getNode(index - 1);
            addAfter(item,temp);
        }

    }


    private void addFirst(E item)
    {
        head = new Node(item,head);
        size++;
    }

    private void addAfter(E item, Node nodeRef)
    {

        Node temp = new Node(item);
        temp.next = nodeRef.next;
        nodeRef.next = temp;
        size++;
    }


    private E removeFirst()
    {
        if(head != null)
        {
            Node temp = head;
            head = temp.next;
            size--;
            return (E) temp.data;
        }
        else
            return null;

    }

    private E removeAfter(Node nodeRef)
    {
        Node temp = nodeRef.next;
        if(temp != null)
        {
            nodeRef.next = temp.next;
            size--;
            return (E) temp.data;
        }
        else
            return null;
    }

    private Node getNode(int index)
    {
        Node temp = head;
        int i = 0;
        while(i < index && temp != null)
        {
            temp = temp.next;
            i++;
        }

        return temp;
    }
}