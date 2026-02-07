package src;

import javax.swing.*;
import java.awt.*;

// 설정 변경을 자동으로 반영하는 핵심 패널
public class PreviewPanel extends JPanel
	implements ConfigManager.ConfigListener {

	 private final ConfigManager config = ConfigManager.getInstance();
	 private final JLabel previewLabel;
	 
	 public PreviewPanel() {
	        setLayout(new GridBagLayout());

	        previewLabel = new JLabel("Preview Text");
	        add(previewLabel);

	        // 설정 변경 구독
	        config.addListener(this);

	        applyConfig();
	    }

	    private void applyConfig() {
	        // 테마 적용
	        if (config.getTheme() == ConfigManager.Theme.DARK) {
	            setBackground(Color.DARK_GRAY);
	            previewLabel.setForeground(Color.WHITE);
	        } else {
	            setBackground(Color.WHITE);
	            previewLabel.setForeground(Color.BLACK);
	        }

	        // 폰트 적용
	        previewLabel.setFont(
	                previewLabel.getFont()
	                        .deriveFont((float) config.getFontSize())
	        );

	        repaint();
	    }

	    @Override
	    public void onConfigChanged() {
	        applyConfig();
	    }  
}
