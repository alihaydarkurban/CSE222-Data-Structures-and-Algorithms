package CSE222_HW3_151044058;

import java.io.IOException;

/**
 * @author Ali Haydar KURBAN
 */
public class Part1_Main
{
    /**
     * This method runs the other methods
     * @param args It represent the file name (program arguments).
     * @throws IOException exception
     */
    public static void main(String [] args) throws IOException
    {
        Part1 part1 = new Part1(args[0]);

        System.out.println("Number of “white components” : " + part1.calculateWhiteComponents());


        //!!!!!!! part1.printTheArr() IT PRINTS THE NEW MATRIX IF YOU DELETE COMMAND LINE ARGUMENT (//)
        //part1.printTheArr(); // You can see the new matrix using this method
    }
}