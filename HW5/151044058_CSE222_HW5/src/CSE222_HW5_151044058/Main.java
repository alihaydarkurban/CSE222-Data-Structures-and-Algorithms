package CSE222_HW5_151044058;

public class Main
{
    public static void main(String [] args)
    {
        Thread thread1 = new Thread(new Pixels(args[0]));
        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
