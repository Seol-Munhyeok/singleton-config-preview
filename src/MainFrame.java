package src;

import javax.swing.*;
import java.awt.*;

// 전체 레이아웃 담당 (왼쪽 설정 / 오른쪽 미리보기)
public class MainFrame extends JFrame {
	
	public MainFrame() {
        super("Singleton Config Preview");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        add(new SettingsPanel());
        add(new PreviewPanel());
    }
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
