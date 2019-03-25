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
            String[][] cards = new String[4][13];
            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine())
                lines.add(sc.nextLine());

            for (int i = 0; i < lines.size() / 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int points = 0;
                    //suits organized by D Diamonds, H Hearts, C Clubs, S Spades
                    int[] suitCount = new int[4];

                    for (int k = 0; k < 26; k += 2) {
                        String card = (lines.get(i + j).substring(k, k+1));
                        cards[j][k / 2] = card;
                        
                        char face = card.charAt(0);
                        char suit = card.charAt(1);
                        
                        if(face == 'A')
                            points += 4;
                        else if(face == 'K')
                            points += 3;
                        else if(face == 'Q')
                            points += 2;
                        else if(face == 'J')
                            points += 1;

                        if(suit == 'D')//TODO adds to suitcount based on what suit it is
                            suitCount[0] ++;
                        
                    }
                    //TODO add points depending on suitcount
                    if(suitCount == 1)

                }
                
                
            }


        } catch (FileNotFoundException e) {
        }

    }
}
