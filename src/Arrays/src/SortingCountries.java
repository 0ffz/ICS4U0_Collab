import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//TODO create Javadoc

public class SortingCountries
{
    private ArrayList<String> countryList = new ArrayList<String>();
    private ArrayList<String> capitalList = new ArrayList<String>();
    private ArrayList<String> populationList = new ArrayList<String>();
    private ArrayList<String> areaList = new ArrayList<String>();

    public SortingCountries()
    {
        readFile();
    }

    //TODO create a file-reading method
    private void readFile()
    {
        try
        {
            Scanner inFile = new Scanner(new FileInputStream(new File("Countries-Population.txt")));
            while (inFile.hasNextLine())
            {
                String line = inFile.nextLine();
                int index = line.indexOf(" ");
                System.out.println(line.substring(0,index));
                //TODO put countries, capitals, populations and areas into their respective lists
            }
            inFile.close();
        }
        catch (FileNotFoundException f)
        {
            System.out.println("There's no such File!");
        }
    }

    //TODO create a methods that write to sortedByCountry.txt(not yet created) and sortedByPopulations.txt (not yet created)

    public static void main (String[] args)
    {
        SortingCountries sc = new SortingCountries();
        sc.readFile();
    }
}
