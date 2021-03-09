import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NumbericArray {
    public static void main(String[] args) {
        int A[] = new int[1000];
        Random rand = new Random();
        for(int i = 0; i < 1000; i++){
            A[i] = rand.nextInt(10000);
        }
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.print("Array size: ");
            n = scanner.nextInt();
        } while(n <= 0 || n >= 1000);
        int B[] = new int[n];
        String temp;
        for (int i = 0; i < n; i++) {
            System.out.print("B" + i + ": ");
            if(scanner.hasNextInt()){
                B[i] = scanner.nextInt();
            } else {
                temp = scanner.next();
                if(temp.equals("$")) {
                    B[i] = A[i];
                } else {
                    i--;
                }
            }
        }
        Arrays.sort(B);
        long sum = 0;
        for (int i : B) {
            System.out.print(i + " ");
            sum += i;
        }
        System.out.println();
        double avg = sum/1000;
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + avg);
        scanner.close();
    }
}
