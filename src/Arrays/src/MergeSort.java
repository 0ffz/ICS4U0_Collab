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
     * This method sorts the List
     * @param list The list that is being organized according to
     * @param list2 The list that is being organized according to the other list
     * @param comp The comparator to compare
     * @param <T> Temporary type, because the type is not yet defined
     */
    public static <T> void sort(List<T> list, List<T> list2, Comparator<T> comp) {
        mergeSort(0, list.size() - 1, list, list2, comp); //public static method to be accessed by main class, initiates mergesort over entire list with given comparator
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
        if (l < r) { //recursion stops when the size of the list is < 1
            int m = (l + r) / 2; //finds midpoint of current list
            mergeSort(l, m, list, list2, comp); //recursively sorts two half lists
            mergeSort(m + 1, r, list, list2, comp); //basically the recursion goes all the way down until there is one element which is already sorted, then they merge all the way up to the whole array
            merge(l, r, m, list, list2, comp); //merges two sorted half lists
        }
    }

    /**
     * The method that puts the lists back together
     * @param l The leftmost value
     * @param r The rightmost value
     * @param m The middle value
     * @param list The list that is being organized according to.
     * @param list2 The second list
     * @param comp The list that is being organized according to the other.
     * @param <T> Temporary type, because the type is not yet defined.
     */
    private static <T> void merge(int l, int r, int m, List<T> list, List<T> list2, Comparator<T> comp) {
        List<T> newl = new ArrayList<>(); //makes new list to store merged elements in the current range
        List<T> new2 = new ArrayList<>();
        int leastl = l, leastr = m + 1; //minimum index in the left and right subarrays
        while (leastl <= m || leastr <= r) { //while there are still elements to merge (while either subarray still has elements)
            if (leastr > r || (leastl <= m && comp.compare(list.get(leastl), list.get(leastr)) < 0)) {
                newl.add(list.get(leastl++)); //if there are only elements left in the right subarray, or there are elements in both and the left one is lesser, add the left one and increase the current index for the left subarray
                new2.add(list2.get(leastl++));
            } else {
                newl.add(list.get(leastr++)); //otherwise add the right one
                new2.add(list2.get(leastr++));
            }

            for (int x = l; x <= r; x++) {
                list.set(x, newl.get(x - l)); //set the current range in the original list to the new list
                list2.set (x, new2.get(x - 1));
            }
        }
    }
}