package CSE222_HW2_151044058;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ExperimentList class implements Iterable.
 * @author Ali Haydar KURBAN
 */
public class ExperimentList implements Iterable
{
    /**
     * It represents the head of ExperimentList.
     */
    private Node head = null;
    /**
     * Total number of elements currently in the ExperimentList.
     */
    private int size = 0;

    /**
     * It represents the head of ExperimentList's day.
     */
    private Node head_day = null;
    private static final int NOT_CHANGE = 0;

    /**
     * Returns the total number of elements currently in the ExperimentList.
     * @return size
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Returns new ExperimentListIter.
     * @return iterator.
     */
    public Iterator iterator()
    {
        return new ExperimentListIter();
    }

    /**
     * This is an inner class. ExperimentList consist of Node.
     * Inner Node class.
     * @author Ali Haydar KURBAN
     */
    private class Node
    {
        /**
         * It represents the Experiment.
         */
        private Experiment data;
        /**
         * It represents the next Node.
         */
        private Node next;
        /**
         * It represents the next day Node.
         */
        private Node next_day;

        /**
         * Node constructor, sets dataItem to data.
         * @param dataItem this is the Experiment reference.
         */
        private Node(Experiment dataItem)
        {
            data = dataItem;
            next = null;
        }

        /**
         * Node constructor, sets dataItem to data and sets nodeRef to next.
         * @param dataItem this is the Experiment reference.
         * @param nodeRef this is the Node reference.
         */
        private Node(Experiment dataItem, Node nodeRef)
        {
            data = dataItem;
            next = nodeRef;
        }
    }

    /**
     * Inner Experiment class.
     * This is an inner class. Node consist of Experiment.
     * @author Ali Haydar KURBAN
     */
    public static class Experiment
    {
        /**
         * It represents Experiment's name.
         */
        private String setup;
        /**
         * It represents Experiment's day.
         */
        private int day;
        /**
         * It represents Experiment's time when it starts.
         */
        private String time;
        /**
         * It represents Experiment's completed or not.
         */
        private boolean completed;
        /**
         * It represents Experiment's accuracy.
         */
        private float accuracy;

        /**
         * Experiment constructor, sets setupName to setup, dayValue to day, timeV to time, completedV to completed, accuracyV to accuracy.
         * @param setupName It represents Experiment's name.
         * @param dayValue It represents Experiment's day.
         * @param timeV It represents Experiment's time when it starts.
         * @param completedV It represents Experiment's completed or not.
         * @param accuracyV It represents Experiment's accuracy. If completed is false then accuracy is -1.00, otherwise accuracy is accuracyV.
         */
        public Experiment(String setupName, int dayValue, String timeV, boolean completedV, float accuracyV)
        {
            setup = setupName;
            day = dayValue;
            time = timeV;
            completed = completedV;

            if(completed == false)
                accuracy = -1.00f;
            else
                accuracy = accuracyV;
        }

        /**
         * toString method is used to make a result string of Experiment data fields.
         * @return formatted string version of collection.
         */
        @Override
        public String toString()
        {
            String result;
            result = "SetUp : " + setup + " Day : " + day + " Time : " + time + " Completed : " + completed + " Accuracy : " + accuracy;
            return result;
        }
    }

    /**
     *  Inner ExperimentListIter class.
     * @author Ali Haydar KURBAN
     */
    private class ExperimentListIter implements Iterator
    {
        /**
         * It represents the nextItem which iterator shows just after.
         */
        private Node nextItem;
        /**
         * It represents the lastItemReturned which iterator shows just before.
         */
        private Node lastItemReturned;

        /**
         * ExperimentListIter constructor, sets nextItem to head.
         */
        public ExperimentListIter()
        {
            nextItem = head;
        }

        /**
         * checks if the there is a next Node.
         * @return true if there is next Node,  false otherwise.
         */
        @Override
        public boolean hasNext()
        {
            return nextItem != null;
        }

