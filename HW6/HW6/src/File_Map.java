import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
    ArrayList<String> fnames;
    ArrayList<List<Integer>> occurances;


    public File_Map()
    {
        fnames = new ArrayList<>();
        occurances = new ArrayList<>();
    }

    @Override
    public int size()
    {
        return fnames.size();
    }

    @Override
    public boolean isEmpty()
    {
        return fnames.size() == 0;
    }

    @Override
    public boolean containsKey(Object key)
    {
        Iterator fname_iter = fnames.iterator();

        Object myKey;

        while(fname_iter.hasNext())
        {
            myKey = fname_iter.next();

            if(myKey.equals(key))
                return true;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value)
    {
        int index = 0;

        Iterator fname_iter = fnames.iterator();

        Object myValue;

        while(fname_iter.hasNext())
        {
            fname_iter.next();

            myValue = occurances.get(index);

            if(myValue.equals(value))
                return true;

            ++index;
        }

        return false;
    }

    @Override
    public Object get(Object key)
    {

        int index = 0;
        if(!containsKey(key))
        {
            return null;
        }

        Iterator fname_iter = fnames.iterator();

        Object myKey;

        while(fname_iter.hasNext())
        {
            myKey = fname_iter.next();

            if(myKey.equals(key))
            {
                return occurances.get(index);
            }
            index++;
        }

        return null;
    }

    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value)
    {
        fnames.add((String) key);
        occurances.add((List<Integer>) value);

        return null;
    }
    @Override
    public Object remove(Object key)
    {
        if(!containsKey(key))
            return null;

        Iterator fname_iter = fnames.iterator();

        Object myKey;
        Object olValue = null;

        int index = 0;

        while(fname_iter.hasNext())
        {
            myKey = fname_iter.next();

            if(myKey.equals(key))
            {
                olValue = fnames.get(index);
                fnames.remove(index);
                occurances.remove(index);
            }
        }
        return olValue;
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
        fnames.clear();
        occurances.clear();
    }

    @Override
    public Set keySet()
    {
        Set<Object> myKeySet = new HashSet<>();

        Iterator fname_iter = fnames.iterator();

        Object myKey;

        while(fname_iter.hasNext())
        {
            myKey = fname_iter.next();
            myKeySet.add(myKey);
        }
        if(myKeySet.size() > 0)
            return myKeySet;
        else
            return null;
    }

    @Override
    public Collection values()
    {
        ArrayList<List<Integer>> values = new ArrayList<>();

        for(int i = 0; i < occurances.size(); ++i)
        {
            values.add(occurances.get(i));
        }

        return values;
    }

    @Override
    public Set<Entry> entrySet()
    {
        Map temp_map = new HashMap<>();

        for(int i = 0;  i < fnames.size();  i++)
        {
            temp_map.put(fnames.get(i), occurances.get(i));

        }
        Set<Entry> resultSet = temp_map.entrySet();

        return resultSet;
    }
}
