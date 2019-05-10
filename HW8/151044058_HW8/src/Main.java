import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    public static void main(String [] args)
    {
        Edge edge;

        MyArrayList<Edge> myArr = new MyArrayList<>();

        DirectedGraph DG;

        final boolean isDirected = true;

        int numV;

        Scanner scann = null;

        try
        {
            scann = new Scanner(new File("input.txt"));
        }

        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        while(scann.hasNext())
        {
            String str = scann.nextLine();

            String [] tokens = str.split("\\s+"); //It takes tokens until space comes

            edge = new Edge(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));

            myArr.add(edge);

        }

        edge = myArr.get(0);

        numV = edge.getSource();

        DG = new DirectedGraph(numV, isDirected);

        for(int i = 1; i < myArr.getSize(); ++i)
        {
            DG.insert(myArr.get(i));
        }

        MyArrayList<MyArrayList<Integer>> temp = new MyArrayList<>();

        for(int i = 1; i < myArr.getSize(); ++i)
        {
            Edge e = myArr.get(i);

            temp.add(DG.DFS(e.getSource()));

        }

        MyArrayList<MyArrayList<Integer>> real = new MyArrayList<>();
        MyArrayList<Integer> starts = new MyArrayList<>();


        for(int i = 0; i < temp.getSize(); ++i)
        {
            int start = temp.get(i).get(0);
            int flag = 0;

            starts.add(start);

            if(i == 0)
                real.add(temp.get(i));

            else
            {
                for(int j = 0; j < starts.getSize() - 1; ++j)
                {
                    if(starts.get(j) == start)
                    {
                        flag = -1;
                    }
                }

                if(flag == 0)
                    real.add(temp.get(i));
            }
        }

/*
        System.out.println("Deep First Search");
        System.out.println("=================");

        for(int k = 0; k < real.getSize(); ++k)
          System.out.println(real.get(k).toString());
*/
        int count = numOfPopular(real,numV);

        System.out.println(count);

    }

    private static int numOfPopular(MyArrayList<MyArrayList<Integer>> myArr, int numV)
    {
        int count = 0;

        MyArrayList<Integer> firstArray = myArr.get(0);

        for(int i = 0; i < firstArray.getSize(); ++i)
        {
            int temp_count = 0;

            int controlNum = firstArray.get(i);

            for(int j = 0; j < myArr.getSize(); ++j)
            {
                MyArrayList<Integer> temp = myArr.get(j);

                if(temp.contain(controlNum) && temp.getIndex(controlNum) != 0)
                    temp_count++;
            }

            if(temp_count == numV - 1)
                count++;
        }

        return count;
    }
}