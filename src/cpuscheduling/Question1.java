
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question1 {
    
    public static void main(String[] args) {
        // Process command line arguments
        // read the file
        
        
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        
        
        String filename ;
        String allocationStrategy;
        int quantum=20;
        
        /*filename = args[0];
        allocationStrategy = args[1];*/
        
        filename = "testing.txt";
        allocationStrategy = "FCFS";
        
        
        //filename = sc.nextLine();
        
        if(args.length==3){
            quantum = new Integer(args[2]);
        }
        
        BufferedReader br = null;
        
        try {
            
            String sCurrentLine;
            
            br = new BufferedReader(new FileReader("C://Users/Arnav/Desktop/"+filename));
            //System.out.println("processId  arrivalTime  cpuTime");
            
            List<Job> jobList = new ArrayList<Job>();
            while ((sCurrentLine = br.readLine()) != null) {
                
                String a[] = sCurrentLine.split(",");
                int processId = new Integer(a[0]);
                int arrivalTime = new Integer(a[1]);
                int cpuTime = new Integer(a[2]);
                
                Job job = new Job(processId,arrivalTime,cpuTime);
                
                jobList.add(job);
                
                //System.out.println(processId+" "+ arrivalTime+" " + cpuTime);
            }
            
            if("FCFS".equalsIgnoreCase(allocationStrategy)){
                
                FirstComeFirstServed firstComeFirstServed = new FirstComeFirstServed(jobList);
                firstComeFirstServed.run(jobList);
                
                }else if("SRT".equalsIgnoreCase(allocationStrategy)){
                
                ShortestRemainingTime shortestRemainingTime = new ShortestRemainingTime(jobList);
                shortestRemainingTime.run(jobList);
                
                }else if("RR".equalsIgnoreCase(allocationStrategy)){
                
                RoundRobin roundRobin = new RoundRobin();
                roundRobin.run(jobList,quantum);
                
            }
            
            } catch (IOException e) {
            e.printStackTrace();
            } finally {
            try {
                if (br != null)br.close();
                } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        JobFinishEvent callback = new JobFinishEvent() {
            @Override
            public void onFinish(Job j) {
                // this will be called when a job is finished.
            }
        };
        

    }
    
}