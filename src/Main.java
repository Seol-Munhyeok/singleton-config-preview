package src;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConfigManager a = ConfigManager.getInstance();
        ConfigManager b = ConfigManager.getInstance();

        System.out.println(a == b); // true 나오면 싱글톤 OK

        a.addListener(() -> System.out.println("changed!"));
        a.setTheme(ConfigManager.Theme.DARK); // changed! 출력
        a.setFontSize(22);                    // changed! 출력
    }
}

