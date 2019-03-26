import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
                    ArrayList<String>[] suits = new ArrayList[4];

                    for (int k = 0; k < 26; k += 2) {
                        String card = (hand.substring(k, k + 1));

                        //adding points and validity check
                        char face = card.charAt(0);
                        char suit = card.charAt(1);

                        if (face == 'A')
                            points += 4;
                        else if (face == 'K')
                            points += 3;
                        else if (face == 'Q')
                            points += 2;
                        else if (face == 'J')
                            points += 1;
                        else if (!(face >= '2' && face <= '9' || face == 'T')) { //if the card isn't valid
                            isValid = false;
                            break;
                        }

                        if (suit == 'S')//TODO FIX ORDER
                            suits[0].add(card);
                        else if (suit == 'H')
                            suits[1].add(card);
                        else if (suit == 'D')
                            suits[2].add(card);
                        else if (suit == 'C')
                            suits[3].add(card);
                        else {
                            isValid = false;
                            break;
                        }

                        //organize as we go
                        cards[j][k / 2] = card;


                    }

                    if (!isValid) {
                        System.out.println("Invalid Hand");
                        continue;
                    }

                    //TODO add points depending on suitcount
                    for (int k = 0; k < 4; k++) {
                        if (suitCount[k] < 3)
                            points += 3 - suitCount[k];
                    }
                    System.out.println(hand + " has " + points + " points");
                }


            }


        } catch (FileNotFoundException e) {
        }

    }
}
