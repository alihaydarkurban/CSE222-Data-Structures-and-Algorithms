package CSE222_HW5_151044058;

import java.util.Comparator;

public class LEX implements Comparator<Integer>, Runnable
{
    private MyPriorityQueue<Integer> PQLEX;
    private int counter;


    public LEX(int counter)
    {
        this.counter = counter;
    }

    public void SetPQ(MyPriorityQueue<Integer> PQ)
    {
        PQLEX = PQ;
    }


    @Override
    public void run()
    {
        int count = 0;
        for(int i = 0; i < counter ; )
        {
            synchronized(PQLEX)
            {
                if(PQLEX.size() > 0)
                {
                    count++;
                    int Pixel = PQLEX.poll();

                    int red = (Pixel>>16) & 0xff;
                    int green = (Pixel>>8) & 0xff;
                    int blue = (Pixel) & 0xff;

                    System.out.printf("Thread2-PQLEX: [%d,%d,%d]\n",red, green, blue);
                    ++i;

                }
                else
                {
                    try {
                        PQLEX.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Thread2 worked " + count + " times");

    }

    @Override
    public int compare(Integer px1, Integer px2)
    {
        int red1 = (px1>>16) & 0xff;
        int red2 = (px2>>16) & 0xff;
        int green1 = (px1>>8) & 0xff;
        int green2 = (px2>>8) & 0xff;
        int blue1 = px1 & 0xff;
        int blue2 = px2 & 0xff;


        if(red1 < red2)
            return 1;
        else if((red1 == red2) && (green1 < green2))
            return 1;
        else if((red1 == red2) && (green1 == green2) && (blue1 < blue2))
            return 1;
        else if((red1 == red2) && (green1 == green2) && (blue1 == blue2))
            return 0;
        else
            return -1;
    }
}
