import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class LabOne
{
  public static void main (String[] args) throws FileNotFoundException {
    ArrayList<Integer> list = new ArrayList<Integer>();

    list = buildArray(list);
    inversionCount(list);
  }

  /**
  * Iterate through an array list of arbitrary numbers
  * and print the number of inversions and the number
  * of element comparisons.
  * For example, given an array {1, 3, 2}, the pair
  * of numbers (3, 2) is considered as an inversion.
  * @param An ArrayList of n integer numbers
  */
  public static void inversionCount(ArrayList<Integer> list) {
    int theInversionCount = 0;
    int loopCount         = 0;
    int n                 = list.size();
    for(int i = 0; i < n-1; i++) {
      for(int j = i+1; j < n; j++) {
        loopCount++;
        if(list.get(i) > list.get(j)) {
          theInversionCount++;
        }
      }
    }
    System.out.println("Number of inversions: " + theInversionCount);
    System.out.println("Number of element comparisons:  " + loopCount);
  }

  /**
  * Prompts the user for a test input file name,
  * reads in all the integers from it and constructs an input
  * array instance for the inversionCount method
  * @param An empty ArrayList that accepts int values
  * @return Populated list with integers from the input file
  */
  public static ArrayList<Integer> buildArray(ArrayList<Integer> list) throws FileNotFoundException {
    System.out.print("Enter a file name: ");
    Scanner filenameReader = new Scanner(System.in);
    String filename        = filenameReader.nextLine();
    try {
      Scanner file           = new Scanner(new File(filename));
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

}