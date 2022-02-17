package queueSimulator.model;

import queueSimulator.view.SimulationView;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable{

    private Integer timeLimit;
    private Integer maxProcessingTime;
    private Integer minProcessingTime;
    private Integer numberOfServers;
    private Integer numberOfClients;
    private Integer minArrivalTime;
    private Integer maxArrivalTime;


    private Scheduler scheduler;
    private List<Client> generatedClients;
    private SimulationView view;

    private PrintWriter pw;

    private int totalWaitingTime;
    private int totalServiceTime;
    private int clientsDispatched;
    private int peekHour;
    private int maxClients;

    public SimulationManager(SimulationView view, Integer timeLimit, Integer numberOfServers, Integer numberOfClients, Integer minArrivalTime, Integer maxArrivalTime, Integer minProcessingTime,Integer maxProcessingTime,  PrintWriter pw) {
        setSimulationData(timeLimit, maxProcessingTime, minProcessingTime, minArrivalTime, maxArrivalTime, numberOfServers, numberOfClients);
        this.view = view;
        this.scheduler = new Scheduler(this.numberOfServers, this.numberOfClients);
        this.pw = pw;
        generatedClients = new ArrayList<>();
        generateNRandomClients();

    }

    @Override
    public void run() {
        int currentTime = 0;
        AtomicInteger ct = new AtomicInteger(0);
        while(currentTime <= this.timeLimit) {
            System.out.println(currentTime);
            for(Client tmp : generatedClients) {
                if (tmp.getArrivalTime().equals(currentTime)) {
                    totalWaitingTime+=scheduler.dispatchClient(tmp);
                    totalServiceTime+=tmp.getProcessingTime();
                    clientsDispatched++;
                }
            }
            ct.set(currentTime);
            if(!generatedClients.isEmpty())
                generatedClients.removeIf(c -> (c.getArrivalTime().equals(ct.get())));
            int currentClients = 0;
            for(ServerQueue queue : scheduler.getQueues()) {
                currentClients+=queue.getClients().length;
            }
            if(currentClients > maxClients) {
                this.peekHour = currentTime;
                maxClients = currentClients;
            }
            updateTxt(currentTime);
            updateGUI(currentTime);
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Can't sleep :(");
            }
        }
        pw.println("\nAverage waiting time: " + 1.0f * totalWaitingTime/clientsDispatched);
        pw.println("Average service time: " + 1.0f * totalServiceTime/clientsDispatched);
        pw.println("Peek hour: " + peekHour + " with " + maxClients + " in total.");
        pw.flush();
        updateDone();
        scheduler.killThreads();
    }

    public void setSimulationData(Integer timeLimit, Integer maxProcessingTime, Integer minProcessingTime,Integer minArrivalTime, Integer maxArrivalTime, Integer numberOfServers, Integer numberOfClients) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
    }

    private void generateNRandomClients() {
        for(int i=0;i<numberOfClients;i++) {
            Integer arrivalTime = getRandomNumberUsingInts(minArrivalTime, maxArrivalTime);
            Integer processingTime = getRandomNumberUsingInts(minProcessingTime, maxProcessingTime);
            this.generatedClients.add(new Client(arrivalTime, processingTime));
        }

        Collections.sort(generatedClients, (a, b) -> a.compareTo(b));
        int i=1;
        for(Client tmp : generatedClients) {
            tmp.setId(i);
            i++;
        }

        for(Client tmp : generatedClients)
            System.out.println(tmp.toString());
    }

    public int getRandomNumberUsingInts(Integer min, Integer max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    public void updateTxt(Integer time) {
        int i = 1;
        pw.println("\nTime " + time);
        pw.println("Waiting clients: ");
        for(Client client : generatedClients)
            pw.print(client.toString());
        pw.print("\n");

        for(ServerQueue queue : scheduler.getQueues()) {
            pw.print("Queue: " + i + ": ");
            if(queue.getClients().length == 0) {
                pw.print("Empty");
            }
            else {
                for (Client client : queue.getClients()) {
                    pw.print(client.toString());
                }
            }
            pw.print("\n");
            i++;
        }
        pw.flush();

    }

    public void updateGUI(int time){
        String resultedString = new String();


        resultedString = resultedString + "  Time : " + time + "\n   ";
        for(Client client : generatedClients) {
            resultedString = resultedString + "(" + client.getId() + ") ";
        }
        resultedString = resultedString + "\n";
        int i = 1;
        for(ServerQueue queue : scheduler.getQueues()) {
            resultedString = resultedString + "\n"+ "   Queue " + i + ": ";
            i++;
            for(Client client : queue.getClients()) {
                resultedString = resultedString + "(" + client.getId() + ") ";
            }
        }

        view.setUpdated(resultedString);
    }

    public void updateDone(){
        view.setUpdated(new String("  Done!"));
        System.out.println("Done!");
    }

}

