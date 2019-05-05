import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void main(String [] args)
    {
        NLP nlp = new NLP();

        nlp.readDataset("dataset");

        //nlp.printWordMap();  IT PRINTS ALL MAP

        float tfidf;
        ArrayList<String> bigramsResult;

        Scanner scann = null;
        try {
            scann = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scann.hasNext()) {
            String str = scann.nextLine();

            String [] tokens = str.split("\\s+"); //It takes tokens until space comes

            if(tokens[0].equals("tfidf"))
            {
                tfidf = nlp.tfIDF(tokens[1],tokens[2]);

                System.out.println(tfidf);
                System.out.println();
            }
            else if(tokens[0].equals("bigram"))
            {
                bigramsResult = (ArrayList<String>) nlp.bigrams(tokens[1]);

                System.out.println(bigramsResult.toString());
                System.out.println();
            }
        }
    }
}