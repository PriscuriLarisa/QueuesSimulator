package queueSimulator.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerQueue implements Runnable {

    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;


    public ServerQueue(Integer N) {
        clients = new LinkedBlockingQueue<>(N);
        waitingPeriod = new AtomicInteger();
        waitingPeriod.set(0);
    }

    public void addClient(Client client) {
        clients.offer(client);
        waitingPeriod.set(waitingPeriod.get() + client.getProcessingTime());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Client tmp = new Client(0, 0);
            if (!clients.isEmpty()) {
                for(int i=0; i<clients.peek().getProcessingTime();i++) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        System.out.print("");
                    }
                    waitingPeriod.set(waitingPeriod.get()-1);

                }
                try {
                    tmp = clients.take();
                } catch (InterruptedException e) {
                    System.out.println("Waiting for clients.. (1)");
                }

            }
        }
    }


    public Client[] getClients() {
        Object[] clientsArray = this.clients.toArray();
        Client[] resultedArray = new Client[clientsArray.length];
        for(int i=0;i< clientsArray.length;i++)
            resultedArray[i] = (Client)clientsArray[i];

        return resultedArray;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }
}

