package cmu.edu.random;

import java.util.Random;

public class RandomThread extends Thread {
  
  static Random random = new Random();
  static final int INSERT = 2;
  RanSet choose;
  
  public RandomThread(RanSet choose) {
    super();
    this.choose = choose;
  }
  
  public void run(){
    int gap = INSERT;
    while(choose.isRunning){
      int result;
      if (gap > 0) {
        double r = random.nextDouble();
        gap--;
        if (r<=choose.getUpdateMean()) {
          result = 0;
        }else{
          result = 1;
        }
     }else{
       result = SystemTime.getTime();
       gap = INSERT;
     }
      
     if (result ==1) {
       choose.addPositive();
     }
     choose.addTotal();
     choose.updateMean();
     
     boolean added = false;
     while (!added) {
       try {
         added = choose.getOutput().add((byte) result);
       } catch (IllegalStateException e) {
         try {
           Thread.sleep(10);
         } catch (InterruptedException e1) {
           e1.printStackTrace();
         }
       }
     }
    }
  }

}
