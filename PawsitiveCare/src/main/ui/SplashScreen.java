package ui;

import javax.swing.*;

public class SplashScreen extends JFrame {

    private JLabel splashLabel;

    // Effect: Create a Splash Screen and add the Image to be displayed 
    public SplashScreen() {
        setTitle("Pawsitive Care");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(612, 306);  
        setLocationRelativeTo(null);  

        splashLabel = new JLabel(new ImageIcon("src/main/images/cat and dog paw.jpg")); // Add your image file path
        add(splashLabel);
        
        setVisible(true);
    }

    // Effect: display the splash screen and then transition to the main window
    public void showSplashScreen() {
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new PawsitiveCareGUI();  
        this.dispose(); 
    }

    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen();
        splash.showSplashScreen();
    }
}