        /**
         * If the there is a next Node returns it.
         * @return lastItemReturned Node.
         */
        @Override
        public Node next()
        {
            if(!hasNext())
                throw new NoSuchElementException();

            lastItemReturned = nextItem.next;
            nextItem = nextItem.next;

            return lastItemReturned;
        }
        @Override
        public void remove(){/*EMPTY*/}
    }


    /**
     * This method adds the item in a list.
     * If the list is empty or the item's day is smaller than first element's day, then calls helper addFirst method.
     * Otherwise calls helper getNode method and finds the node which is just smaller than the item's day and then calls the helper addAfter method.
     * Finally call helper Linked_of_Days method and links the days.
     * @param item Experiment reference.
     * @return true always.
     */
    public boolean addExp(Experiment item)
    {
        if(head == null || head.data.day > item.day)
            addFirst(item);
        else
        {
            int tempDay = item.day;
            Node temp = getNode(tempDay);
            addAfter(temp, item);
        }

        this.Linked_of_Days();
        return true;
    }

    /**
     * It returns the Experiment according to given day and index.
     * This method has getExp_With_Node method, which is returns the node according to given day and index.
     * node.data means the Experiment.
     * getExp_With_Node(dayV,index) can throw exception.
     * @param dayV It represents the day where you get.
     * @param index It represents the index where you get.
     * @return Experiment reference.
     */
    public Experiment getExp(int dayV, int index)
    {
        Node temp = getExp_With_Node(dayV,index);
        return temp.data;
    }

    /**
     * This method modify the Experiment with newVaule according to given dayValue and index.
     * It returns the old Experiment.
     * This method has getExp_With_Node method, which is returns the node according to given day and index.
     * @param dayValue It represents the day where you set.
     * @param index It represents the index where you set.
     * @param newValue It represents the Experiment reference what you set.
     * @return Experiment reference where you set.
     */
        public Experiment setExp(int dayValue ,int index, Experiment newValue)
    {
        Node temp = getExp_With_Node(dayValue,index);
        Experiment result = temp.data;
        temp.data = newValue;

        return result;
    }

    /**
     * This method delete the Experiment according to given day and index.
     * It returns Experiment which is deleted.
     * This method has getExp method, which is control the day and index is legal.
     * This method has getNode_to_Remove method, which is returns the node according to given day and index - 1.
     * This method has removeFirst and removeAfter helper method to delete.
     * This method has Linked_of_Days method to link days.
     * @param day It represents the day where you remove.
     * @param index It represents the index where you remove.
     * @return Experiment reference which is removed.
     */
    public Experiment removeExp(int day, int index)
    {
        Experiment returnValue;
        if(index == 0 && day == head.data.day)
        {
            returnValue = removeFirst();
        }
        else
        {
            this.getExp(day,index); // This is control method for day and index are legal
            Node temp = getNode_to_Remove(day,index - 1);
            returnValue = removeAfter(temp);
        }

        this.Linked_of_Days();
        return returnValue;
    }

    /**
     * This method returns a new list which has only true completed experiment according to given day.
     * This method has getExp_With_Node method, which is returns the node according to given day and index (index = 0).
     * It can throw exception if the day is illegal.
     * It uses addExp method to add experiment to the newList.
     * @param day It represents the day of Experiment's.
     * @return newList which is new ExperimentList.
     */
    public ExperimentList listExp(int day)
    {

        ExperimentList newList = new ExperimentList(); // Create a newList

        Node temp = getExp_With_Node(day,NOT_CHANGE); // Find the node with given day

        // Check the completed if true or not until the day does not change
        while(temp.next != null && (temp.data.day == temp.next.data.day))
        {
            if(temp.data.completed == true )
            {
                newList.addExp(temp.data); // add Experiment
            }
            temp = temp.next;
        }
        if(temp.data.completed == true )
        {
            newList.addExp(temp.data); // add Experiment
        }

        return newList;
    }

