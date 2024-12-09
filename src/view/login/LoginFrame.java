package view.login;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {

    private JPanel loginPanel;   // 登录界面
    private JPanel registerPanel; // 注册界面
    private CardLayout cardLayout; // 页面切换布局
    private view.level.LevelFrame levelFrame; // 使用全局的关卡窗口类
    private static String username;

    public LoginFrame(int width, int height) {
        // 初始化窗口
        this.setTitle("登陆界面");
        this.setSize(430, 250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // 初始化布局
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        // 初始化登录界面
        initLoginPanel();

        // 初始化注册界面
        initRegisterPanel();

        // 显示登录界面
        cardLayout.show(this.getContentPane(), "login");
    }


    private void initLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);

        // 登录组件
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(50, 50, 100, 30);
        loginPanel.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 30);
        loginPanel.add(usernameField);

        JLabel passLabel = new JLabel("密码:");
        passLabel.setBounds(50, 100, 100, 30);
        loginPanel.add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("登入");
        loginButton.setBounds(50, 150, 100, 30);
        loginPanel.add(loginButton);

        JButton registerButton = new JButton("没有账号？点我注册");
        registerButton.setBounds(200, 150, 150, 30);
        loginPanel.add(registerButton);

        // 登录逻辑：点击登入按钮后调用事件监听器，获取输入的用户名和密码并与数据库作比较，作出判断
        loginButton.addActionListener(e -> {
            String usernameInput = usernameField.getText();
            String password = new String(passwordField.getPassword());



            File dir2 = new File("C:\\Users\\WESTBROOK\\IdeaProjects\\JavaNewversion\\src\\Userdata");
            File[] files2 = dir2.listFiles();
            if (files2 != null) {
                for (File file : files2) {
                    if (file.isDirectory() && file.getName().equals(usernameInput)) {
                        File folder = new File("C:\\Users\\WESTBROOK\\IdeaProjects\\JavaNewversion\\src\\Userdata\\" + usernameInput);
                        File[] contents = folder.listFiles();
                        if (contents != null) {
                            for (File content : contents) {
                                if (content.getName().equals(password + ".txt")) {
                                    this.username = usernameInput;
                                    JOptionPane.showMessageDialog(this, "登录成功！");
                                    this.levelFrame.setVisible(true); // 显示关卡窗口
                                    this.setVisible(false);// 隐藏登录窗口
                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(this, "用户名或密码错误！");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        });

        // 切换到注册界面
        registerButton.addActionListener(e -> cardLayout.show(this.getContentPane(), "register"));

        // 添加登录面板
        this.add(loginPanel, "login");
    }

    private void initRegisterPanel() {
        registerPanel = new JPanel();
        registerPanel.setLayout(null);

        // 注册组件
        JLabel newUserLabel = new JLabel("新建用户名:");
        newUserLabel.setBounds(50, 50, 100, 30);
        registerPanel.add(newUserLabel);

        JTextField newUsernameField = new JTextField();
        newUsernameField.setBounds(150, 50, 150, 30);
        registerPanel.add(newUsernameField);


        JLabel newPassLabel = new JLabel("新建密码:");
        newPassLabel.setBounds(50, 100, 100, 30);
        registerPanel.add(newPassLabel);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(150, 100, 150, 30);
        registerPanel.add(newPasswordField);

        JButton saveButton = new JButton("保存");
        saveButton.setBounds(100, 150, 100, 30);
        registerPanel.add(saveButton);

        JButton backButton = new JButton("返回登录");
        backButton.setBounds(220, 150, 100, 30);
        registerPanel.add(backButton);

        // 保存注册信息逻辑
        saveButton.addActionListener(e2 -> {
            String newUser = newUsernameField.getText();
            String newPass = new String(newPasswordField.getPassword());
            File dir = new File("C:\\Users\\WESTBROOK\\IdeaProjects\\JavaNewversion\\src\\Userdata");
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals(newUser)) {
                        JOptionPane.showMessageDialog(this, "用户名已存在！");
                        return;
                    }
                }
                File f1 = new File("C:\\Users\\WESTBROOK\\IdeaProjects\\JavaNewversion\\src\\Userdata/" + newUser);
                f1.mkdirs();
                File f2 = new File("C:\\Users\\WESTBROOK\\IdeaProjects\\JavaNewversion\\src\\Userdata/" + newUser + "/" + newPass + ".txt");
                try {
                    f2.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                File f3 = new File("C:\\Users\\WESTBROOK\\IdeaProjects\\JavaNewversion\\src\\Userdata/" + newUser + "/" + newUser + "data");
                f3.mkdirs();
                JOptionPane.showMessageDialog(this, "注册成功！");
                cardLayout.show(this.getContentPane(), "login"); // 切回登录界面
            }
        });

        // 返回登录界面
        backButton.addActionListener(e -> cardLayout.show(this.getContentPane(), "login"));

        // 添加注册面板
        this.add(registerPanel, "register");
    }

    public void setLevelFrame(view.level.LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }
    public static String getUsername() {
        return username;
    }

}

