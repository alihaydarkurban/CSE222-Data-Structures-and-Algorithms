package CSE222_HW5_151044058;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Pixels implements Runnable
{
    private int W;
    private int H;
    private int [][] PixelsArray;
    private String pathname;

    private int red;
    private int green;
    private int blue;

    public Pixels(String pathname)
    {
        this.pathname = pathname;
    }

    @Override
    public void run()
    {
        this.readPng();

        LEX lex = new LEX(W*H);
        EUC euc = new EUC(W*H);
        BMX bmx = new BMX(W*H);

        MyPriorityQueue<Integer> PQLEX = new PQ_Implemantation<>(lex);
        MyPriorityQueue<Integer> PQEUC = new PQ_Implemantation<>(euc);
        MyPriorityQueue<Integer> PQBMX = new PQ_Implemantation<>(bmx);

        Thread thread2 = null;
        Thread thread3 = null;
        Thread thread4 = null;

        int counter = 0;
        int flag = 0;

        for(int y = 0; y < H; ++y)
        {
            for(int x = 0; x < W; ++x)
            {
                synchronized(PQLEX)
                {
                    PQLEX.offer(PixelsArray[y][x]);
                    PQLEX.notify();
                }

                synchronized(PQEUC)
                {
                    PQEUC.offer(PixelsArray[y][x]);
                    PQEUC.notify();

                }
                synchronized(PQBMX)
                {
                    PQBMX.offer(PixelsArray[y][x]);
                    PQBMX.notify();
                }
                counter++;

                red = (PixelsArray[y][x]>>16) & 0xff;

                green = (PixelsArray[y][x]>>8) & 0xff;

                blue = PixelsArray[y][x] & 0xff;

                System.out.printf("Thread 1: [%d, %d, %d]\n",red, green, blue);

                if(counter == 100 && flag == 0)
                {
                    System.out.println("//... etc inserting all the way to at least the first 100 pixels..");
                    flag++;

                    lex.SetPQ(PQLEX);
                    euc.SetPQ(PQEUC);
                    bmx.SetPQ(PQBMX);

                    thread2 = new Thread(lex);
                    thread3 = new Thread(euc);
                    thread4 = new Thread(bmx);
                    thread2.start();
                    thread3.start();
                    thread4.start();
                }

            }
        }

        System.out.println("\nThread1 worked " + counter + " times");
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void readPng()
    {
        BufferedImage img = null;
        File f = null;

        try
        {
            f = new File(pathname);
            img = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

        W = img.getWidth();
        H = img.getHeight();

        PixelsArray = new int [H][W];

        for(int y = 0; y < H; ++y)
        {
            for(int x = 0; x < W; ++x)
            {
                int p = img.getRGB(x,y);

                PixelsArray[y][x] = p;
            }
        }
    }
}