import javax.swing.JOptionPane;

public class ChoosingOption {
    public static void main(String[] args) {
        int option = JOptionPane.showConfirmDialog(null, "Do you want to change to the last ticket", "Choose", JOptionPane.YES_NO_OPTION);
        JOptionPane.showMessageDialog(null, "You've choose: " + (option == JOptionPane.YES_OPTION?"Yes":"No"));
        System.exit(0);
    }
}
