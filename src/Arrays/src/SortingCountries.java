import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TODO create Javadoc

public class SortingCountries {
    String[] multiWordCountries = {"Antigua and Barbuda", "Bosnia and Herzegovina", "Brunei Darussalam", "Burkina Faso", "Cabo Verde", "Cape Verde", "Central African Republic", "Congo, Democratic Republic of the", "Congo, Republic of", "Costa Rica", "Czech Republic", "Côte D'Ivoire", "Côte d'Ivoire", "Dominican Republic", "East Timor", "El Salvador", "Equatorial Guinea", "Guinea Bissau", "Korea, North", "Korea, South", "Marshall Islands", "Myanmar (Burma)", "New Zealand", "Papua New Guinea", "Saint Lucia", "San Marino", "Saudi Arabia", "Sierra Leone", "Solomon Islands", "South Africa", "South Sudan", "Sri Lanka", "St. Kitts and Nevis", "St. Lucia", "St. Vincent and the Grenadines", "São Tomé and Príncipe", "Trinidad and Tobago", "United Arab Emirates", "United Kingdom", "United States", "Vatican City", "Western Sahara"};
    private ArrayList<String> countryList = new ArrayList<String>();
    private ArrayList<String> capitalList = new ArrayList<String>();
    private ArrayList<String> populationList = new ArrayList<String>();
    private ArrayList<String> areaList = new ArrayList<String>();

    public SortingCountries() {
        readFile();
    }

    public static void main(String[] args) {
        SortingCountries sc = new SortingCountries();
        sc.readFile();
    }

    //TODO create a file-reading method
    private void readFile() {
        try {
            Scanner inFile = new Scanner(new FileInputStream(new File("Countries-Population.txt")));
            while (inFile.hasNextLine()) {
                String line = inFile.nextLine().replace(",", "");

                String[] location = getCountryFromLine(line);

                System.out.println("population: " + String.format ("%.0f",location[3])); //String.format stops scientific notation
            }
            inFile.close();
        } catch (FileNotFoundException f) {
            System.out.println("There's no such File!");
        }
    }

    public void sortAndWrite()
    {

    }
    //TODO create a methods that write to sortedByCountry.txt(not yet created) and sortedByPopulations.txt (not yet created)

    /**
     * Converts file line into a readable location name: Country, City
     *
     * @param line the line read from the file
     * @return an array containing the Country in the 0th index, and the City in 1st
     */
    public String[] getCountryFromLine(String line) {
        String[] split = line.split("\\s+");
//     System.out.println(Arrays.toString(split));
        String[] location = new String[4];
        String country;
        String capital;
        double area;
        double population;

        for (int i = 0; i < split.length; i++) {
            try {

                areaList.add(split[i]);
                populationList.add(split[i + 1]);
                String unseparatedLocation = "";
                for (int j = 0; j < i; j++) {
                    unseparatedLocation += split[j];
                    if (j < i - 1)
                        unseparatedLocation += " ";
                }
                for (String multiWordCountry : multiWordCountries) {
                    if (unseparatedLocation.startsWith(multiWordCountry)) {
                        country = multiWordCountry;
                        if (multiWordCountry.length() != unseparatedLocation.length())
                            capital = unseparatedLocation.substring(multiWordCountry.length() + 1);
                        else
                            location[1] = "No Capital";
                        return location;
                    }
                }
                location[0] = split[0];
                location[1] = unseparatedLocation.substring(split[0].length() + 1);
                return location;
            } catch (NumberFormatException e) {

            }
        }
        return null;
    }


    }
