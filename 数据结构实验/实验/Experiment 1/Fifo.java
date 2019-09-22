import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Fifo extends Simulator{
    public Fifo(int seconds_per_page, Queue<Event> workload) {
        super(seconds_per_page, workload);
    }
    public static double formatDouble1(double d) {
        return (double)Math.round(d*10000)/10000;
    }

    public Queue<Event> ReadFile(String fileName){
        Queue<Event> queue = new LinkedList<>();
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String strr = null;
            while ((strr = br.readLine())!=null){
                String[] tmp;
                tmp = strr.split(" ");
                Job tmpJob = new Job(tmp[2],Integer.parseInt(tmp[1]));
                Event tmpEvent = new Event(tmpJob,Integer.parseInt(tmp[0]));
                queue.add(tmpEvent);
            }
            return queue;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getRes(int Seconds_per_pages,Queue<Event> queue){
        Queue<Event> need = new LinkedList<>();
        String out = "FIFO Simulation \n\n";

        int count = 0;
        int tmp =queue.peek().getArrival_time();//上次打印结束的时间
        int size = queue.size();
        int sum = 0;
        while (!queue.isEmpty() || !need.isEmpty()){
            if(!queue.isEmpty()){
                Event topEvent = queue.peek();
                while (topEvent!= null &&topEvent.getArrival_time() == count){

                    out += "\tArriving: "+topEvent.getJ().getNumber_of_pages()+" pages from "+topEvent.getJ().getUser()+"at "+String.valueOf(count)+" seconds\n";
                    need.add(topEvent);
                    queue.remove(topEvent);
                    topEvent = queue.peek();
                }
            }
            if (!need.isEmpty()){
                Event topNeed = need.peek();
                if(tmp==count){
                    sum+=count-topNeed.getArrival_time();
                    out +="\tServicing: "+topNeed.getJ().getNumber_of_pages()+" pages from "+topNeed.getJ().getUser()+" at "+String.valueOf(tmp)+" seconds\n" ;
                    need.remove(topNeed);
                    tmp+=topNeed.getJ().getNumber_of_pages()*Seconds_per_pages;
                }
            }
            count+=1;
        }
        Double res = formatDouble1(Double.parseDouble(String.valueOf(sum))/Double.parseDouble(String.valueOf(size)));
        out +="\n\tTotal jobs: "+size+"\n\tAggregate latency: "+sum+" seconds \n\tMean latency: "+res+" seconds ";
        System.out.println(out);
        return out;
    }
    public static void main(String[] args) {
        Queue q = new LinkedList();
        Fifo fifo = new Fifo(2,q);
        fifo.getRes(2,fifo.ReadFile("bigfirst.run"));
    }
}
