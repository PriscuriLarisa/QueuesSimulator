package queueSimulator.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame{
    private Container container;
    private JLabel simulationTimeLabel, nbQueuesLabel, nbClientsLabel, minArrivalLabel, maxArrivalLabel, minProcessingLabel, maxProcessingLabel;
    private JTextField simulationTimeText, nbQueuesText, nbClientsText, minArrivalText, maxArrivalText, minProcessingText, maxProcessingText;
    private JButton startButton;

    public MainView(String title) {
        setTitle(title);
        setSize(360, 510);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        container = getContentPane();
        container.setLayout(null);

        simulationTimeLabel = new JLabel("Simulation time: ");
        nbQueuesLabel = new JLabel("Number of queues: ");
        nbClientsLabel = new JLabel("Number of clients: ");
        minArrivalLabel = new JLabel("Min arrival time: ");
        maxArrivalLabel = new JLabel("Max arrival time: ");
        minProcessingLabel = new JLabel("Min processing time: ");
        maxProcessingLabel = new JLabel("Max processing time: ");

        simulationTimeText = new JTextField();
        nbQueuesText = new JTextField();
        nbClientsText = new JTextField();
        minArrivalText = new JTextField();
        maxArrivalText = new JTextField();
        minProcessingText = new JTextField();
        maxProcessingText = new JTextField();

        simulationTimeLabel.setBounds(10, 10, 335, 30);
        simulationTimeLabel.setForeground(new Color(155, 155, 155));
        simulationTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        simulationTimeLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        nbQueuesLabel.setBounds(10, 90, 335, 30);
        nbQueuesLabel.setForeground(new Color(155, 155, 155));
        nbQueuesLabel.setHorizontalAlignment(JLabel.CENTER);
        nbQueuesLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        nbClientsLabel.setBounds(10, 170, 335, 30);
        nbClientsLabel.setForeground(new Color(155, 155, 155));
        nbClientsLabel.setHorizontalAlignment(JLabel.CENTER);
        nbClientsLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        minArrivalLabel.setBounds(10, 240, 170, 30);
        minArrivalLabel.setForeground(new Color(155, 155, 155));
        minArrivalLabel.setHorizontalAlignment(JLabel.CENTER);
        minArrivalLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        maxArrivalLabel.setBounds(180, 240, 170, 30);
        maxArrivalLabel.setForeground(new Color(155, 155, 155));
        maxArrivalLabel.setHorizontalAlignment(JLabel.CENTER);
        maxArrivalLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        minProcessingLabel.setBounds(10, 320, 170, 30);
        minProcessingLabel.setForeground(new Color(155, 155, 155));
        minProcessingLabel.setHorizontalAlignment(JLabel.CENTER);
        minProcessingLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        maxProcessingLabel.setBounds(180, 320, 170, 30);
        maxProcessingLabel.setForeground(new Color(155, 155, 155));
        maxProcessingLabel.setHorizontalAlignment(JLabel.CENTER);
        maxProcessingLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));


        simulationTimeText.setBounds(120, 50, 105, 30);
        simulationTimeText.setBackground(new Color(155, 155, 155));
        simulationTimeText.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        nbQueuesText.setBounds(120, 130, 105, 30);
        nbQueuesText.setBackground(new Color(155, 155, 155));
        nbQueuesText.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        nbClientsText.setBounds(120, 210, 105, 30);
        nbClientsText.setBackground(new Color(155, 155, 155));
        nbClientsText.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        minArrivalText.setBounds(40, 280, 105, 30);
        minArrivalText.setBackground(new Color(155, 155, 155));
        minArrivalText.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        maxArrivalText.setBounds(200, 280, 105, 30);
        maxArrivalText.setBackground(new Color(155, 155, 155));
        maxArrivalText.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        minProcessingText.setBounds(40, 360, 105, 30);
        minProcessingText.setBackground(new Color(155, 155, 155));
        minProcessingText.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        maxProcessingText.setBounds(200, 360, 105, 30);
        maxProcessingText.setBackground(new Color(155, 155, 155));
        maxProcessingText.setBorder(new LineBorder(new Color(155, 155, 155), 5));

        startButton = new JButton("START");
        startButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        startButton.setBounds(120, 410, 105, 40);
        startButton.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        startButton.setBackground(new Color(63, 127, 0));
        startButton.setVerticalAlignment(JButton.CENTER);


        container.setBackground(new Color(48, 48, 48));

        container.add(simulationTimeLabel);
        container.add(nbClientsLabel);
        container.add(maxArrivalLabel);
        container.add(minArrivalLabel);
        container.add(maxProcessingLabel);
        container.add(minProcessingLabel);
        container.add(nbQueuesLabel);

        container.add(simulationTimeText);
        container.add(nbQueuesText);
        container.add(nbClientsText);
        container.add(minArrivalText);
        container.add(maxArrivalText);
        container.add(minProcessingText);
        container.add(maxProcessingText);

        container.add(startButton);

        setVisible(true);

    }

    public void startListener(ActionListener listenForStart) {
        startButton.addActionListener(listenForStart);
    }

    public String getSimulationTimeText() {
        return simulationTimeText.getText();
    }

    public String getNbQueuesText() {
        return nbQueuesText.getText();
    }

    public String getNbClientsText() {
        return nbClientsText.getText();
    }

    public String getMinArrivalText() {
        return minArrivalText.getText();
    }

    public String getMaxArrivalText() {
        return maxArrivalText.getText();
    }

    public String getMinProcessingText() {
        return minProcessingText.getText();
    }

    public String getMaxProcessingText() {
        return maxProcessingText.getText();
    }
}

