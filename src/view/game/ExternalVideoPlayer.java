package view.game;

import javax.swing.*;
import java.io.IOException;

public class ExternalVideoPlayer extends JFrame {
    public ExternalVideoPlayer(String videoPath) {
        setTitle("External Video Player");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // 尝试打开外部视频播放器
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + videoPath);
            } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                Runtime.getRuntime().exec("open " + videoPath);
            } else { // Linux
                Runtime.getRuntime().exec("xdg-open " + videoPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "无法打开视频播放器", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }}