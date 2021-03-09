import java.util.Random;
import java.util.Scanner;

public class TwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n,m;
        do {
            System.out.print("Enter m: ");
            m = scanner.nextInt();
        } while(m <= 0);
        do {
            System.out.print("Enter n: ");
            n = scanner.nextInt();
        } while(n <= 0);
        int A[][] = new int[m][n];
        String temp;
        Random rand = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("A[" + i + "][" + j + "]: ");
                if(scanner.hasNextInt()){
                    A[i][j] = scanner.nextInt();
                } else {
                    temp = scanner.next();
                    if(temp.equals("$")) {
                        A[i][j] = rand.nextInt(1000);
                    } else {
                        j--;
                    }
                }
            }
        }
        int B[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("B[" + i + "][" + j + "]: ");
                if(scanner.hasNextInt()){
                    B[i][j] = scanner.nextInt();
                } else {
                    temp = scanner.next();
                    if(temp.equals("$")) {
                        B[i][j] = rand.nextInt(1000);
                    } else {
                        j--;
                    }
                }
            }
        }
        int C[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
                System.out.print(C[i][j]+" ");
            }
            System.out.println();
        }
        scanner.close();
    }
}
