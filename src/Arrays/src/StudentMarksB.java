import java.io.*;

public class StudentMarksB {

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
