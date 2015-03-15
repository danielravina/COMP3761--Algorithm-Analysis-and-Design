import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class BubbleSwapIndicator
{
  public static long numberOfSwaps            = 0;
  public static long numberOfElemntComparison = 0;

  public static void main (String[] args) throws FileNotFoundException {
    ArrayList<Integer> integerArray1000        = new ArrayList<Integer>();
    ArrayList<Integer> integerArray10000       = new ArrayList<Integer>();
    ArrayList<Integer> integerArray100000      = new ArrayList<Integer>();

    integerArray1000   = buildArray(integerArray1000, "integerArray_1000.txt");
    integerArray10000  = buildArray(integerArray10000, "integerArray_10000.txt");
    integerArray100000 = buildArray(integerArray100000, "integerArray_100000.txt");

    System.out.println("BubbleSwapIndicator:");
    System.out.println("--------------------------------------");

    System.out.println("Sorting integerArray1000");
    bubbleSort(integerArray1000);
    printSummary();

    System.out.println("Sorting integerArray10000");
    bubbleSort(integerArray10000);
    printSummary();

    System.out.println("Sorting integerArray100000");
    bubbleSort(integerArray100000);
    printSummary();
  }

  public static void bubbleSort(ArrayList<Integer> list) {
    int n  = list.size();
    boolean didSwap = false;
    int temp;
    for (int i = 0; i < (n - 1); i++) {
      for (int j = 0; (j < (n - 1)); j++) {
        numberOfElemntComparison++;
        if ( list.get(j+1) < list.get(j) ) {
        numberOfSwaps++;
          didSwap = true;
          temp = list.get(j);
          list.set(j, list.get(j+1));
          list.set(j+1, temp);
        }
      }
      if(didSwap == false) { return; }
    }

  }


  public static ArrayList<Integer> buildArray(ArrayList<Integer> list, String filename) throws FileNotFoundException {
    try {
      Scanner file = new Scanner(new File(filename));
      while(file.hasNextLine()) {
        String line = file.nextLine();
        Scanner scanner = new Scanner(line);
        list.add(scanner.nextInt());
        scanner.close();
      }
      file.close();
    } catch(FileNotFoundException e) {
      System.out.println("Error: File Not Found...Exiting");
      System.exit(-1);
    }
    return list;
  }

  public static void printSummary() {
    System.out.println("Number of Swaps:        " + numberOfSwaps);
    System.out.println("Number of Comparisons:  " + numberOfElemntComparison);
    System.out.println("--------------------------------------");
    numberOfSwaps            = 0;
    numberOfElemntComparison = 0;
  }
}