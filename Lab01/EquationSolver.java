import javax.swing.JOptionPane;
public class EquationSolver {
    public static void main(String[] args) {
        EquationSolver solver = new EquationSolver();
        String options[] = {"Linear equation","Linear system","Second-degree equation","Cancel"};
        int type = JOptionPane.showOptionDialog(null, "Which equation do you want to solve?", "Choose", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[3]);
        
        switch (type) {
            case 0:
                solver.linear_equation();
                break;
            case 1:
                solver.linear_system();
                break;
            case 2:
                solver.second_degree_equation();
                break;
            default:
                break;
        }

        System.exit(0);
    }

    private void linear_equation() {
        double a = 0, b = 0;
        a = input_panel("a", 0);
        b = input_panel("b");
        String strX = "x = " + String.valueOf(-b/a);
        JOptionPane.showMessageDialog(null, strX, "Solver", JOptionPane.INFORMATION_MESSAGE);
    }

    private void linear_system() {
        double a[] = new double[6];
        String name[] = {"a11", "a12", "b1", "a21", "a22", "b2"};
        String strX;
        for (int i = 0; i < name.length; i++) {
            a[i] = input_panel(name[i]);
        }
        double d = a[0]*a[4] - a[1]*a[3];
        double d1 = a[2]*a[4] - a[5]*a[1];
        double d2 = a[0]*a[5] - a[3]*a[2];    
        if(d == 0){
            if(d1 == 0 && d2 == 0){
                strX = "Infinitely solutions";
            } else{
                strX = "No solution";
            }
        } else{
            strX = "x1 = " + String.valueOf(d1/d) + "\nx2 = " + String.valueOf(d2/d);
        }
        JOptionPane.showMessageDialog(null, strX, "Solver", JOptionPane.INFORMATION_MESSAGE);
    }

    private void second_degree_equation() {
        double a = 0, b = 0, c = 0;
        a = input_panel("a", 0);
        b = input_panel("b");
        c = input_panel("c");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        String strX;
        double delta = b*b - 4*a*c;
        System.out.println(delta);
        if(delta < 0){
            strX = "No solution";
        } else if (delta == 0) {
            strX = "x = " + String.valueOf(-b/(2*a));
        } else {
            strX = "x = " + String.valueOf((-b + Math.sqrt(delta))/(2*a)) + "\nor\nx = " + String.valueOf((-b - Math.sqrt(delta))/(2*a));
        }
        JOptionPane.showMessageDialog(null, strX, "Solver", JOptionPane.INFORMATION_MESSAGE);
    }

    private double input_panel(String name){
        double result = 0;
        String str = JOptionPane.showInputDialog(null, "Please input "+ name +": ", "Input " + name, JOptionPane.INFORMATION_MESSAGE);
        if(str == null){
            System.exit(1);
        }
        try {
            result = Double.parseDouble(str);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong input. The number is set to 0.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    private double input_panel(String name, double except){
        double result = 0;
        String str;
        do{
            str = JOptionPane.showInputDialog(null, "Please input "+ name +": ", "Input " + name, JOptionPane.INFORMATION_MESSAGE);
            if(str == null){
                System.exit(1);
            }
            try {
                result = Double.parseDouble(str);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Wrong input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(result == except){
                JOptionPane.showMessageDialog(null, "A must not be 0.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while(result == except);
        return result;
    }
}
