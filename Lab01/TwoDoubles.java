import javax.swing.JOptionPane;
public class TwoDoubles {
    public static void main(String[] args) {
        double num1 = 0, num2 = 0;
        String strNum1, strNum2;

        strNum1 = JOptionPane.showInputDialog(null, "Please input the first number: ", "Input the first number", JOptionPane.INFORMATION_MESSAGE);
        if(strNum1 == null){
            System.exit(1);
        }
        try {
            num1 = Double.parseDouble(strNum1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong input. The number is set to 0.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        strNum2 = JOptionPane.showInputDialog(null, "Please input the second number: ", "Input the second number", JOptionPane.INFORMATION_MESSAGE);
        if(strNum2 == null){
            System.exit(1);
        }
        try {
            num2 = Double.parseDouble(strNum2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong input. The number is set to 0.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        double sum = num1 + num2;
        double diff = num1 - num2;
        double prod = num1 * num2;
        String output = "Sum: " + String.valueOf(sum) + "\nDifferent: " + String.valueOf(diff) +"\nProduct: " + String.valueOf(prod) +"\nQuotient: ";
        if(num2 == 0){
            output += "Divine by zero error.";
        }
        else{
            output += String.valueOf(num1/num2);
        }
        JOptionPane.showMessageDialog(null, output, "Output", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
