package CSE222_HW5_151044058;

import java.util.Comparator;

public class EUC implements Comparator<Integer>, Runnable
{
    private MyPriorityQueue<Integer> PQEUC;
    private int counter;

    public EUC(int counter)
    {
        this.counter = counter;
    }


    public void SetPQ(MyPriorityQueue<Integer> PQ)
    {
        PQEUC = PQ;
    }
    @Override
    public void run()
    {
        int count = 0;
        for(int i = 0; i < counter ; )
        {
            synchronized(PQEUC)
            {
                if(PQEUC.size() >0)
                {
                    count++;

                    int Pixel = PQEUC.poll();

                    int red = (Pixel >> 16) & 0xff;
                    int green = (Pixel >> 8) & 0xff;
                    int blue = (Pixel) & 0xff;

                    System.out.printf("Thread3-PQEUC: [%d,%d,%d]\n", red, green, blue);
                    ++i;

                }

                else
                {
                    try {
                        PQEUC.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Thread3 worked " + count + " times");

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

        double comp1 = Math.sqrt((red1*red1 + green1*green1 + blue1*blue1));
        double comp2 = Math.sqrt((red2*red2 + green2*green2 + blue2*blue2));

        if(comp1 < comp2)
            return 1;
        else if(comp1 == comp2)
            return 0;
        else
            return -1;
    }
}
