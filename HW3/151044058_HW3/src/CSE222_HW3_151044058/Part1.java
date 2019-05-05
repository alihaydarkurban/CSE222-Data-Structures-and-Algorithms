package CSE222_HW3_151044058;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author Ali Haydar KURBAN
 */
public class Part1
{
    /**
     * This is an inner class to keep element and index of my_CharArr.
     * @author Ali Haydar KURBAN
     */
    private class Cell
    {
        /**
         * To keep element of my_CharArr.
         */
        char cellValue;
        /**
         * To keep row index of my_CharArr.
         */
        int index_x;
        /**
         * To keep column index of my_CharArr.
         */
        int index_y;


        /**
         * This is a constructor to create a new Cell object.
         * @param cellValue It represents the character.
         * @param index_x It represent the row index.
         * @param index_y It represent the column index.
         */
        private Cell(char cellValue, int index_x, int index_y)
        {
            this.cellValue = cellValue;
            this.index_x = index_x;
            this.index_y = index_y;
        }
    }

    /**
     * X represents the file's row.
     */
    private int X = 0;
    /**
     * Y represents the file's row.
     */
    private int Y = 0;
    /**
     * file_name represents the file name of given file.
     */
    private String file_name;
    /**
     * my_CharArr is need to assign each element of given file to 2d Character.
     */
    private Character[][] my_CharArr;

    /**
     * This is a constructor to assign file name.
     * @param file_name It represents the file name.
     */
    public Part1(String file_name)
    {
        this.file_name = file_name;
    }

    /**
     * This method is used to print my_CharArr.
     */
    public void printTheArr()
    {
        for(int i = 0; i < X; ++i)
        {
            for(int j = 0; j < (Y / 2 + 1); j++)
            {
                System.out.print(my_CharArr[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This method is used calculate the amount of white components.
     * This method uses helper setTheArr method.
     * @return The total white components.
     * @throws IOException exception
     */
    public int calculateWhiteComponents() throws IOException
    {
        this.setTheArr(); // It fills the my_CharArr
        MyStack<Cell> myCellStack = new MyStackImplementing<Cell>(); // Create a MyStack of Cell

        int cell_counter = 0; // It is the counter which is returned.
        boolean control = false; // It is a control for if statements.

        int FLAG = 64; // It is for assign a new value for cell which is 1

        // The nested loop to search index by index on the my_CharArr
        for(int i = 0; i < X; ++i)
        {
            for(int j = 0; j <(Y / 2 + 1); ++j )
            {
                if(my_CharArr[i][j] == '1')
                {
                    cell_counter++; // Increase the cell_counter
                    FLAG++; // Assign new value of cell
                    Cell myCell_1 = new Cell((char)FLAG, i, j); // Create a new object of cell with (char)FLAG, i, j values
                    my_CharArr[i][j] = (char)FLAG; // Assign the value of FLAG to my_CharArr
                    myCellStack.push(myCell_1); // Push the myCell_1 into the myCellStack

                    // It is a while loop, it ends when the myCellStack
                    while(!myCellStack.empty())
                    {
                        // These are temporary variable to keep index of cell which is 1
                        // Peek returns the object without removing
                        int temp_i = myCellStack.peek().index_x;
                        int temp_j = myCellStack.peek().index_y;

                        // This if statement is for left control of given cell
                        // If it is 1 assign the value of FLAG to my_CharArr to prevent infinite loop
                        // Creates a Cell object of given values and pushes into the myCellStack
                        // And makes the control true, it means do not make pop.
                        if((temp_j - 1 >= 0) && my_CharArr[temp_i][temp_j - 1] == '1') //left
                        {
                            Cell myCell = new Cell((char)FLAG, temp_i, temp_j - 1);
                            my_CharArr[temp_i][temp_j - 1] = (char)FLAG;
                            myCellStack.push(myCell);
                            control = true;
                        }

                        // This if statement is for right control of given cell
                        // If it is 1 assign the value of FLAG to my_CharArr to prevent infinite loop
                        // Creates a Cell object of given values and pushes into the myCellStack
                        // And makes the control true, it means do not make pop.
                        if((temp_j + 1 < (Y / 2 + 1)) && my_CharArr[temp_i][temp_j + 1] == '1') //Right
                        {
                            Cell myCell = new Cell((char)FLAG, temp_i, temp_j + 1);
                            my_CharArr[temp_i][temp_j + 1] = (char)FLAG;
                            myCellStack.push(myCell);
                            control = true;

                        }

                        // This if statement is for down control of given cell
                        // If it is 1 assign the value of FLAG to my_CharArr to prevent infinite loop
                        // Creates a Cell object of given values and pushes into the myCellStack
                        // And makes the control true, it means do not make pop.
                        if((temp_i + 1 < X ) && my_CharArr[temp_i + 1][temp_j] == '1') //Down
                        {
                            Cell myCell = new Cell((char)FLAG, temp_i + 1, temp_j);
                            my_CharArr[temp_i + 1][temp_j] = (char)FLAG;
                            myCellStack.push(myCell);
                            control = true;

                        }

                        // This if statement is for up control of given cell
                        // If it is 1 assign the value of FLAG to my_CharArr to prevent infinite loop
                        // Creates a Cell object of given values and pushes into the myCellStack
                        // And makes the control true, it means do not make pop.
                        if((temp_i - 1 >= 0) && my_CharArr[temp_i - 1][temp_j] == '1') //Up
                        {
                            Cell myCell = new Cell((char)FLAG, temp_i - 1, temp_j);
                            my_CharArr[temp_i - 1][temp_j] = (char)FLAG;
                            myCellStack.push(myCell);
                            control = true;
                        }

                        // If value of control false, it means there is no 1 anymore so pop the Cell object from myCellStack
                        if(!control)
                            myCellStack.pop();

                        // Assign false to control
                        control = false;
                    }
                }
            }
        }
        // Return the number of white components which is asked for in homework
        return cell_counter;
    }

    /**
     * This method reads the file and founds the row and column number of file.
     * @throws IOException
     */
    private void findRowColumn() throws IOException
    {
        File my_file = new File(file_name);
        BufferedReader bufferR = new BufferedReader(new FileReader(my_file));
        String s;

        while((s = (bufferR.readLine()) )!= null)
        {
            X += 1; // It means row number
            Y = s.length(); // It means column number
        }

        bufferR.close();
    }

    /**
     * This method reads the file character by character.
     * This method fills the my_CharArr.
     * 1 and 0 are added my_CharArr.
     * This method uses helper findRowColumn method to create my_CharArr with its' size.
     * @throws IOException
     */
    private void setTheArr() throws IOException
    {
        this.findRowColumn();

        my_CharArr = new Character[X][(Y/2) + 1]; // Crate my_CharArr

        int c, i = 0 , j = 0; // Variables for index of my_CharArr

        File file = new File(file_name);
        try(Reader r = new FileReader(file))
        {
            while((c = r.read()) != -1)
            {
                if(c == 48 || c == 49) // ASCII 0 and 1
                {
                    my_CharArr[i][j] = (char)c; // Fill the my_CharArr
                    j++; // Increase the column number
                }

                else if(c == 10) // ASCII '\n' it means newline
                {
                    i++; // Increase the row number
                    j = 0; // Assign 0 to column number
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}