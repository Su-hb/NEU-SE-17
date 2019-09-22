public class Job {
    private String user;
    private int number_of_pages;

    public Job() {
    }

    public Job(String user, int number_of_pages) {
        this.user = user;
        this.number_of_pages = number_of_pages;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }
}
