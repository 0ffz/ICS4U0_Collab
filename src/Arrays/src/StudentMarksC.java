import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The StudentMarksB class reads a file which contains student's names and marks and then merge sorts the names and their marks by least to greatest
 * in terms of their marks and then prints the name with the mark next to it spaced properly.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Daniel Voznyy, Enfei Zhang, Ivan Karlov
 * @version 1 04.02.19
 */

public class StudentMarksC {

    /**
     * This is the method that splits the arrays into their sections to be further sorted in the merge method.
     *
     * @param a The marks of the students
     * @param b The names of the students
     * @param n The size of the array
     *
     * <b>Local Variables</b>
     * <p>
     * <b>mid</b> The place of the middle element in the array.
     * <p>
     * <b>tempA[]</b> Integer array to hold the integer values of the marks.
     * <p>
     * <b>l[]</b> Integer array to hold all the values of tempA to the left of the middle.
     * <p>
     * <b>r[]</b> Integer array to hold all the values of tempA to the right of the middle.
     * <p>
     * <b>ll[]</b> String array to hold all the values of a to the left of the middle.
     * <p>
     * <b>lr[]</b> String array to hold all the values of a to the right of the middle.
     * <p>
     * <b>bl[]</b> String array to hold all the values of b to the left of the middle.
     * <p>
     * <b>br[]</b> String array to hold all the values of b to the right of the middle.
     */
    public static void mergeSort(String[] a, String[] b, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] tempA = new int [a.length];
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        String[] ll = new String[mid];
        String[] lr = new String[n - mid];
        String[] bl = new String[mid];
        String[] br = new String[n - mid];

        for (int x = 0; x < a.length; x++){
            tempA[x] = Integer.parseInt(a[x]);
        }
        for (int i = 0; i < mid; i++) {
            l[i] = tempA[i];
            bl[i] = b[i];
            ll[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = tempA[i];
            br[i] =  b[i];
            lr[i] = a[i];
        }
        mergeSort(ll, bl, mid);
        mergeSort(lr, br, n - mid);

        merge(a, tempA, l, r, ll, lr, b, bl, br, mid, n - mid);
    }

    /**
     *
     * @param a The marks as Strings.
     * @param a2 The marks as integers.
     * @param l The values of all the elements to the left of the middle in the a2 array.
     * @param r The values of all the elements to the right of the middle in the a2 array.
     * @param ll The values of all the elements to the left of the middle in the a array.
     * @param lr The values of all the elements to the right of the middle in the a array.
     * @param b The names of the students.
     * @param bl The values of all the elements to the left of the middle in the b array.
     * @param br The values of all the elements to the right of the middle in the b array.
     * @param left The amount of items on the left side of the array.
     * @param right The amount of items on the right side of the array.
     *
     * <b>Local Variables</b>
     * <p>
     * <b>i</b> Integer to know the place of the values of the array
     * <p>
     * <b>j</b> Integer to know the place of the values of the array
     * <p>
     * <b>k</b> Integer to know the place of the values of the array
     */
    public static void merge(String[] a, int[] a2, int[] l, int[] r, String[] ll, String[] lr, String[] b, String[] bl, String[] br, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                k++;
                i++;
                a2[k] = l[i];
                b[k] = bl[i];
                a[k] = ll[i];
            }
            else {
                k++;
                j++;
                a2[k] = r[j];
                b[k] = br[j];
                a[k] = lr[j];
            }
        }
        while (i < left) {
            k++;
            i++;
            a2[k] = l[i];
            b[k] = bl[i];
            a[k] = ll[j];
        }
        while (j < right) {
            k++;
            j++;
            a2[k] = r[j];
            b[k] = br[j];
            a[k] = lr[j];
        }
    }

    /**
     * This is the main method that reads the "A7-1.txt" file and calls the mergeSort() method to sort the
     * values in least to greatest order and then print them all while being spaced nicely.
     *
     * @param args [ ]  String array that allows command line
     * parameters to be used when executing the program.
     *
     * <b>Local Dictionary</b>
     * <p>
     * <b>br</b> Instance of the BufferedReader class to read the fil A7-1.txt.
     * <p>
     * <b>line</b> int variable to keep track of the amount of lines in the file.
     * <p>
     * <b>studentNames[]</b> String array to hold the names of the students.
     * <p>
     * <b>studentMarks[]</b> String array to hold the marks of the students
     */
    public static void main(String[] args) {
        int lines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("A7-1.txt"));
            while (br.readLine() != null){
                lines++;
            }
        }
        catch (IOException e){
            System.out.println("Oh no!");
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader("A7-1.txt"));
            String[] studentNames = new String[lines/2 + 1];
            String[] studentMarks = new String[lines/2 + 1];
            for (int x = 0; x < lines; x++){
                if (x % 2 == 0)
                    studentNames [x/2] = br.readLine();
                else
                    if (x == 1)
                        studentMarks [x - 1] = br.readLine();
                    else
                        studentMarks [(x - 1)/2] = br.readLine();
            }
            mergeSort(studentMarks, studentNames, studentMarks.length);
            for (int x = 0; x < lines/2; x++){
                System.out.printf("%-10s%s\n", studentNames [x], studentMarks [x]);
            }
        }
        catch (IOException e){
            System.out.println("Oh no!");
        }
    }
}
