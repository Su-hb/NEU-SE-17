package SPFA;


import java.io.*;
import java.util.*;

public class EUtravel {

    public  long[] result;
    public  long[] cost;
    public  ArrayList<Integer> FPath  = null;
    public  ArrayList<Integer> EPath  = null;

    //内部类
    static class  edge{
        public int from;//起点
        public int end;//终点
        public int cost;//花费
        public int len;//长度

        public edge(int from, int end, int len, int cost) {
            this.from = from;
            this.end = end;
            this.cost = cost;
            this.len = len;
        }
    }

    public  boolean getShortestPaths(int n,int s,edge[] A){
        FPath = new ArrayList<>();
        EPath = new ArrayList<>();

        ArrayList<Integer> list = new ArrayList<>();
        result = new long[n];
        cost = new long[n];
        boolean[] used = new boolean[n];
        int[] num = new int[n];

        //初始化
        for (int i = 0;i<n;i++){
            cost[i] = Integer.MAX_VALUE;
            result[i] = 0;
            used[i] = false;
        }

        cost[s] = 0;     //第s个顶点到自身距离为0
        used[s] = true;    //表示第s个顶点进入数组队
        num[s] = 1;       //表示第s个顶点已被遍历一次
        list.add(s);      //第s个顶点入队
        while(list.size() != 0) {
            int a = list.get(0);   //获取数组队中第一个元素

            list.remove(0);  //删除数组队中第一个元素
            for(int i = 0;i < A.length;i++) {
                //当list数组队的第一个元素等于边A[i]的起点时
                if(a == A[i].from && cost[A[i].end] > cost[A[i].from] + A[i].cost) {
                    cost[A[i].end] = cost[A[i].from] + A[i].cost;
                    result[A[i].end] = result[A[i].from]+A[i].len;

                    if(!used[A[i].end]) {
                        list.add(A[i].end);

                        FPath.add(A[i].from);
                        EPath.add(A[i].end);

                        num[A[i].end]++;
                        if(num[A[i].end] > n)
                            return false;
                        used[A[i].end] = true;   //表示边A[i]的终点b已进入数组队
                    }
                }
            }
            used[a] = false;        //顶点a出数组对
        }
        return true;
    }


    public HashMap<String,Integer> dic = new HashMap<>();
    public HashMap<Integer,String> anti = new HashMap<>();

    public static void main(String[] args) {

        EUtravel m = new EUtravel();
        File f= new File("services.txt");

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String strr = null;
            int count = 0;
            int n = 0;

            edge[] A= new edge[54];
            System.out.print("城市  ");
            while ((strr = br.readLine())!=null){

                String[] sp = strr.split(" ");
                if(!m.dic.containsKey(sp[0])){
                    count+=1;
                    m.dic.put(sp[0], count);
                    m.anti.put(count,sp[0]);
                    System.out.print(sp[0]+"\t");
                }
                if(!m.dic.containsKey(sp[1])){
                    count+=1;
                    System.out.print(sp[1]+"\t");
                    m.dic.put(sp[1], count);
                    m.anti.put(count,sp[1]);
                }

                A[n] = new edge(m.dic.get(sp[0]),m.dic.get(sp[1]),Integer.parseInt(sp[3]),Integer.parseInt(sp[2]));

                n+=1;


        }
            Scanner sc = new Scanner(System.in);
            System.out.print("\n起始点:");
            String start = sc.nextLine();
            int Start = m.dic.get(start);
            System.out.print("\n目的地:");
            String end = sc.nextLine();
        if (m.getShortestPaths(n,Start,A)){

            int End = m.dic.get(end);
            int tmpStart = Start;
            int tmpEnd = End;

            ArrayList<String> resPath = new ArrayList<>();
            resPath.add(end);
            while (tmpEnd != tmpStart){
                for(int i = m.EPath.size()-1;i > -1;i--){
                    //System.out.println(m.FPath.get(i)+"-->"+m.EPath.get(i));
                    if(tmpEnd == m.EPath.get(i)){
                        //System.out.println(m.FPath.get(i)+"-->"+m.EPath.get(i));
                        tmpEnd = m.FPath.get(i);
                        resPath.add(m.anti.get(tmpEnd));
                        break;
                    }
                }

            }
            System.out.print("路线为：");
            for(int i = resPath.size()-1;i > 0;i--){
                System.out.print(resPath.get(i)+"-->");
            }
            System.out.println(resPath.get(0));
            System.out.println("从 "+start +"到"+ end+"距离为"+m.cost[End]+"花钱为"+m.result[End] );

        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
