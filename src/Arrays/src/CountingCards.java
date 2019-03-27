import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The JFrameTest class creates the application window,
 * menu bar, File and Help menus and the Quit and Help menu items. It then adds
 * the menu items to the File, then Help menus respectively, then both menus to the
 * menu bar. It also ensures that the window will close if the user
 * clicks the 'x' button.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Daniel Voznyy, Enfei Zhang, Ivan Karlov
 * @version 1 03.20.19
 */
public class CountingCards {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("Cards.txt"));
            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine())
                lines.add(sc.nextLine());

            for (int i = 0; i < lines.size() / 4; i++) {
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
                    Arrays.setAll(suits, ArrayList::new);

                    for (int k = 0; k < 26; k += 2) {
                        String card = (hand.substring(k, k + 2));

                        //adding points and validity check
                        char face = card.charAt(0);
                        char suit = card.charAt(1);

                        if (faceToInt(face) == 0 || (switchSuit(suit) == 4) || !addCard(switchSuit(suit), suits, card)) { //if the card isn't valid
                            isValid = false;
                            break;
                        }

                        points += switchFace(face);
                    }

                    if (!isValid) {
                        System.out.println("Invalid Hand");
                        continue;
                    }

                    for (int k = 0; k < 4; k++) {
                        if (suits[k].size() < 3)
                            points += 3 - suits[k].size();
                    }
                    for (List<String> suit : suits) {
                        System.out.println(suit.toString());
                    }
                    System.out.println("This player has " + points + " points");
                }


            }


        } catch (FileNotFoundException e) {
            System.out.println("oh no");
        }

    }

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
