import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Phonepad {
    public static HashMap<String, List<String>> words = new HashMap<>();

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("DICT.txt"));
            long startTime = System.currentTimeMillis();
            System.out.println("Pre-encoding file");

            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                String encoded = encode(word);
                if (encoded == null)
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
            System.out.println("Type in a number to look up words for, or type \"exit\" to exit. Your number should be between 1 and 4 letters (but the program works regardless)");
            while (!(input = inputSc.nextLine().toLowerCase()).equals("exit")) {
                startTime = System.currentTimeMillis();

                try {
                    if (Long.parseLong(input) < 0) //simultaneously check if the number is a valid integer and if it is positive using long to allow for longer number lengths
                        System.out.println("Your number must be positive, try again");
                    else
                        try { //if words.get(input) returns nothing, the .toString will cause an exception, which we know means we didn't find that word
                            System.out.println(words.get(input).toString());
                        } catch (NullPointerException e) {
                            System.out.println("No words found in our dictionary for those numbers, try another one");
                        }
                } catch (NumberFormatException e) {
                    System.out.println("Your number must be a valid integer, try again");
                }

                System.out.println("Completed operation in " + (System.currentTimeMillis() - startTime) + " ms");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
