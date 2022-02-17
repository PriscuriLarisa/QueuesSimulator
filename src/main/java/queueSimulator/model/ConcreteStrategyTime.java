package queueSimulator.model;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public int addTask(List<ServerQueue> servers, Client client) {
        ServerQueue minWaitingTime = servers.get(0);
        for(ServerQueue tmp : servers) {
            if(tmp.getWaitingPeriod().get() < minWaitingTime.getWaitingPeriod().get()) {
                minWaitingTime = tmp;
            }
        }

        minWaitingTime.addClient(client);
        return  minWaitingTime.getWaitingPeriod().get()-client.getProcessingTime();
    }
}
