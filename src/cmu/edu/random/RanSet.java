package cmu.edu.random;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class RanSet {

  public static final int THREADNUM = 4;//Numbers of CPU in my computer
  ArrayBlockingQueue<Byte> output = new ArrayBlockingQueue<Byte>(1000000);
  ArrayList<RandomThread> threads = new ArrayList<RandomThread>();
  boolean isRunning = false;
  
  double mean = 0.5;
  long total = 10000;
  long positive = 5000;
  
  public ArrayBlockingQueue<Byte> getOutput() {
    return output;
  }
  
  public void setOutput(ArrayBlockingQueue<Byte> output) {
    this.output = output;
  }
  
  public ArrayList<RandomThread> getThreads() {
    return threads;
  }
  
  public void setThreads(ArrayList<RandomThread> threads) {
    this.threads = threads;
  }
  
  public boolean isRunning() {
    return isRunning;
  }
  
  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }
  
  public void start(){
    isRunning = true;
    for (int i = 0; i < THREADNUM; i++) {
      threads.add(new RandomThread(this));
    }
    for (RandomThread t : threads) {
      t.start();      
    }
  }
  
  
  public int next() {
    while (output.isEmpty()) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return output.poll().intValue();
  }
  
  public void stop() {
    isRunning = false;
  }
  
  public long getTotal() {
    return total;
  }
  public void addTotal() {
    total++;
  }
  
  public void addPositive() {
    positive++;
  }
  
  public void updateMean() {
    mean = (double)positive/total;
  }
  
  public double getUpdateMean() {
    return mean;
  }
  
  

}
