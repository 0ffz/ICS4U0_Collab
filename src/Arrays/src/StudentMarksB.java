import java.io.*;

/**
 * The StudentMarksB class reads a file which contains student's names and marks and then merge sorts the names and their marks by alphabetical order
 * of their name prints the name with the mark next to it spaced properly.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Daniel Voznyy, Enfei Zhang, Ivan Karlov
 * @version 1 04.02.19
 */

public class StudentMarksB {

    /**
     * This is the method that splits the arrays into their sections to be further sorted in the merge method.
     *
     * @param a The names of the students
     * @param b The marks of the students
     * @param n The size of the array
     *
     * <b>Local Variables</b>
     * <p>
     * <b>mid</b> The place of the middle element in the array.
     * <p>
     * <b>a1[]</b> Integer array to hold the ASCII values of the first letter of the names.
     * <p>
     * <b>l[]</b> Integer array to hold all the values of a1 to the left of the middle.
     * <p>
     * <b>r[]</b> Integer array to hold all the values of a1 to the right of the middle.
     * <p>
     * <b>l2[]</b> String array to hold all the values of a to the left of the middle.
     * <p>
     * <b>r2[]</b> String array to hold all the values of a to the right of the middle.
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
        int[] a1 = new int[a.length - 1];
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        String[] l2 = new String[mid];
        String[] r2 = new String[n - mid];
        String[] bl = new String[mid];
        String[] br = new String[n - mid];
        for (int x = 0; x < a.length; x++){
            a1[x] = a[x].charAt(0);
        }
        for (int i = 0; i < mid; i++) {
            l[i] = a[i].charAt(0);
            bl[i] = b[i];
            l2[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i].charAt(0);
            br[i - mid] = b[i];
            r2[i - mid] = a[i];
        }
        mergeSort(l2, bl, mid);
        mergeSort(r2, br, n - mid);

        merge(a1, a, b, l, r, l2, r2, bl, br, mid, n - mid);
    }

    /**
     * This method puts together the values of the smaller seperated chunks of sorted groups of the array.
     *
     * @param a1 The ASCII values of the first character of the names.
     * @param a The names.
     * @param b The marks.
     * @param l The values of all the characters to the left of the middle in the a1 array.
     * @param r The values of all the characters to the right of the middle in the a1 array.
     * @param l2 The values of all the characters to the left of the middle in the a array.
     * @param r2 The values of all the characters to the right of the middle in the a array.
     * @param bl The values of all the characters to the left of the middle in the b array.
     * @param br The values of all the characters to the right of the middle in the b array.
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
    public static void merge(int[] a1, String[] a, String[] b, int[] l, int[] r, String[] l2, String[] r2, String[] bl, String[] br, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                k++;
                i++;
                a1[k] = l[i];
                a[k] = l2[i];
                b[k] = bl[i];
            }
            else {
                k++;
                j++;
                a1[k] = r[i];
                a[k] = r2[j];
                b[k] = br[j];
            }
        }
        while (i < left) {
            k++;
            i++;
            a1[k] = l[i];
            a[k] = l2[i];
            b[k] = bl[i];
        }
        while (j < right) {
            k++;
            i++;
            a1[k] = r[j];
            a[k] = r2[j];
            b[k] = br[j];
        }
    }

    /**
     * This is the main method that reads the "A7-1.txt" file and calls the mergeSort() method to sort the
     * values in alphabetical order and then print them all while being spaced nicely.
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
            mergeSort(studentNames, studentMarks, studentNames.length);
            for (int x = 0; x < lines/2; x++){
                System.out.printf("%-10s%s\n", studentNames [x], studentMarks [x]);
            }
        }
        catch (IOException e){
            System.out.println("Oh no!");
        }
    }
}
