import java.io.*;

public class StudentMarksA {
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

            for (int x = 0; x < lines/2; x++){
                System.out.printf("%-10s%s\n", studentNames [x], studentMarks [x]);
            }
        }
        catch (IOException e){
            System.out.println("Oh no!");
        }
    }
}
