public class Event {
    private Job j;
    private int arrival_time;

    public Event(Job j, int arrival_time) {
        this.j = j;
        this.arrival_time = arrival_time;
    }

    public Event() {
    }

    public Job getJ() {
        return j;
    }

    public void setJ(Job j) {
        this.j = j;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }
}
