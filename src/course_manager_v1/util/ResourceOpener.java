package course_manager_v1.util;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class ResourceOpener {
    public static void openLink(String url) {
        String os = System.getProperty("os.name").toLowerCase();

        // 1. Try the standard Java Desktop API first
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
                return;
            } catch (Exception e) {
                System.err.println("Desktop API failed: " + e.getMessage());
            }
        }

        // 2. Fallback: Use ProcessBuilder for Linux/Unix
        if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            try {
                new ProcessBuilder("xdg-open", url).start();
            } catch (IOException e) {
                System.err.println("Could not open browser using xdg-open: " + e.getMessage());
            }
        } else {
            System.err.println("Unsupported operating system or browser environment.");
        }
    }
}