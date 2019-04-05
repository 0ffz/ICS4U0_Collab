import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The StudentMarksB class reads a file which contains student's names and marks and then merge sorts the names and their marks by alphabetical order
 * of their name prints the name with the mark next to it spaced properly.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Enfei Zhang
 * @version 1 04.02.19
 */

public class StudentMarksB {

    /**
     * This is the main method that reads the "A7-1.txt" file and calls the mergeSort() method to sort the
     * values in alphabetical order and then print them all while being spaced nicely.
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
     *
     * @param args [ ]  String array that allows command line parameters to be used when executing the program.
     */
    public static void main(String[] args) {
        int lines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("A7-1.txt"));
            while (br.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.out.println("Oh no!");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("A7-1.txt"));
            List<String> studentNames = new ArrayList<>();
            List<String> studentMarks = new ArrayList<>();
            Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            };
            for (int x = 0; x < lines; x++) {
                if (x % 2 == 0)
                    studentNames.add(br.readLine());
                else if (x == 1)
                    studentMarks.add(br.readLine());
                else
                    studentMarks.add(br.readLine());
            }
            MergeSort.sort(studentNames, studentMarks, comparator);
            for (int x = 0; x < lines / 2; x++) {
                System.out.printf("%-10s%s\n", studentNames.get(x), studentMarks.get(x));
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Oh no!");
        }
    }
}
