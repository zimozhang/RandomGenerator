package cmu.edu.random;

public class SystemTime {
  
  public static int getTime() {
    
      long now =System.nanoTime();
      System.out.println(now);

      return (int) (now%2);
  }

}
