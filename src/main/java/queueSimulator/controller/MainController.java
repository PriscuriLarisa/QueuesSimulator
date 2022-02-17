package queueSimulator.controller;

import queueSimulator.model.SimulationManager;
import queueSimulator.view.MainView;
import queueSimulator.view.SimulationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainController {

    private MainView view;
    private SimulationManager simulationManager;

    public MainController(MainView view){
        this.view = view;

        this.view.startListener(new StartListener());;
    }

    class StartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer simulationTime = Integer.parseInt(view.getSimulationTimeText());
            Integer nbQueues = Integer.parseInt(view.getNbQueuesText());
            Integer nbClients = Integer.parseInt(view.getNbClientsText());
            Integer minArrivalTime = Integer.parseInt(view.getMinArrivalText());
            Integer maxArrivalTime = Integer.parseInt(view.getMaxArrivalText());
            Integer minProcessingTime = Integer.parseInt(view.getMinProcessingText());
            Integer maxProcessingTime = Integer.parseInt(view.getMaxProcessingText());

            if(maxArrivalTime<minArrivalTime)
                JOptionPane.showMessageDialog(view, "Incorrect input data(Arrival time).");
            else if(maxProcessingTime<minProcessingTime)
                JOptionPane.showMessageDialog(view, "Incorrect input data(Processing time).");
            else {
                File file = new File("out.txt");
                FileWriter fw = null;
                try {
                    fw = new FileWriter(file);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                PrintWriter pw = new PrintWriter(fw);
                SimulationView view = new SimulationView("Simulation");
                simulationManager = new SimulationManager(view, simulationTime, nbQueues, nbClients, minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime, pw);
                Thread T = new Thread(simulationManager);
                T.start();
            }
        }
    }
}

