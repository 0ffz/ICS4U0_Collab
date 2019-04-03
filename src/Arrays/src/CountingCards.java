import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class reads a file containing a list of card decks for four players. It then determines a score for each hand
 * and returns a sorted list of cards inside them.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author 70% Daniel Voznyy, 30% Enfei Zhang
 * @version 1 03.20.19
 */
public class CountingCards {
    /**
     * The main algorithm for sorting the cards
     *
     * @param args command line arguments for main method
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("Cards.txt"));
            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine())
                lines.add(sc.nextLine());

            //since we know the cards are formatted in sets of four, this allows us to loop through each set of cards
            for (int i = 0; i < lines.size() / 4; i++) {
                //for each player's hand in the set
                for (int j = 0; j < 4; j++) {
                    String hand = lines.get(i + j);
                    //validity check
                    if (hand.length() != 26) {
                        System.out.println("Invalid Hand");
                        continue;
                    }

                    int points = 0;
                    boolean isValid = true;

                    //suits organized by S Spades, H Hearts, D Diamonds, C Clubs
                    List<String>[] suits = new ArrayList[4];
                    Arrays.setAll(suits, ArrayList::new); //initialize the ArrayLists

                    //for every card in the list (cards are 2 characters long so we do k+=2)
                    for (int k = 0; k < 26; k += 2) {
                        String card = (hand.substring(k, k + 2));


                        char face = card.charAt(0);
                        char suit = card.charAt(1);


                        if (faceToInt(face) == 0 || //faceToInt returns 0 if invalid face
                                (switchSuit(suit) == 4) ||  //switchSuit returns 0 if suit is invalid
                                !addCard(switchSuit(suit), suits, card)) { //addCard adds the card if it is valid and returns false if the card is invalid (specifically for if it's repeated)
                            isValid = false; //we can't break twice so we need a variable so that we don't add points outside this loop
                            break; //don't go through every card if we know the hand is invalid
                        }

                        //add points for face
                        points += switchFace(face);
                    }

                    if (!isValid) {
                        System.out.println("Invalid Hand");
                        continue; //don't go any further
                    }

                    //for each suit, add a different number of points
                    for (int k = 0; k < 4; k++) {
                        if (suits[k].size() < 3)
                            points += 3 - suits[k].size(); //we can do this because suits are stored in a descending order of points
                    }

                    //print out each suit
                    for (List<String> suit : suits) {
                        System.out.println(suit.toString());
                    }
                    //print out number of points for hand
                    System.out.println("This player has " + points + " points");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds a card into its respective suit and keeps them sorted in descending order
     *
     * @param j     the suit this card belongs to
     * @param suits the current list of cards that have been sorted into appropriate suits
     * @param card  the name of the card we want to add into suits
     * @return whether the card was successfully added
     */
    public static boolean addCard(int j, List<String>[] suits, String card) {
        for (int i = 0; i < suits[j].size(); i++) {
            int listCardValue = faceToInt(suits[j].get(i).charAt(0));
            int cardValue = faceToInt(card.charAt(0));
            if (listCardValue < cardValue) {
                suits[j].add(i, card); //add card at current position
                return true;
            } else if (listCardValue == cardValue)
                return false; //lets outside method know the suit was invalid (because it has multiple of the same card)
        }
        suits[j].add(card); //add card in the end if we've gone through the entire loop and found no other cards to match it with
        return true;
    }

    /**
     * Uses the switchFace() method to return an int value for any face, not just from Jack to Ace
     *
     * @param face a card's face
     * @return integer value of face (for counting points)
     */
    public static int faceToInt(char face) {
        if (face >= '2' && face <= '9')
            return face;
        else if (face == 'T')
            return '9' + 1;
        else if (switchFace(face) == 0) //if face is invalid
            return 0;
        else
            return '9' + 1 + switchFace(face);
    }

    /**
     * @param face a face of a card that is between Jack and Ace
     * @return an integer value for the face. 0 if the face is invalid
     */
    public static int switchFace(char face) {
        if (face == 'A')
            return 4;
        else if (face == 'K')
            return 3;
        else if (face == 'Q')
            return 2;
        else if (face == 'J')
            return 1;
        return 0;
    }

    /**
     * @param suit the suit of a card
     * @return an integer value of the suit (for counting ponits); 4 if the suit is invalid
     */
    public static int switchSuit(char suit) {
        if (suit == 'S')
            return 0;
        else if (suit == 'H')
            return 1;
        else if (suit == 'D')
            return 2;
        else if (suit == 'C')
            return 3;
        return 4;
    }
}
