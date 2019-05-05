import java.io.*;
import java.util.*;


public class NLP
{
    public Word_Map wmap;
    private ArrayList<String> file_namesArray = new ArrayList<>();
    private Word_Map.Node node_class;

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */


    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir)
    {
        wmap = new Word_Map();
        File_Map fmap;
        File_Map tempFP;
        int location = 0;

        final File folder = new File(dir);

        listFilesForFolder(folder);


        for(int i = 0; i < file_namesArray.size(); ++i)
        {
            String fileName =  dir + "\\" +  file_namesArray.get(i);


            Scanner sc2 = null;
            try {
                sc2 = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (sc2.hasNextLine()) {
                Scanner s2 = new Scanner(sc2.nextLine());
                while (s2.hasNext()) {
                    String s = s2.next();
                    String word = s.trim().replaceAll("\\p{Punct}", "");

                    if(word.equals("")) continue;

                    if (!wmap.containsKey(word))
                    {
                        List<Integer> locationArray = new ArrayList<>();
                        locationArray.add(location);
                        fmap = new File_Map();
                        fmap.put(file_namesArray.get(i), locationArray);
                        wmap.put(word, fmap);

                    } else {
                        tempFP = (File_Map) wmap.get(word);

                        if (!tempFP.containsKey(file_namesArray.get(i))) {
                            List<Integer> locationArray = new ArrayList<>();
                            locationArray.add(location);
                            tempFP.put(file_namesArray.get(i), locationArray);
                            //wmap.put(word, tempFP);
                        } else {
                            List<Integer> locationArray = (List<Integer>) tempFP.get(file_namesArray.get(i));
                            locationArray.add(location);
                        }
                    }
                    location++;

                }
            }
            location = 0;

        }
    }

    private void listFilesForFolder(final File folder)
    {

        for (final File fileEntry : folder.listFiles())
        {
            if (fileEntry.isDirectory())
            {
                listFilesForFolder(fileEntry);
            }
            else
            {
                file_namesArray.add(fileEntry.getName());
            }
        }
    }

    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word)
    {
        List<String> returnArray = new ArrayList<>();
        ArrayList<String> fileName;
        File_Map tempFP;
        String res = "";

        int k = 0;
        tempFP = (File_Map) wmap.get(word);

        fileName = tempFP.fnames;

        int size = fileName.size();

        for(int i = 0; i < size; ++i)
        {
            Iterator WM_iter = wmap.iterator();

            ArrayList<Integer> index;

            index = (ArrayList<Integer>) tempFP.occurances.get(i);

            while(WM_iter.hasNext())
            {
                node_class = (Word_Map.Node) WM_iter.next();

                String key1 = node_class.getWord();

                File_Map tempFP2 = (File_Map) wmap.get(key1);

                if(tempFP2.containsKey(fileName.get(i)))
                {
                    ArrayList<String> filename2 = tempFP2.fnames;

                    int a = filename2.indexOf(fileName.get(i));

                    if(a >= 0)
                    {
                        ArrayList<Integer> index2 = (ArrayList<Integer>) tempFP2.occurances.get(a);


                        for(int j = 0; j < index.size(); ++j)
                        {
                            int conrol = index2.indexOf(index.get(j) + 1);
                            if(conrol >= 0)
                            {
                                res = word + " " +  key1;
                                if(returnArray.indexOf(res) < 0)
                                    returnArray.add(res);
                            }
                        }
                    }
                }
            }
        }

        return returnArray;
    }


    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName)
    {
        float TF;
        float IDF;

        float tfidf;

        String key;

        int countWord = 0;
        int totalWordInAFile = 0;

        int totalNumOfDocument = 0;
        int numberOfDocumentWithWord = 0;

        File_Map tempFP;

        tempFP = (File_Map) wmap.get(word);

        Iterator it_wmap = wmap.iterator();

        List<Integer> locationArray1 = (List<Integer>) tempFP.get(fileName);

        countWord = locationArray1.size();


        while(it_wmap.hasNext())
        {
            node_class = (Word_Map.Node) it_wmap.next();

            key = node_class.getWord();

            tempFP = (File_Map) wmap.get(key);

            int size = tempFP.fnames.size();

            for(int i = 0; i < size; ++i)
            {
                if(fileName.equals(tempFP.fnames.get(i)))
                {
                    List<Integer> locationArray = (List<Integer>) tempFP.get(fileName);
                    totalWordInAFile = totalWordInAFile + locationArray.size();
                }
            }
        }

        TF = (float) countWord / totalWordInAFile;

        totalNumOfDocument = file_namesArray.size();


        tempFP = (File_Map) wmap.get(word);

        numberOfDocumentWithWord = tempFP.fnames.size();


        IDF = (float) Math.log((double) totalNumOfDocument / numberOfDocumentWithWord);

        tfidf = TF * IDF;

        return tfidf;

    }

    /*Print the WordMap by using its iterator*/
    public  void printWordMap()
    {
        Iterator it = wmap.iterator();

        File_Map tempFP;

        String word;

        while(it.hasNext())
        {
            node_class = (Word_Map.Node) it.next();

            word = node_class.getWord();

            tempFP = (File_Map) wmap.get(word);

            int size = tempFP.fnames.size();

            for(int i = 0; i < size; ++i)
            {
                System.out.print( word + " ");
                List<Integer> locationArray = (List<Integer>) tempFP.get(tempFP.fnames.get(i));

                System.out.print(tempFP.fnames.get(i) + " ");
                System.out.println(locationArray.toString());
           }
        }
    }
}
