package queueSimulator.model;

import java.util.List;

public interface Strategy {
    public int addTask(List<ServerQueue> servers, Client client);
}
