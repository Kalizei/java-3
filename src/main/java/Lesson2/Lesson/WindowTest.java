package Lesson2.Lesson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowTest extends JFrame {

    public WindowTest(){
        setTitle("PS");
        JPanel panel = new JPanel(new GridLayout(1,2));
        setBounds(300,300,300,300);
        JTextField text_field = new JTextField();
        JButton button = new JButton("send");

        panel.add(text_field);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainBD.start();
            }
        });

        add(panel,BorderLayout.NORTH);
        setVisible(true);

    }
}
