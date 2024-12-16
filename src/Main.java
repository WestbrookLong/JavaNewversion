import view.level.LevelFrame;
import view.login.LoginFrame;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        new Thread(() -> playWavMusic("JavaNewversion\\src\\music.wav")).start();
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(280, 280);
            LevelFrame levelFrame = new LevelFrame(1000, 200);
            loginFrame.setLevelFrame(levelFrame);
            String[] options = {"游客模式", "用户模式"};
            int choice = JOptionPane.showOptionDialog(null,
                    "请选择游戏模式",
                    "模式选择",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (choice == 0) {
                levelFrame.setVisible(true);
            } else if (choice == 1) {
                loginFrame.setVisible(true);
            }
        });

    }
    public static void playWavMusic(String s) {
        try {
            File audioFile = new File("JavaNewversion\\src\\music.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("无法播放音乐文件");
        }
    }
}



