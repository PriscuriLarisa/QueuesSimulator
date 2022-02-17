package queueSimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<ServerQueue> queues;
    private Integer maxNoQueues;
    private Integer maxClientsPerQueue;
    private Thread[] threads;
    private Strategy strategy;

    public Scheduler(Integer maxNoQueues, Integer maxClientsPerQueue) {
        strategy = new ConcreteStrategyTime();
        this.maxNoQueues = maxNoQueues;
        this.maxClientsPerQueue = maxClientsPerQueue;

        threads = new Thread[maxNoQueues];
        queues = new ArrayList<>();

        for(int i=0;i<maxNoQueues;i++) {
            queues.add(new ServerQueue(maxClientsPerQueue));

            threads[i] = new Thread(queues.get(i));
            threads[i].start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
        else if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
    }

    public void killThreads(){
        for(Thread tmp : threads){
            tmp.interrupt();
        }
    }

    public int dispatchClient(Client client) {
        return strategy.addTask(getQueues(), client);
    }

    public List<ServerQueue> getQueues() {
        return queues;
    }

    public void setQueues(List<ServerQueue> queues) {
        this.queues = queues;
    }
}

