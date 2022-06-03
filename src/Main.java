import java.util.Random;
import java.util.Scanner;

public class Main
{
    static boolean queue_cond = true;

    public static void main(String args[])
    {
        Random priority = new Random();
        System.out.println("Enter number of threads:");
        Scanner input = new Scanner(System.in);
        int threadsCount = input.nextInt();
        Producer [] producers= new Producer [threadsCount];
        for (int i=0;i<threadsCount; i++)
        {
            producers[i] = new Producer();
            producers[i].x= priority.nextInt(300);
            producers[i].number=i+1;
        }
        while (queue_cond)
        {
            int curr=-1;
            for (int i=0;i<threadsCount;i++)
            {
                for (int j=0;j<threadsCount;j++)
                {
                    if (producers[i].x<producers[j].x)
                    {
                        Producer producer=producers[i];
                        producers[i]=producers[j];
                        producers[j]=producer;
                        curr=producers[j].number;
                    }
                }
            }
            producers[threadsCount-1].startProducer();
            System.out.println("Thread №"+ curr + " is running");
            for (int i=0;i<threadsCount; i++)
            {
                producers[i] = new Producer();
                producers[i].x= priority.nextInt(300);
                producers[i].number=i+1;
            }
            System.out.println("Thread №" + curr + " is done");
            Producer.sleep1();
        }
    }
    static class Producer extends MyThread
    {   int x;
        int number;
        public void startProducer()
        {
            Thread f = new Thread();
            f.start();
        }
        public static void sleep1()
        {
            try{sleep(100);}
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        public static void sleep2()
        {
            try{sleep(100000);}
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

    }
    static class MyThread extends Thread
    {
        public static void sleep1()
        {
            try{sleep(100);}
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