    /**
     * This method remove the all Experiment according to given day.
     * This method uses helper getExp_With_Node method to check day value is legal.
     * @param day It represents the day of Experiment's which is removed.
     */
    public void removeDay(int day)
    {
        getExp_With_Node(day,NOT_CHANGE); // To check if day valid or not
        Node temp = head;

        if(head.data.day == day)
        {
            head = head.next_day;
        }
        else
        {
            while(temp.next != null)
            {
                if(temp.next.data.day == day)
                {
                    if(temp.next.next_day == null)
                        temp.next = null;
                    else
                        temp.next= temp.next.next_day;
                }
                else
                {
                    temp = temp.next;
                }
            }
        }
        this.Linked_of_Days(); // Link the days
    }

    /**
     * This method order experiment of given day according to accuracy.
     * This method use helper getExp_With_Node method to find node where it starts.
     * getExp_With_Node can throw exception if day is illegal.
     * @param day It represents the day of Experiment's which is ordered.
     */
    public void orderDay(int day)
    {
        Node temp = getExp_With_Node(day,NOT_CHANGE);

        Experiment ex;
        Node temp2;

        while(temp.data.day == day)
        {
            if(temp.next == null)
                break;
            else
                temp2 = temp.next;

            while(temp2.data.day == day)
            {
                if(temp.data.accuracy > temp2.data.accuracy)
                {
                    ex = temp.data;
                    temp.data = temp2.data;
                    temp2.data = ex;
                }
                if(temp2.next == null)
                    break;
                else
                    temp2=temp2.next;
            }
            if(temp.next == null)
                break;
            else
                temp = temp.next;
        }
    }

    /**
     * This method create a new list.
     * This method uses addExp to add experiment to new list from real list.
     * New list is ordered and returned.
     * @return ordered_list which is ordered new ExperimentList.
     */
    public ExperimentList orderExperiments()
    {
        Node temp = head;
        ExperimentList ordered_list = new ExperimentList();

        while(temp != null)
        {
            ordered_list.addExp(temp.data);
            temp = temp.next;
        }

        Node temp2 = ordered_list.head;
        Experiment ex;
        Node temp3 = temp2.next;

        while(temp2 != null)
        {
            temp3 = temp2.next;

            while(temp3 != null)
            {
                if(temp2.data.accuracy > temp3.data.accuracy)
                {
                    ex = temp2.data;
                    temp2.data = temp3.data;
                    temp3.data = ex;
                }
                temp3=temp3.next;
            }
            temp2 = temp2.next;
        }

        return ordered_list;
    }

    /**
     * toString method to use printing all list on to screen.
     * It uses iterator.
     * @return formatted string version of collection.
     */
    @Override
    public String toString()
    {
        ExperimentListIter iter = new ExperimentListIter();
        String result = "======================================================================================================\n";
        String temp;
        while(iter.hasNext())
        {
            temp = iter.nextItem.data.toString();

            result = result + temp;

            if(iter.nextItem.next != null)
                result = result + "\n";

            iter.next();
        }
        return result + "\n======================================================================================================";
    }

    /**
     * This methods print the screen days links.
     */
    //  !!!!IMPORTANT!!!! IT MEANS LIKE YOUR listAll() METHOD
    //  !!!!IMPORTANT!!!! IT PRINTS ON THE SCREEN NEXT_DAY LINKS
    //  !!!!IMPORTANT!!!! FOR EXAMPLE IN HOMEWORK PDF DAY 1 LINKS TO DAY 2 AND DAY 2 LINKS TO DAY 3
    //  !!!!IMPORTANT!!!! TOP LINK IN THE IN HOMEWORK PDF
    public void print_LinkedDay()
    {
        Node temp = head_day;
        if(temp != null)
            System.out.println(temp.data.toString());
        while(temp != null)
        {
            if(temp.next_day != null)
                System.out.println(temp.next_day.data.toString());
            temp = temp.next;
        }

    }

