import javax.lang.model.element.NestingKind;
import java.io.*;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 This class creates the 4 Arraylists which will store the attributes of each country.
 * It reads from the file Countries-Population, distributes the attributes to their respective lists,
 * sorts the country names and populations and writes both variations of the sort into a txt file it creates.
 *
 * <h2>Course Info:<h2/>
 * ICS4U0, Krasteva, V.
 *
 * @version 2
 * @author Ivan Karlov,  addCountry by Daniel Vozny and MergeSort by Enfei Zhang
 *
 */

public class SortingCountries {
    String[] multiWordCountries = {"Antigua and Barbuda", "Bosnia and Herzegovina", "Brunei Darussalam", "Burkina Faso", "Cabo Verde", "Cape Verde", "Central African Republic", "Congo, Democratic Republic of the", "Congo, Republic of", "Costa Rica", "Czech Republic", "Côte D'Ivoire", "Côte d'Ivoire", "Dominican Republic", "East Timor", "El Salvador", "Equatorial Guinea", "Guinea Bissau", "Korea, North", "Korea, South", "Marshall Islands", "Myanmar (Burma)", "New Zealand", "Papua New Guinea", "Saint Lucia", "San Marino", "Saudi Arabia", "Sierra Leone", "Solomon Islands", "South Africa", "South Sudan", "Sri Lanka", "St. Kitts and Nevis", "St. Lucia", "St. Vincent and the Grenadines", "São Tomé and Príncipe", "Trinidad and Tobago", "United Arab Emirates", "United Kingdom", "United States", "Vatican City", "Western Sahara"};//array containing country names made up of multiple words
    private ArrayList<String> countryList = new ArrayList<String>(); //ArrayLists containing the country's names, capitals, populations and areas, respectively
    private ArrayList<String> capitalList = new ArrayList<String>();
    private ArrayList<String> populationList = new ArrayList<String>();
    private ArrayList<String> areaList = new ArrayList<String>();

    public static void main(String[] args) {
        SortingCountries sc = new SortingCountries();
        sc.readFile();
        sc.sortAndWrite();
    }

    private void readFile() {
        try {
            Scanner inFile = new Scanner(new FileInputStream(new File("Countries-Population.txt")));
            while (inFile.hasNextLine()) {
                String line = inFile.nextLine().replace(",", "");//creates string to contain the line and removes commas from area and population

                addCountry(line);
            }
            inFile.close();
        } catch (FileNotFoundException f) {
            System.out.println("There's no such File!");
        }
    }

    public void sortAndWrite() {
        ArrayList<Integer> tempPosList = new ArrayList<>(); //ArrayList containing the positions of countryList elements within countryList
        ArrayList<Integer> tempPopList = new ArrayList<>(); //ArrayList containing Integer values of each country's population
        Comparator<String> comp = new Comparator<String>() { //creates Comparator object to be used in the sort method
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        };
        Comparator<Integer> comp2 = new Comparator<Integer>() { //creates Comparator object for second sort
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer.compareTo(t1);
            }
        };

        MergeSort.sort(countryList, populationList, comp);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("sortedByCountry.txt"));
            for (int i = 0; i < countryList.size(); i++) {
                writer.println(countryList.get(i) + "\t\t\t" + populationList.get(i)); //writes sorted data into "sortedByCountry.txt"
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OH NOOOOOO");
        }

        for (int x = 0; x < populationList.size(); x++) {
            tempPopList.add(Integer.parseInt(populationList.get(x)));
            tempPosList.add(x);
        }

        MergeSort.sort(tempPopList, tempPosList, comp2);

        for (int i = 0; i < tempPopList.size(); i++) {
            String population = String.valueOf(tempPopList.get(i));
            String temp = countryList.get(i);
            populationList.set(i, population); //adds populations to populationList in sorted order
            countryList.set(i, countryList.get(tempPosList.get(i)));
            countryList.set(tempPosList.get(i), temp);

            System.out.println(tempPosList);
            System.out.println(countryList.size());
            System.out.println(countryList);
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("sortedByPopulation.txt"));
            for (int i = 0; i < countryList.size(); i++) {
                writer.println(countryList.get(i) + "\t\t\t" + populationList.get(i)); //writes sorted data into "sortedByPopulation.txt"
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OH NOOOOOO");
        }
    }

    /**
     * Converts file line into a readable location name: Country, City
     *
     * @param line the line read from the file
     * @return an array containing the Country in the 0th index, and the City in 1st
     */
    public void addCountry(String line) {
        String[] split = line.split("\\s+");
//     System.out.println(Arrays.toString(split));

        areaList.add(split[split.length - 2]); //before last item in array
        populationList.add(split[split.length - 1]); //last item in array

        String unseparatedLocation = "";
        for (int j = 0; j < split.length - 2; j++) { //combine all elements of array except the last two
            unseparatedLocation += split[j];
            if (j < split.length - 3) //add space between parts of the name, except for the last word
                unseparatedLocation += " ";
        }

        for (String multiWordCountry : multiWordCountries) { //check through every multi word country name
            if (unseparatedLocation.startsWith(multiWordCountry)) { //if the location name starts with the multi word country name
                countryList.add(multiWordCountry);
                if (multiWordCountry.length() != unseparatedLocation.length())
                    capitalList.add(unseparatedLocation.substring(multiWordCountry.length() + 1));
                else
                    capitalList.add("No Capital");
                return;
            }
        }
        //add first word in split list
        countryList.add(split[0]);
        //add everything except first word (and the space that follows it) in the unseparated list
        capitalList.add(unseparatedLocation.substring(split[0].length() + 1));
    }
}
