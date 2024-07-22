import javax.swing.SwingUtilities;

public class LaunchApp {
    public static void main(String[] args) {
    
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and show the GUI
                new BankingApp();
            }
        });

    }
    
}
