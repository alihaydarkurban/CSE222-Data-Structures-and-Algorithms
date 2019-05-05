package CSE222_HW3_151044058;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;

/**
 * @author Ali Haydar KURBAN
 */
public class Part2
{
    /**
     * It is a stack of string to keep postfix notation.
     */
    MyStack<String> myStringStack = new MyStackImplementing<String>();
    /**
     * It is stack of double to keep result of postfix notation.
     */
    MyStack<Double> myDoubleStack = new MyStackImplementing<Double>();

    /**
     * It is a constructor to assign file name.
     * @param file_name It represents the file name.
     */
    public Part2(String file_name)
    {
        this.file_name = file_name;
    }

    private String file_name;
    /**
     * It is postfix notation string.
     */
    private StringBuilder postfix;
    /**
     * It is number line of given file.
     */
    private int line_of_exp;
    /**
     * It is string array to keep all file.
     */
    private String [] strings;
    /**
     * It is string array to keep variables.
     */
    private String [] variable;
    /**
     * It is string array to keep values of variables.
     */
    private String [] values;
    /**
     * It is number of calculate sin and cos function. It is loop number.
     */
    private static final int COUNTER_FOR_SIN_COS = 20;

    /**
     *  This method uses helper infixToPostfix method.
     *  infixToPostfix returns postfix notation and then calculate the result of this postfix expression.
     *  This method uses helper calculateOp method it calculates the expression by given operator and pushes into myDoubleStack.
     * @return the result of postfix notation.
     * @throws IOException exception
     */
    public double calculate() throws IOException
    {
        double doubleValue;
        String expression = infixToPostfix(); // Assign the postfix notation a new String

        String [] tokens = expression.split("\\s+"); //It takes tokens until space comes

        for(String nextToken : tokens)
        {


            char first_char = nextToken.charAt(0);

            // It means there is a variable
            if(Character.isJavaIdentifierPart(first_char) && !isOperator(nextToken))
            {
                // Search in variable string array
                for(int i = 0; i < variable.length; ++i)
                {
                    // If find the variable push the value of the variable into myDoubleStack
                    if(nextToken.equals(variable[i]))
                    {
                        doubleValue = Double.parseDouble(values[i]);
                        myDoubleStack.push(doubleValue);
                    }
                }
            }

            // It means there is a number
            if(Character.isDigit(first_char))
            {
                doubleValue = Double.parseDouble(nextToken);
                myDoubleStack.push(doubleValue);
            }

            //It means negative number such as "-69"
            if(Character.valueOf(nextToken.charAt(0)).equals('-') && nextToken.length() > 1 && first_char != ')')
            {
                doubleValue = Double.parseDouble(nextToken);
                myDoubleStack.push(doubleValue);
            }

            // It means there is an operator
            // Call calculateOp and push result of this expression into myDoubleStack
            else if(isOperator(nextToken))
            {
                double result = calculateOp(nextToken);
                myDoubleStack.push(result);
            }
        }

        double result = myDoubleStack.pop();

        if(myDoubleStack.empty())
            return result;
        else
            throw new EmptyStackException();

    }


    /**
     * This method finds the line number of file.
     * @throws IOException
     */
    private void find_line_number() throws IOException
    {
        // Open and read file and find the number of line given file
        File my_file = new File(file_name);
        BufferedReader bufferR = new BufferedReader(new FileReader(my_file));

        int line_counter = 0;

        while ((bufferR.readLine()) != null)
            line_counter += 1;

        line_of_exp = line_counter;

        bufferR.close();
    }

    /**
     * This method uses helper find_line_number method to find line number of given file then create strings array.
     * This method uses helper assignValueVariable method to fill variable (string array) and value (string array).
     * This method fill the strings with all file.
     * Each index of strings holds a line of file.
     * @throws IOException
     */
    private void line_spectator() throws IOException
    {
        this.find_line_number(); // Find line number
        int line = line_of_exp;
        strings = new String[line]; // Create strings array
        int i = 0;

        File my_file = new File(file_name);
        BufferedReader bufferR = new BufferedReader(new FileReader(my_file));
        String st;
        // Read line by line and assign it to strings array
        while ((st = bufferR.readLine()) != null)
        {
            strings[i] = st;
            i += 1;
        }

        this.assignValueVariable(); // Fill the variable and values

        bufferR.close();
    }

