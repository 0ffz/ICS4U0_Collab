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

        while (sorted == false) { //loops until array is declared sorted

            gap = Math.floor(gap / shrinkFactor); //decreases gap size

            if (gap <= 1) {
                gap = 1;
                sorted = true; //array is sorted when the gap == 1
            } else {
            }

            //combs through the array once
            int i = 0;
            while (i + gap < testArray.length) {
                if (testArray[i] > testArray[(int) (i + gap)]) {
                    //swaps the two values
                    int temp = testArray[i];
                    testArray[i] = testArray[(int) (i + gap)];
                    testArray[(int) (i + gap)] = temp;

                    //array is still unsorted
                    sorted = false;
                } else {
                }
                i++;
            }
        }
        //prints out now-sorted array
        for (int i : testArray) {
            System.out.println(i);
        }
    }
}