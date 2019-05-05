package CSE222_HW4_151044058;

public class part1
{
    private Node head = null;
    private int size = 0;

    private static class Node
    {
        private int data;
        private Node next;

        private Node(int dataItem)
        {
            data = dataItem;
            next = null;
        }

        private Node(int dataItem, Node nodeRef)
        {
            data = dataItem;
            next = nodeRef;
        }
    }

    private void addFirst(int item)
    {
        head = new Node(item, head);
        size++;
    }

    private void addAfter(Node node, int item)
    {
        Node temp = new Node(item);
        temp.next = node.next;
        node.next = temp;
        size++;
    }

    private Node getNode(int index)
    {
        Node node = head;

        for(int i = 0; i < index && node != null; ++i)
            node = node.next;

        return node;
    }


    private void add(int index, int item)
    {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        if(index == 0)
            addFirst(item);
        else
        {
            Node temp = getNode(index - 1);
            addAfter(temp, item);
        }
    }

    private void add(int item)
    {
        add(size,item);
    }

    @Override
    public String toString()
    {
        Node nodeRef = head;
        StringBuilder result = new StringBuilder();

        while(nodeRef != null)
        {
            result.append(nodeRef.data);

            if(nodeRef.next != null)
                result.append(" ");
            nodeRef = nodeRef.next;
        }

        return result.toString();
    }


    private static part1 part1Iterative(part1 list)
    {
        part1 returnList = new part1();
        Node temp = list.head;
        Node returnTemp = list.head;
        Node currentTemp = list.head;

        int sizeMax = 0;
        int currentSize = 1;

        while(temp.next != null)
        {
            if(temp.data <= temp.next.data)
            {
                currentSize++;
                temp = temp.next;
            }

            else
            {
                if(currentSize > sizeMax)
                {
                    sizeMax = currentSize;
                    returnTemp = currentTemp;
                }

                currentTemp = temp.next;
                temp = temp.next;
                currentSize = 1;
            }
        }

        if(currentSize > sizeMax)
        {
            sizeMax = currentSize;
            returnTemp = currentTemp;
        }

        for(int i = 0; i < sizeMax; ++i)
        {
            returnList.add(returnTemp.data);

            returnTemp = returnTemp.next;
        }

        return returnList;
    }

    private static part1 part1Recursive(part1 list)
    {
        return helper_part1Recursive(list.head, list.head, list.head,0,1);
    }

    private static part1 copyList(part1 returnList ,Node temp, int sizeValue)
    {
        if(sizeValue == 0)
            return returnList;
        else
        {
            sizeValue--;
            returnList.add(temp.data);
            return copyList(returnList,temp.next, sizeValue);
        }
    }

    private static part1 helper_part1Recursive(Node temp, Node returnTemp, Node currentTemp, int sizeMax, int currentSize)
    {
        part1 returnList = new part1();

        if(temp.next == null)
        {
            if(currentSize > sizeMax)
            {
                sizeMax = currentSize;
                returnTemp = currentTemp;
            }

            return copyList(returnList,returnTemp,sizeMax);
        }

        else
        {
            if(temp.data <= temp.next.data)
            {
                currentSize++;
                temp = temp.next;
                return helper_part1Recursive(temp,returnTemp,currentTemp,sizeMax,currentSize);
            }

            else
            {
                if(currentSize > sizeMax)
                {
                    sizeMax = currentSize;
                    returnTemp = currentTemp;
                }
                currentTemp = temp.next;
                temp = temp.next;
                currentSize = 1;

                return helper_part1Recursive(temp,returnTemp,currentTemp,sizeMax,currentSize);
            }
        }
    }

    public static void main(String [] args)
    {
        part1 myList = new part1();

        myList.add(1);
        myList.add(9);
        myList.add(2);
        myList.add(7);
        myList.add(20);
        myList.add(13);

        System.out.println("=======================================");
        System.out.println("-- MyList --> " + myList.toString());
        System.out.println("=======================================");
        System.out.println("Iterative --> " + part1Iterative(myList).toString());
        System.out.println("=======================================");
        System.out.println("Recursive --> " + part1Recursive(myList).toString());
        System.out.println("=======================================");

    }
}