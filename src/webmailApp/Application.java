package webmailApp;

/*
 * Créé le 17 oct. 2003
 */
import webmailIhm.FenetrePrincipale;
import javax.swing.*;

/**
 *
 *
 * @author jrl
 * @version 1.0
 */
public class Application {

    public Application() {
        FenetrePrincipale frm = new FenetrePrincipale();
        frm.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //JFrame.setDefaultLookAndFeelDecorated(true);
            //JDialog.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
        }
        Application application = new Application();
    }
}
