import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

class LabTwo {
  static int operationCount = 0;
  public static void main(String[] args) {
    System.out.print("Enter a size (n) for the array: ");
    Scanner nReader = new Scanner(System.in);
    int n               = nReader.nextInt();
    int[] sortedArray   = buildSortedArray(n);
    int[] reversedArray = buildReversedArray(n);
    int[] randomArray   = buildRandomArray(n);

    System.out.println("Sorted Array");
    printArray(sortedArray);
    secret(sortedArray, n);
    printArray(sortedArray);
    System.out.println("operationCount is: " + operationCount);
    operationCount = 0;
    System.out.println("Reversed Array");
    printArray(reversedArray);
    secret(reversedArray, n);
    printArray(reversedArray);
    System.out.println("operationCount is: " + operationCount);
    operationCount = 0;

    System.out.println("Random Array");
    printArray(randomArray);
    secret(randomArray, n);
    printArray(randomArray);
    System.out.println("operationCount is: " + operationCount);
    operationCount = 0;
  }

  public static void secret(int[] array, int length) {

    int last = length - 1;
    if (last == 0) {
      return;
    }
    secret(array, length-1);
    int temp = array[last];
    int pos = last -1;
    while (pos >= 0) {
      operationCount++;
      if(array[pos] > temp) {
        array[pos +1] = array[pos];
      } else { break; }
      pos --;
    }
    array[pos+1] = temp;
    // System.out.println("operationCount is: " + operationCount);
  }

  public static int[] buildRandomArray(int n) {
    int[] array = new int[n];
    for(int i = 0; i < n; i++) {
      array[i] = randInt(0, 1000);
    }
    return array;
  }

  public static int[] buildSortedArray(int n) {
    int[] array = new int[n];
    for(int i = 0; i < n; i++) {
      array[i] = i;
    }
    return array;
  }
  public static int[] buildReversedArray (int n) {
    int[] array = new int[n];
    int j = 0;
    for(int i = n-1; i >= 0; i--) {
      array[j] = i;
      j++;
    }
    return array;
  }

  public static int randInt(int min, int max) {
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }

  public static void printArray(int[] array) {
    System.out.println( Arrays.toString( array ) );
  }
}