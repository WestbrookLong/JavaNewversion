package view.game;


import view.FrameUtil;

import javax.swing.*;
import java.awt.*;

public class FailFrame extends JFrame{

    private final JLabel FAIL;
    private JButton confirm;
    public FailFrame(int width, int height){
        this.setTitle("level victory");
        this.setLayout(null);
        this.setSize(width, height);
        this.FAIL = FrameUtil.createJLabel(this, "FAIL", new Font("serif", Font.ITALIC, 22), new Point( 80, 70), 180, 50);
        this.confirm = FrameUtil.createButton(this, "confirm", new Point(30, height / 2 - 50), 200, 60);

        confirm.addActionListener(e ->{

            this.setVisible(false);

        });



}}
