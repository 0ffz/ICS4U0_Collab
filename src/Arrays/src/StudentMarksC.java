import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentMarksC {

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
