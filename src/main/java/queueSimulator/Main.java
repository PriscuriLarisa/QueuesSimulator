package queueSimulator;

import queueSimulator.controller.MainController;
import queueSimulator.view.MainView;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {


        MainView view = new MainView("Simulation form");
        MainController controller = new MainController(view);

    }
}
