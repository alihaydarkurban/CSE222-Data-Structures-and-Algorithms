package CSE222_HW2_151044058;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Driver class that tests all methods.
 * @author Ali Haydar KURBAN
 */
public class Main
{
    /**
     * Tests all methods of ExperimentList class.
     * @param args string to be printed.
     */
    public static void main(String[] args)
    {

        DateFormat dateFormat = new SimpleDateFormat();
        Date date = new Date();
        String time = dateFormat.format(date);

        int ELEMENT_SIZE = 8;

        ExperimentList myLinkedList = new ExperimentList();

        // Create Experiment and store the in an array to use easily
        ExperimentList.Experiment[] exp = new ExperimentList.Experiment[ELEMENT_SIZE];
        exp[0] = new ExperimentList.Experiment("A",1,time,true,9.1f);
        exp[1] = new ExperimentList.Experiment("B",4,time,true,7.2f);
        exp[2] = new ExperimentList.Experiment("C",3,time,false,9.3f);
        exp[3] = new ExperimentList.Experiment("D",2,time,true,8.4f);
        exp[4] = new ExperimentList.Experiment("E",3,time,false,7.5f);
        exp[5] = new ExperimentList.Experiment("F",4,time,true,19.6f);
        exp[6] = new ExperimentList.Experiment("G",2,time,false,2.7f);
        exp[7] = new ExperimentList.Experiment("H",1,time,false,3.8f);


        // With a for loop add all exp in to myLinkedList
        for(int i = 0; i < ELEMENT_SIZE; ++i)
            myLinkedList.addExp(exp[i]);

        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After addExp=======");
        System.out.println(myLinkedList.toString()); // Write the myLinkedList on the screen
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After printLinkedDay=======");
        System.out.println("======================================================================================================");
        myLinkedList.print_LinkedDay();
        System.out.println("======================================================================================================");
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After getExp=======");
        System.out.println("======================================================================================================");
        System.out.println("Day : " + 1 + " Index : " + 1 + " ---> " + myLinkedList.getExp(1,1).toString());
        System.out.println("Day : " + 3 + " Index : " + 0 + " ---> " + myLinkedList.getExp(3,0).toString());
        System.out.println("======================================================================================================");
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After setExp=======");
        System.out.println("======================================================================================================");
        ExperimentList.Experiment set_element1 = new ExperimentList.Experiment("I", 2,time,true, 7.9f);
        System.out.println("The Element of setExp is : " + set_element1.toString());
        System.out.println("The Old Experiment ---> " + myLinkedList.setExp(2,1,set_element1));
        System.out.println(myLinkedList.toString()); // Write the myLinkedList on the screen
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After removeExp=======");
        System.out.println("======================================================================================================");
        System.out.println("The Removed Experiment ---> " + myLinkedList.removeExp(4,1));
        System.out.println(myLinkedList.toString()); // Write the myLinkedList on the screen
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After printLinkedDay=======");
        System.out.println("======================================================================================================");
        myLinkedList.print_LinkedDay();
        System.out.println("======================================================================================================");
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After listExp=======");
        System.out.println("======================================================================================================");
        System.out.println("1st Day's True Completed");
        System.out.println(myLinkedList.listExp(1).toString()); // Write the newList on the screen
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After removeDay=======");
        System.out.println("======================================================================================================");
        System.out.println("Remove 3rd Days");
        myLinkedList.removeDay(3);
        System.out.println(myLinkedList.toString()); // Write the myLinkedList on the screen
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After printLinkedDay=======");
        System.out.println("======================================================================================================");
        myLinkedList.print_LinkedDay();
        System.out.println("======================================================================================================");
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After orderDay=======");
        System.out.println("======================================================================================================");
        System.out.println("Order 2nd Days");
        myLinkedList.orderDay(2);
        System.out.println(myLinkedList.toString()); // Write the myLinkedList on the screen
        System.out.println("======================================================================================================");
        System.out.println("\t\t\t\t\t=======After orderExperiments=======");
        System.out.println(myLinkedList.orderExperiments().toString());

    }
}