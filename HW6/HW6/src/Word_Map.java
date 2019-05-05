import java.util.*;

public class Word_Map implements Map, Iterable
{

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];


    private int countOfKeys = 0; //It means size

    private Node head = null;
    private Node tail = null;


    public Word_Map()
    {
        this.table = new Node[INITCAP];
    }

    @Override
    public Iterator iterator()
    {
        return new Word_MapIterator();
    }

    private class Word_MapIterator implements Iterator
    {
        private Node nextItem;
        private Node lastItemReturned;

        private Word_MapIterator()
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
    }

    static class Node
    {
        // complete this class according to the given structure in homework definition
        private Node next = null;
        private String word;
        private File_Map FP;

        public Node(String word, File_Map FP)
        {
            this.FP = FP;
            this.word = word;
        }

        public String getWord()
        {
            return word;
        }

    }

    @Override
    public int size()
    {
        return countOfKeys;
    }

    @Override
    public boolean isEmpty()
    {
        return countOfKeys == 0;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key)
    {
        Object myKey = get(key);

        if(myKey == null)
            return false;

        else
            return true;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value)
    {
        Word_MapIterator WM_iter = (Word_MapIterator)iterator();

        Object myKey;

        while(WM_iter.hasNext())
        {
            myKey = WM_iter.next();

            if(((Node) myKey).FP.equals(value))
                return true;
        }

        return false;
    }

    @Override
    public Object get(Object key)
    {
        int index = find(key);

        if(table[index] != null)
            return table[index].FP;

        else
            return null;
    }

    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value)
    {
        //System.out.println("PUT WMP : " + key);
        int index = find(key);
        if(table[index] == null)
        {
            table[index] = new Node((String) key, (File_Map) value);
            ++countOfKeys;
            double loadFactor = (double) countOfKeys / table.length;

            if(head == null)
            {
                head = table[index];
                tail = head;
                tail.next = null;
            }

            else
            {
                tail.next = table[index];
                tail = tail.next;
                tail.next = null;
            }

            if(loadFactor > LOADFACT)
                rehasing();

            return null;
        }

        Object oldValue = table[index].FP;
        table[index].FP = (File_Map) value;

        if(head == null)
        {
            head = table[index];
            tail = head;
            tail.next = null;
        }

        else
        {
            tail.next = table[index];
            tail = tail.next;
            tail.next = null;
        }

        return oldValue;
    }

    @Override
    public void putAll(Map m)
    {
        Iterator mapIter = m.keySet().iterator();

        while(mapIter.hasNext())
        {
            Object myKey = mapIter.next();
            Object myValue = m.get(myKey);

            this.put(myKey,myValue);
        }
    }

    @Override
    public void clear()
    {
        for(int i = 0; i < this.size(); ++i)
        {
            this.put(null,null);
        }
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet()
    {
        Word_MapIterator WM_iter = (Word_MapIterator)iterator();

        Set<Object> myKeySet = new HashSet<>();

        Object myKey;

        while(WM_iter.hasNext())
        {
            myKey = WM_iter.next();
            myKeySet.add(((Node) myKey).word);
        }

        return myKeySet;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values()
    {
        Node key;
        Word_MapIterator WM_iter = (Word_MapIterator)iterator();

        ArrayList<String> values = new ArrayList<>();

        while(WM_iter.hasNext())
        {
            key = WM_iter.next();
            values.add(key.word);
        }

        return values;
    }

    private int find(Object key)
    {
        int index = key.hashCode() % table.length;

        if(index < 0)
            index = index + table.length;

        while((table[index] != null) && (!key.equals(table[index].word)))
        {
            index++;
            if(index >= table.length)
                index = 0;
        }

        return index;
    }

    private void rehasing()
    {
        Node [] oldTable = table;

        table = new Node[2 * oldTable.length + 1];

        countOfKeys = 0;

        head = null;
        tail = null;

        for(int i = 0; i < oldTable.length; ++i)
        {
            if(oldTable[i] != null)
            {
                put(oldTable[i].word, oldTable[i].FP);
            }
        }
    }






    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }
}
