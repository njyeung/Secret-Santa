import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        // Make sure eel is downloaded first
        try {
            pr = rt.exec("pip install eel");
            pr.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        // Run eelController.py
        try {
            pr = rt.exec("python3 eelController.py");
            pr.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
