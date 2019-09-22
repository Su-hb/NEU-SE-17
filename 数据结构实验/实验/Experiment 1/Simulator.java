import java.util.Queue;

public class Simulator {
    private int seconds_per_page;
    private Queue<Event> workload;

    public Simulator(int seconds_per_page, Queue<Event> workload) {
        this.seconds_per_page = seconds_per_page;
        this.workload = workload;
    }

    public int getSeconds_per_page() {
        return seconds_per_page;
    }

    public void setSeconds_per_page(int seconds_per_page) {
        this.seconds_per_page = seconds_per_page;
    }

    public Queue<Event> getWorkload() {
        return workload;
    }

    public void setWorkload(Queue<Event> workload) {
        this.workload = workload;
    }
}

