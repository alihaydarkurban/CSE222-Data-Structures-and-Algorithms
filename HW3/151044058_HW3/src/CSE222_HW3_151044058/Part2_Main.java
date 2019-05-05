package CSE222_HW3_151044058;

import java.io.IOException;

/**
 * @author Ali Haydar KURBAN
 */
public class Part2_Main
{
    /**
     * This method runs the other methods
     * @param args It represent the file name (program arguments).
     * @throws IOException exception
     */
    public static void main(String [] args) throws IOException
    {
        Part2 part2 = new Part2(args[0]);

        System.out.println("Result of the expression : " + part2.calculate());

        /*
            IMPORTANT TXT FILE FORMAT (There is a before and after '=' operator)
            =========================

            variable = value
            variable = value

            infix expression

         */
    }
}