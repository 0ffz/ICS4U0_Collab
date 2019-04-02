import java.io.*;

public class StudentMarksA {
    public static void main(String[] args) {
        int lines = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("A7-1.txt"));
            while (br.readLine() != null){
                lines++;
            }
            String[] studentNames = new String[lines/2];
            String[] studentMarks = new String[lines/2];
            for (int x = 0; x <= lines; x++){
                if (x % 2 == 0)
                    studentNames [x] = br.readLine();
                else
                    studentMarks [x] = br.readLine();
            }

            for (int x = 0; x < lines/2; x++){
                System.out.println(String.format("%-5s= %s", studentNames [x], studentMarks [x]));
            }
        }
        catch (IOException e){
            System.out.println("Oh no!");
        }
    }
}
