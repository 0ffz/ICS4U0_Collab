import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * This class shows all possible words for phone number button codes using a dictionary
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Daniel Voznyy
 * @version 1 03.20.19
 */
public class Phonepad {
    public static HashMap<String, List<String>> words = new HashMap<>();

    /**
     * The main algorithm for converting and searching for words
     *
     * @param args command line arguments for main method
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("DICT.txt"));
            long startTime = System.currentTimeMillis();
            System.out.println("Pre-encoding file");

            //add all the possible words for each "encoded" number
            while (sc.hasNextLine()) { //for every word in the dictionary
                String word = sc.nextLine();
                String encoded = encode(word);
                if (encoded == null) //if word can't be formatted into phone number codes, don't add it
                    continue;

                //if the words map doesn't already contain a list of words for a number, add a new ArrayList containing our word
                if (words.putIfAbsent(encoded, new ArrayList<String>(Collections.singletonList(word))) != null) {
                    //otherwise, add our word to that existing list
                    words.get(encoded).add(word);
                }
            }
            sc.close();

            //display the time difference between the start and current time
            System.out.println("Encoding finished in " + (System.currentTimeMillis() - startTime) + " ms");

            Scanner inputSc = new Scanner(System.in);
            String input;
            //instructions for user
            System.out.println("Type in a number to look up words for, or type \"exit\" to exit. Your number should be between 1 and 4 letters (but the program works regardless)");
            //keep asking for numbers until user types "exit"
            while (!(input = inputSc.nextLine().toLowerCase()).equals("exit")) {
                startTime = System.currentTimeMillis();

                try { //if words.get(input) returns nothing, the .toString will cause an exception, which we know means we didn't find that word
                    System.out.println(words.get(input).toString());
                } catch (NullPointerException e) {
                    System.out.println("No words found in our dictionary for that code, try another one");
                }

                System.out.println("Completed operation in " + (System.currentTimeMillis() - startTime) + " ms");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encodes each word by reading it letter by letter and assigning a number to it
     *
     * @param word the word to be encoded
     * @return the encoded word; null if the word can't be put into phone button form
     */
    public static String encode(String word) {
        //better than constantly adding to a String object, since the String creates a copy of itself when doing so
        StringBuilder encoded = new StringBuilder();
        for (char c : word.toCharArray()) {
            //check what number the current letter belongs to
            if (c >= 'a' && c <= 'c')
                encoded.append(2);
            else if (c >= 'd' && c <= 'f')
                encoded.append(3);
            else if (c >= 'g' && c <= 'i')
                encoded.append(4);
            else if (c >= 'j' && c <= 'l')
                encoded.append(5);
            else if (c >= 'm' && c <= 'o')
                encoded.append(6);
            else if (c >= 'p' && c <= 's')
                encoded.append(7);
            else if (c >= 't' && c <= 'v')
                encoded.append(8);
            else if (c >= 'w' && c <= 'z')
                encoded.append(9);
            else //if the word contains any other characters, we consider it invalid
                return null;
        }
        return encoded.toString();
    }
}
