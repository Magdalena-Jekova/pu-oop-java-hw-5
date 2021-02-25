import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modal extends JDialog {

    public Modal(JFrame parent, String title, String message) {
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        JButton refreshButton = new JButton("Нов дисплей!");

        panel.add(label);
        panel.add(refreshButton);
        getContentPane().add(panel);
        this.setSize(370,150);
        refreshButton.addActionListener(new RefreshScreen());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void show(JFrame parent, String title, String message){
        new Modal(parent, title, message);
    }

    public static class RefreshScreen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Phone newScreen = new Phone("");
        }
    }
}