    /**
     * It is a helper method for addExp().
     * It adds the item at the beginning of the list.
     * @param item It represents the Experiment reference.
     */
    private void addFirst(Experiment item)
    {
        head = new Node(item, head);
        size++;
    }

    /**
     * It is a helper method for addExp().
     * It adds the item after the given node.
     * @param node It represents the Node reference.
     * @param item It represents the Experiment reference.
     */
    private void addAfter(Node node, Experiment item)
    {
        Node temp = new Node(item);
        temp.next = node.next;
        node.next = temp;
        size++;
    }

    /**
     * It is a helper method for addExp().
     * It returns a node just before the given tempday.
     * @param tempday It represents the day of Experiment's.
     * @return Node reference just before the given tempday.
     */
    private Node getNode(int tempday) //addExp icin
    {
        Node node = head;
        while(node.next != null)
        {
            if(node.next.data.day > tempday)
                break;
            node = node.next;
        }
        return node;
    }

    /**
     * It is a helper method. It returns the node according to given day and index.
     * If there is no node throw exception.
     * @param dayV It represents the day of Experiment's.
     * @param index It represents the index of Experiment's.
     * @return Node reference.
     */
    private Node getExp_With_Node(int dayV, int index)
    {
        boolean control = true;
        Node node = head;

        while(node != null)
        {
            if(node.data.day == dayV)
            {
                for(int i = 0; i < index; ++i)
                {
                    if(node.next.data.day == dayV && node.next != null)
                        node = node.next;
                    else
                        throw new NoSuchElementException("There is no element day : " + dayV + " index : " + index);
                }
                control = false;
                break;
            }
            else
                node = node.next;
        }
        if(control == true)
            throw new NoSuchElementException("There is no element day : " + dayV + " index : " + index);

        return node;
    }

    /**
     * It removes the first node.
     * @return Experiment reference if has have, null otherwise.
     */
    private Experiment removeFirst()
    {
        Node temp = head;
        if(head != null)
        {
            head = head.next;
            size--;
            return temp.data;
        }
        else
            return null;
    }

    /**
     * It removes the node after the given node.
     * @param node It represents the Node reference.
     * @return Experiment reference if has have, null otherwise.
     */
    private Experiment removeAfter(Node node)
    {
        Node temp = node.next;

        if(temp != null)
        {
            node.next = temp.next;
            size--;
            return temp.data;
        }
        else
            return null;

    }

    /**
     * It finds the node just before the given dayV and index.
     * @param dayV It represents the day of Experiment's.
     * @param index It represents the index of Experiment's.
     * @return Node reference
     */
    private Node getNode_to_Remove(int dayV, int index)
    {
        // crate a node ref which points the head
        Node node = head;
        if(index == -1)
        {
            index = 0;
            while(node.next != null)
            {
                if(node.next.data.day == dayV)
                {
                    for(int i = 0; i < index; ++i)
                    {
                        if (node.next.data.day == dayV)
                            node = node.next;
                    }
                    break;
                } else
                    node = node.next;
            }
        }
        else if(index >= 0)
        {
            while(node.next != null)
            {
                if(node.data.day == dayV)
                {
                    for(int i = 0; i < index; ++i)
                    {
                        if(node.next.data.day == dayV)
                            node = node.next;
                    }
                    break;
                }
                else
                    node = node.next;
            }
        }
        return node;
    }

    /**
     * This method is used to link days.
     */
    private void Linked_of_Days()
    {
        Node temp = head;
        head_day = head;
        Node temp2 = head_day;

        while(temp.next != null)
        {
            if(temp.next.data.day != temp.data.day)
            {
                temp2.next_day = temp.next;
                temp2 = temp.next;
            }
            temp = temp.next;
        }
    }
}