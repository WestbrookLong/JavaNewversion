import view.level.LevelFrame; 
import view.login.LoginFrame; 

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          
            LoginFrame loginFrame = new LoginFrame(280, 280);
            LevelFrame levelFrame = new LevelFrame(500, 200);
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
}

