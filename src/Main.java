package src;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Singleton Config Preview");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(900, 600);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
