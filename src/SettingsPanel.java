package src;

import javax.swing.*;
import java.awt.*;

// ConfigManager의 Mutator만 호출하는 패널
public class SettingsPanel extends JPanel {
	private final ConfigManager config = ConfigManager.getInstance();
	
	public SettingsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Settings");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(title);
        add(Box.createVerticalStrut(20));

        // === Theme buttons ===
        JButton lightBtn = new JButton("Light Theme");
        JButton darkBtn = new JButton("Dark Theme");

        lightBtn.addActionListener(e ->
                config.setTheme(ConfigManager.Theme.LIGHT));
        darkBtn.addActionListener(e ->
                config.setTheme(ConfigManager.Theme.DARK));

        add(lightBtn);
        add(Box.createVerticalStrut(10));
        add(darkBtn);
        add(Box.createVerticalStrut(30));

        // === Font size slider ===
        JLabel fontLabel = new JLabel("Font Size");
        fontLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSlider fontSlider = new JSlider(10, 36, config.getFontSize());
        fontSlider.setMajorTickSpacing(5);
        fontSlider.setPaintTicks(true);
        fontSlider.setPaintLabels(true);
        fontSlider.addChangeListener(e ->
                config.setFontSize(fontSlider.getValue()));

        add(fontLabel);
        add(fontSlider);
    }
}
