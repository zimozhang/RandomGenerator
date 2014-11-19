package cmu.edu.random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class RandomGenerator {

  public static void main(String[] args) {
    String input = getInput("Enter the path for saving random table: ");
    String path = input;

    String number = getInput("Enter number of random number you want: ");
    long k = Long.parseLong(number);
    System.out.println("Please generate " + k + " random numbers and save to " + path);

    System.out.println(path);
    System.out.println(k);

    File f = new File(path);
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(f);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    RanSet rand = new RanSet();
    rand.start();
    long count = 0;

    while (count < k) {
      int next = rand.next();
      writer.println(String.format("%d", next));

      count++;
    }
    //writer.print(String.format("%d", rand.next()));
    writer.flush();
    rand.stop();
    writer.close();

  }

  private static String getInput(String prompt) {
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    System.out.print(prompt);
    System.out.flush();

    try {
      return stdin.readLine();
    } catch (Exception e) {
      return "Error: " + e.getMessage();
    }
  }

}