    /**
     * This method fills the variable and values.
     * Create variable and values string array.
     * Their size is line_of_exp - 2 because last line is expression and before the expression there is a empty line.
     */
    private void assignValueVariable()
    {
        int i;
        //Create string array
        variable = new String[line_of_exp - 2];
        values = new String[line_of_exp - 2];

        // A loop fills the variable and values.
        for(i = 0; i < line_of_exp - 2; ++i)
        {
            String[] tokens = strings[i].split("\\s+"); //It takes tokens until space comes

            for(String nextToken : tokens)
            {
                char firstChar = nextToken.charAt(0);

                // It means variable
                if(Character.isJavaIdentifierPart(firstChar) && !Character.isDigit(firstChar))
                {
                    variable[i] = nextToken;
                }
                //It means value
                else if(Character.isDigit(firstChar))
                {
                    values[i] = nextToken;
                }
                //It means negative value such as -2 or -15
                else if(Character.valueOf(firstChar).equals('-'))
                {
                    values[i] = nextToken;
                }
            }
        }
    }

    /**
     * This method uses helper line_spectator method to reach strings which is filled with given file.
     * This method makes a postfix notation from infix notation.
     * @return It returns postfix string.
     * @throws IOException
     */
    private String infixToPostfix()  throws IOException
    {
        this.line_spectator(); // Fills all of strings, variable, value

        int line = line_of_exp;

        String infix = strings[line -1]; //Expression is in the last index of strings array
        postfix = new StringBuilder();

        String [] tokens = infix.split("\\s+"); //It takes tokens until space comes

        for(String nextToken : tokens)
        {
            char firstChar = nextToken.charAt(0);

            // It means value or variable such as "15" or "x"
            if(Character.isDigit(firstChar) || Character.isJavaIdentifierPart(firstChar))
            {
                if(!isOperator(nextToken))
                {
                    postfix.append(nextToken); //Append int the postfix string
                    postfix.append(' ');
                }
            }

            // It means next token is operator such as "+-*/()sin(cos(abs("
            if(isOperator(nextToken))
            {
                Operator(nextToken); // Call Operator method and fill the MyStringStack with operators
            }
            //It means the token is negative number such as "-23"
            //If the first character of nexToken is '-' and it length bigger than 1 it means a negative number
            else if(Character.valueOf(nextToken.charAt(0)).equals('-') && nextToken.length() > 1)
            {
                postfix.append(nextToken); //Append int the postfix string
                postfix.append(' ');
            }
        }

        // Appends the operator in my postfix string
        while(!myStringStack.empty())
        {
            String operator = myStringStack.pop();

            postfix.append(operator);
            postfix.append(' ');
        }

        // return string of postfix notation
        return postfix.toString();
    }

    /**
     * This method uses Operator_Level method to find priority of operators.
     * This method pop a operator form the myStringStack.
     * The operator is greater or equal level of priority from given op.
     * @param op It represents the operator.
     */
    private void Operator(String op)
    {
        // If myStringStack or op is ( then push into myStringStack because the smallest priority
        if(myStringStack.empty() || op.equals("("))
        {
            if(!op.equals(")"))
                myStringStack.push(op);
        }
        else
        {
            // Create a new string to keep top string of myStringStack
            String topOf_myStringStack = myStringStack.peek();

            // If the priority of op bigger than topOf_myStringStack push the op into myStringStack
            if(Operator_Level(op) > Operator_Level(topOf_myStringStack))
            {
                myStringStack.push(op);
            }
            //Otherwise
            else
            {
                // Pop all topOf_myStringStack which is bigger or equal priority than op
                while(!myStringStack.empty() && (Operator_Level(op) <= Operator_Level(topOf_myStringStack)))
                {
                    myStringStack.pop();
                    if(topOf_myStringStack.equals("(")) // End condition
                        break;

                    // Append it my postfix string
                    postfix.append(topOf_myStringStack);
                    postfix.append(' ');

                    // Assign new value to topOf_myStringStack. Because control of while loop
                    if(!myStringStack.empty())
                    {
                        topOf_myStringStack = myStringStack.peek();
                    }
                }
                // In postfix notation there is no ")"
                if(!op.equals(")"))
                    myStringStack.push(op);
            }
        }
    }

