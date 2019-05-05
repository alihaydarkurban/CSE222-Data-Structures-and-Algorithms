package CSE222_HW4_151044058;

public class part2
{
    private static boolean part2Method(int A[], int sum)
    {
        int startArray = 0;
        int endArray = A.length - 1;


        for(int i = 0; i < A.length; ++i) // A.length - 1, because we want two different numbers addition.
        {
            if (A[startArray] + A[endArray] == sum)
                return true;
            else if (A[startArray] + A[endArray] < sum)
                startArray++;
            else if(A[startArray] + A[endArray] > sum)
                endArray--;
        }
        return false;
    }

    public static void main(String[] args)
    {

        int A[] = {1, 3, 7, 15, 32, 37, 45};

        int x = 3;

        if (part2Method(A, x))
            System.out.print("Array has " + x + " with two elements addition.");
        else
            System.out.print("Array doesn't have " + x + " with two elements addition.");

    }
}