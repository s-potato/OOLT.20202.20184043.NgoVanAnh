import java.util.Scanner;

public class StarTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Height:");
        int num = scanner.nextInt();
        scanner.close();
        if(num < 1){
            return;
        }
        for(int i = 1; i<=num; i++){
            for(int j = 0; j<(num-i); j++){
                System.out.print(" ");
            }
            for(int j=1;j<(2*i);j++){
                System.out.print("*");
            };
            for(int j = 0; j<(num-i); j++){
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
