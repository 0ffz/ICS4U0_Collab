import java.io.*;

/**
 * The StudentMarksA class reads a file which contains student's names and marks and then prints the name with the mark next
 * to it spaced properly.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Daniel Voznyy, Enfei Zhang, Ivan Karlov
 * @version 1 04.02.19
 */
public class StudentMarksA {

    /**
     * This method reads a file that contains students names and corresponding marks under the name. This method will then
     * output the names with their corresponding mark.
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
     *
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
                else if (x == 1)
                    studentMarks [x - 1] = br.readLine();
                else
                    studentMarks [(x - 1)/2] = br.readLine();
            }

            for (int x = 0; x < lines/2; x++){
                System.out.printf("%-10s%s\n", studentNames [x], studentMarks [x]);
            }
        }
        catch (IOException e){
            System.out.println("Oh no!");
        }
    }
}
