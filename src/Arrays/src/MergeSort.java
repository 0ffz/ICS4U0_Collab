import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The MergeSort class organizes two lists using the merge sort method.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Daniel Voznyy, Enfei Zhang, Ivan Karlov
 * @version 1 04.02.19
 */

public class MergeSort {

    /**
     * This method is to be accessed by the main class and sorts the lists
     * @param list The list that is being organized according to
     * @param list2 The list that is being organized according to the other list
     * @param comp The comparator to compare
     * @param <T> Temporary type, because the type is not yet defined
     */
    public static <T> void sort(List<T> list, List<T> list2, Comparator<T> comp) {
        mergeSort(0, list.size() - 1, list, list2, comp);
    }

    /**
     * This method splits the List into 2 until it can no longer be split.
     * @param l the leftmost value.
     * @param r the rightmost value.
     * @param list The list that is being organized according to.
     * @param list2 The list that is being organized according to the other.
     * @param comp The comparator to compare.
     * @param <T> Temporary type, because the type is not yet defined.
     */
    private static <T> void mergeSort(int l, int r, List<T> list, List<T> list2, Comparator<T> comp) {
        if (l < r) { //Recursion stops when the size of the list is less than 1
            int m = (l + r) / 2; //Finds middle of current list
            mergeSort(l, m, list, list2, comp); //Recursively sorts the two half lists
            mergeSort(m + 1, r, list, list2, comp); //Recursion goes all the way down until there is one element which is already sorted, then they merge all the way up to the whole array
            merge(l, r, m, list, list2, comp); //Merges the two sorted half lists
        }
    }

    /**
     * The method that puts the lists back together
     * @param l The leftmost value spot
     * @param r The rightmost value spot
     * @param m The middle value
     * @param list The list that is being organized according to.
     * @param list2 The second list
     * @param comp The list that is being organized according to the other.
     * @param <T> Temporary type, because the type is not yet defined.
     */
    private static <T> void merge(int l, int r, int m, List<T> list, List<T> list2, Comparator<T> comp) {
        List<T> new1 = new ArrayList<>(); //Makes new list to store merged elements in the current range
        List<T> new2 = new ArrayList<>(); //Makes new list to store merged elements in the second list in the current range
        int leastl = l, leastr = m + 1; //Minimum index in the left and right subarrays
        while (leastl <= m || leastr <= r) { //While there are still elements to merge (while either subarray still has elements)
            if (leastr > r || (leastl <= m && comp.compare(list.get(leastl), list.get(leastr)) < 0)) {  //If there are only elements left in the right subarray, or there are elements in both and the left one is lesser, add the left one and increase the current index for the left subarray
                new1.add(list.get(leastl));
                new2.add(list2.get(leastl));
                leastl++;
            }
            else{   //Otherwise add the right one
                new1.add(list.get(leastr));
                new2.add(list2.get(leastr));
                leastr++;
            }
        }
        for (int x = l; x <= r; x++) {
            list.set(x, new1.get(x - l)); //Set the current range in the original list to the new list
            list2.set(x, new2.get(x - l));  //Set the current range in the original list being organized to the new list
        }
    }
}