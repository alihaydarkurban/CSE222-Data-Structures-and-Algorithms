package CSE222_HW5_151044058;

import java.util.Comparator;

public class BMX implements Comparator<Integer>, Runnable
{
    private MyPriorityQueue<Integer> PQBMX;
    private int counter;

    public BMX(int counter)
    {
        this.counter = counter;
    }


    public void SetPQ(MyPriorityQueue<Integer> PQ)
    {
        PQBMX = PQ;
    }

    private char[] decimal_to_binary(int number)
    {
        int count;

        String temp;
        String binary_string = "";

        temp = Integer.toBinaryString(number);

        count = temp.length();

        if(count < 8)
        {
            for(int i = 0; i < 8 - count; ++i)
                binary_string = binary_string + "0";
        }

        binary_string = binary_string + temp;

        char[] stringToCharArray = binary_string.toCharArray();

        return stringToCharArray;
    }

    private long binary_to_decimal(String s)
    {
        long decimalValue = Integer.parseInt(s, 2);

        return decimalValue;
    }

    @Override
    public void run()
    {
        int count = 0;
        for(int i = 0; i < counter ; )
        {
            synchronized(PQBMX)
            {
                if(PQBMX.size() > 0)
                {
                    count++;

                    int Pixel = PQBMX.poll();

                    int red = (Pixel>>16) & 0xff;
                    int green = (Pixel>>8) & 0xff;
                    int blue = (Pixel) & 0xff;

                    System.out.printf("Thread4-PQBMX: [%d,%d,%d]\n",red, green, blue);
                    ++i;


                }

                else
                {

                    try {
                        PQBMX.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        System.out.println("Thread4 worked " + count + " times");

    }

    @Override
    public int compare(Integer px1, Integer px2)
    {
        long number1, number2;

        char [] red1_array, red2_array, green1_array, green2_array, blue1_array, blue2_array;

        char [] long_intChar1 = new char[24];
        char [] long_intChar2 = new char[24];

        String s1 = "";
        String s2 = "";

        int red1 = (px1>>16) & 0xff;
        int red2 = (px2>>16) & 0xff;
        int green1 = (px1>>8) & 0xff;
        int green2 = (px2>>8) & 0xff;
        int blue1 = px1 & 0xff;
        int blue2 = px2 & 0xff;

        red1_array = decimal_to_binary(red1);
        red2_array = decimal_to_binary(red2);

        green1_array = decimal_to_binary(green1);
        green2_array = decimal_to_binary(green2);

        blue1_array = decimal_to_binary(blue1);
        blue2_array = decimal_to_binary(blue2);


        for(int i = 0; i < 8; ++i)
        {
            long_intChar1[3 * i] = red1_array[i];
            long_intChar1[3 * i + 1] = green1_array[i];
            long_intChar1[3 * i + 2] = blue1_array[i];

            long_intChar2[3 * i] = red2_array[i];
            long_intChar2[3 * i + 1] = green2_array[i];
            long_intChar2[3 * i + 2] = blue2_array[i];
        }


        for(int i = 0; i < 24; ++i)
        {
            s1 = s1 + long_intChar1[i];
            s2 = s2 + long_intChar2[i];
        }

        number1 = binary_to_decimal(s1);
        number2 = binary_to_decimal(s2);


        if(number1 < number2)
            return 1;
        else if(number1 == number2)
            return 0;
        else
            return -1;

    }
}