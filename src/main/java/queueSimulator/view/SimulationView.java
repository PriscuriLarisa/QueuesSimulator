package queueSimulator.view;

import javax.swing.*;
import java.awt.*;

public class SimulationView extends JFrame {

    private JTextArea textArea;

    public SimulationView(String title) {
        setTitle(title);
        setSize(520, 520);
        setLocation(600, 100);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        textArea = new JTextArea();

        textArea.setSize(400,400);
        textArea.setBackground(new Color(48, 48, 48));
        textArea.setForeground(new Color(219, 219, 219));
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));

        textArea.setLineWrap(false);
        textArea.setEditable(false);
        textArea.setVisible(true);
        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.add(scroll);
        this.setVisible(true);

    }

    public void setUpdated(String s) {

        MyWorker myWorker = new MyWorker(s, textArea);
        myWorker.execute();
    }

}

