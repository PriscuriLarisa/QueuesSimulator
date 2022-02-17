package queueSimulator.model;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy{

    @Override
    public int addTask(List<ServerQueue> servers, Client client) {
        ServerQueue minNbClients = servers.get(0);
        for(ServerQueue tmp : servers) {
            if(tmp.getClients().length < minNbClients.getClients().length) {
                minNbClients = tmp;
            }
        }

        minNbClients.addClient(client);
        return  minNbClients.getWaitingPeriod().get() - client.getProcessingTime();
    }
}
