import java.util.Arrays;

/**
 * The class sorts an int array using the Comb sort algorithm, and then prints the sorted array in order, least to greatest.
 *
 * <h2>Course Info:<h2/>
 * ICS4U0, Krasteva, V.
 *
 * @author Ivan Karlov, created using the Comb sort pseudocode example on Wikipedia
 * @version 1, April 7th, 2019
 */

public class CombSort {
    //creates an unsorted array, a CombSort object, and runs the sort method
    public static void main(String[] args) {
        int[] unsorted = {10, 5, 421, 19, 9, 2, 6};
        CombSort cs = new CombSort();
        cs.sort(unsorted);
    }

    public void sort(int[] testArray) {
        double gap = testArray.length; //sets initial gap size
        double shrinkFactor = 1.3; //sets shrink factor
        boolean sorted = false;

        while (!sorted) { //loops until array is declared sorted
            if (gap > 1)
                gap = Math.floor(gap / shrinkFactor); //decreases gap size
            else {
                gap = 1;
                sorted = true;  //assume the array is sorted until we find out it's not
                //we can't do this when gap is not 1, since not all elements would be compared
            }

            //this for loop is what actually checks and swaps elements to sort
            //combs through the array once
            for (int i = 0; i + gap < testArray.length; i++) {
                if (testArray[i] > testArray[(int) (i + gap)]) {
                    //swaps the two values
                    int temp = testArray[i];
                    testArray[i] = testArray[(int) (i + gap)];
                    testArray[(int) (i + gap)] = temp;

                    sorted = false; //if a swap was made, the array was not actually sorted
                }
            }
        }

        //prints out now-sorted array
        System.out.println(Arrays.toString(testArray));
    }
}