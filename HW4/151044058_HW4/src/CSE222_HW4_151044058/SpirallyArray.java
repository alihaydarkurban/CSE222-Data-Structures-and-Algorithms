package CSE222_HW4_151044058;

/**
 * @author Ali Haydar KURBAN.
 */
public class SpirallyArray
{
    /**
     * It only calls helperTraversingSpirally method.
     * @param Array2D it is two dimensional array.
     * @return MySimpleArrayList object
     */
    private static MySimpleArrayList<Integer> traversingSpirally(int [][] Array2D)
    {
        return helperTraversingSpirally(Array2D,0,0,Array2D.length,Array2D[0].length);
    }

    /**
     * This method recursively traverse a given two dimensional array spirally clockwise starting at the top left element.
     * @param Array2D it is two dimensional array.
     * @param start_x it is a coordinate, where row is start.
     * @param start_y it is a coordinate, where column is start.
     * @param row it is length of array.
     * @param column it is length of array[0].
     * @return MySimpleArrayList object
     */
    private static MySimpleArrayList<Integer> helperTraversingSpirally(int [][] Array2D, int start_x, int start_y, int row, int column)
    {
        // Create a new MySimpleArrayList object.
        MySimpleArrayList<Integer> result = new MySimpleArrayList<Integer>();

        // Best case, it is ending of recursive call.
        if(row <= 0 || column <= 0)
            return result;

        // Other best case, it is ending of recursive call.
        // If there is only one element left, add it MySimpleArrayList object and return it.
        if(row == 1 && column == 1)
        {
            // add is a method for MySimpleArrayList. adds an element to MySimpleArrayList
            result.add(Array2D[start_x][start_y]);
            return result;
        }

        // Otherwise
        else
        {
            // It starts from the top left position and move to right.
            for(int i = 0; i < column - 1; ++i)
            {
                // adds an element to MySimpleArrayList.
                // increase the start_y position to move right.
                result.add(Array2D[start_x][start_y]);
                start_y++;
            }

            // It starts from the top right position and move to down.
            for(int i = 0; i < row - 1; ++i)
            {
                // adds an element to MySimpleArrayList.
                // increase the start_x position to move down.
                result.add(Array2D[start_x][start_y]);
                start_x++;
            }

            // If only row is bigger than one can move to left.
            if(row > 1)
            {
                // It starts from the down right position and move to left.
                for(int i = 0; i < column - 1; ++i)
                {
                    // adds an element to MySimpleArrayList.
                    // decrease the start_y position to move left.
                    result.add(Array2D[start_x][start_y]);
                    start_y--;
                }
            }

            // If only column is bigger than one can move to up.
            if(column > 1)
            {
                // adds an element to MySimpleArrayList.
                // decrease the start_x position to move up.
                for(int i = 0; i < row - 1; ++i)
                {
                    result.add(Array2D[start_x][start_y]);
                    start_x--;
                }
            }

            // If row's or column's values are 1, then recall itself with given parameter.
            if(row == 1 || column == 1)
            {
                // addArrayList adds an MySimpleArrayList to MySimpleArrayList.
                // addArrayList method uses iterator.
                // It is recursive call.
                result.addArrayList(helperTraversingSpirally(Array2D, start_x, start_y, 1, 1));
            }
            // Otherwise
            else
            {
                // addArrayList adds an MySimpleArrayList to MySimpleArrayList.
                // addArrayList method uses iterator.
                // start_x increase, because its new position is right cross pats of itself.
                // start_y increase, because its new position is right cross pats of itself.
                // row and column decrease by two, because its up row, down row, left column and right column are added MySimpleArrayList.
                // It is recursive call.
                result.addArrayList(helperTraversingSpirally(Array2D, start_x + 1, start_y + 1, row - 2, column - 2));
            }

            return result;
        }
    }

    /**
     * Tests all methods of SpirallyArray class.
     * @param args commend line argument
     */
    public static void main(String [] args)
    {
        // It is two dimensional array which is used in traversingSpirally and helpertraversingSpirally method.
        int [][] array  = {
                             {1,2,3,4},
                             {5,6,7,8},
                             {9,10,11,12},
                             {13,14,15,16}
                           };

        // Create a new MySimpleArrayList object and call traversingSpirally method.
        // traversingSpirally method returns us a MySimpleArrayList.
        MySimpleArrayList<Integer> myArrayList = traversingSpirally(array);

        // Print information.
        System.out.println("=======================================");
        System.out.println("\tPrinting Normally Given Array...");
        System.out.println("=======================================");

        // Print two dimensional array.
        for(int i = 0; i < array.length; ++i)
        {
            for(int j = 0; j < array[i].length; ++j)
            {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        // Print information
        System.out.println("=======================================");
        System.out.println("\tPrinting Spirally Given Array...");
        System.out.println("=======================================");

        // This method uses iterator.
        // Print the all list.
        myArrayList.printArray();

        System.out.println("=======================================");
    }
}