package queueSimulator.model;

public class Client {

    private Integer id;
    private Integer arrivalTime;
    private Integer processingTime;
    private Integer finishTime;

    public Client(Integer arrivalTime, Integer processingTime) {
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Integer processingTime) {
        this.processingTime = processingTime;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public int compareTo(Client toCompare) {
        return this.arrivalTime.compareTo(toCompare.getArrivalTime());
    }

    @Override
    public String toString() {
        return "(" + this.id + ", " + this.arrivalTime + ", " + this.processingTime + "); ";
    }
}