    /**
     * This method assign a integer number to priority of operator.
     * @param operator It represents the operator.
     * @return Priority of operator.
     */
    private int Operator_Level(String operator)
    {
        switch (operator)
        {
            case "(":
            case ")":
                return 1;

            case "+":
            case "-":
                return 2;

            case "*":
            case "/":
                return 3;

            case "sin(":
            case "cos(":
            case "abs(":
                return 4;
        }
        return -1;
    }

    /**
     * This method checks the given parameter is operator or not.
     * @param operator It means that operator.
     * @return true if op is an operator, otherwise return  false.
     */
    private boolean isOperator(String operator)
    {
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/") ||
                operator.equals("(") || operator.equals(")") || operator.equals("sin(") || operator.equals("cos(") ||
                operator.equals("abs(");
    }


    /**
     * This method calculates the expression with given by operator.
     * @param operation It represents the operator.
     * @return the result of expression.
     */
    private double calculateOp(String operation)
    {
        double result = 0;
        double first_num;

        double second_num;

        switch(operation)
        {
            //Addition
            case "+" :
                first_num = myDoubleStack.pop();
                second_num = myDoubleStack.pop();
                result = second_num + first_num; break;

            //Subtraction
            case "-" :
                first_num = myDoubleStack.pop();
                second_num = myDoubleStack.pop();
                result = second_num - first_num; break;

            //Multiplication
            case "*" :
                first_num = myDoubleStack.pop();
                second_num = myDoubleStack.pop();
                result = second_num * first_num; break;

            //Division
            case "/" :
                first_num = myDoubleStack.pop();
                second_num = myDoubleStack.pop();
                result = second_num / first_num; break;

            //my_sin method
            case "sin(":
                first_num = myDoubleStack.pop();
                result = my_sin((int)first_num);break;

            //my_cos method
            case "cos(":
                first_num = myDoubleStack.pop();
                result = my_cos((int)first_num);break;

            //my_abs method
            case "abs(":
                first_num = myDoubleStack.pop();
                result = my_abs(first_num);break;
        }

        return result;
    }

    /**
     * This method calculates the factorial og given n.
     * @param n It represents the number which is calculating factorial.
     * @return result of n!
     */
    private double factorial(int n)
    {
        if (n <= 1)
            return 1;
        else
            return n * factorial(n - 1);

    }

    /**
     * This method calculates the sinus with given by n.
     * @param n It represents the degree of sinus.
     * @return result of my_sin(n).
     */
    private double my_sin(int n)
    {

        double rad = n * 1.0/180.*3.1415;

        double sum = rad;

        for (int i = 1; i <= COUNTER_FOR_SIN_COS; i++)
        {
            if (i % 2 == 0)
                sum += my_pow(rad, 2*i+1) / factorial(2 * i + 1);
            else
                sum -= my_pow(rad, 2*i+1) / factorial(2 * i + 1);
        }
        return sum;
    }

    /**
     * This method calculates the cosinus with given by n.
     * @param n It represents the degree of cosinus.
     * @return result of my_cos(n).
     */
    private double my_cos(int n)
    {
        double rad = n * 1.0/180.*3.1415;
        double sum = 0;

        for (int i = 0; i < COUNTER_FOR_SIN_COS; i++)
        {
            if (i % 2 == 0)
                sum += my_pow(rad, 2 * i) / factorial(2 * i);
            else
                sum -= my_pow(rad, 2 * i) / factorial(2 * i);
        }
        return sum;
    }

    /**
     * This method calculates the pow operation such as 2^3 = 8.
     * @param base It represent the base number.
     * @param exponent It represents the exponent of base.
     * @return result of my_pow(base, exponent).
     */
    private double my_pow(double base, int exponent)
    {
        double result = 1;
        for(int i = 0; i < exponent; ++i)
            result = result * base;

        return result;
    }

    /**
     * This method makes number positive.
     * @param n It represents the number.
     * @return positive n.
     */
    private double my_abs(double n)
    {
        if(n < 0)
            return n * (-1);
        else
            return n;
    }

}