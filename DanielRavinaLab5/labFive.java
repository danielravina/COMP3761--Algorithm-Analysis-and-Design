import java.io.File;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class LabFive {

  public static int comparisonCount;
  public static Random rand = new Random();

  public static void main (String[] args) throws FileNotFoundException {
    int[] integerArray1000   = buildArray((new int[1000]), "integerArray_1000.txt");
    int[] integerArray10000  = buildArray((new int[10000]), "integerArray_10000.txt");
    int[] integerArray100000 = buildArray((new int[100000]), "integerArray_100000.txt");

    System.out.println("Question 1:");
    System.out.println("===============================================");
    lomutoPartitionWithLastPivot(integerArray1000, 0, integerArray1000.length - 1);
    int answer = 0;
    answer = quickSelectNonRecursive(integerArray1000, 0, integerArray1000.length-1);
    System.out.println("Median For integerArray1000 is: " + answer);
    answer = quickSelectNonRecursive(integerArray10000, 0, integerArray10000.length-1);
    System.out.println("Median For integerArray10000 is: " + answer);
    answer = quickSelectNonRecursive(integerArray100000, 0, integerArray100000.length - 1);
    System.out.println("Median For integerArray100000 is: " + answer);
    System.out.println("");

    System.out.println("Question 2");
    System.out.println("===============================================");
    System.out.println("---------- Assignment 1 ----------");
    System.out.println("integerArray10000:");
    System.out.println("  inversion count:  " + assignment1inversionCount(integerArray1000));
    System.out.println("  comparison count: " + comparisonCount);
    System.out.println("integerArray100000:");
    System.out.println("  inversion count:  " + assignment1inversionCount(integerArray10000));
    System.out.println("  comparison count: " + comparisonCount);
    System.out.println("integerArray1000000:");
    System.out.println("  inversion count:  " + assignment1inversionCount(integerArray100000));
    System.out.println("  comparison count: " + comparisonCount);
    System.out.println("---------- Assignment 5 ----------");
    System.out.println("integerArray1000:");
    System.out.println("  inversion count:  " + inversionCount(integerArray1000));
    System.out.println("  comparison count: " + comparisonCount);
    System.out.println("integerArray10000:");
    System.out.println("  inversion count:  " + inversionCount(integerArray10000));
    System.out.println("  comparison count: " + comparisonCount);
    System.out.println("integerArray100000:");
    System.out.println("  inversion count:  " + inversionCount(integerArray100000));
    System.out.println("  comparison count: " + comparisonCount);
    System.out.println("");

    System.out.println("Question 3:");
    System.out.println("===============================================");
    System.out.println("");
    System.out.println("---------- First Pivot ----------");
    runQuickSort("first");
    System.out.println("---------- Random Pivot ----------");
    runQuickSort("random");
    System.out.println("---------- Median Pivot ----------");
    runQuickSort("median");



  }

  public static void printSummary(int list[], String name){
    System.out.println("    ---------- "+ name +" Array ----------");
    System.out.println("    Array Sorted?          " + isSorted(list));
    System.out.println("    Number of comparisons: " + comparisonCount);
  }

  public static void runQuickSort(String pivotType) {
    int n           = 10000;

    comparisonCount = 0;
    int[] rand = buildRandomArray(n);
    rand = quickSort(rand, 0, n - 1, pivotType);
    printSummary(rand, "Random");

    comparisonCount = 0;
    int[] rev = buildReversedArray(n);
    rev = quickSort(rev, 0, rev.length-1, pivotType);
    printSummary(rev, "Reversed");

    comparisonCount = 0;
    // don't need to build a new one. Simply use the previous one
    int[] sorted = quickSort(rev, 0, n - 1, pivotType);
    printSummary(sorted, "Sorted");
  }

  public static int lomutoPartitionWithLastPivot(int[] list, int l, int  r) {
    // pivot
    int p = list[l];
    // the border between the smaller than pivot and larger than pivot
    int i = r - 1;
    // just a helper for swap
    int swapper;

    for(int j = i; j >= l; --j) {
      if( list[j] > p ) {
        swapper = list[i];
        list[i] = list[j];
        list[j] = swapper;
        --i;
      }
    }
    swapper   = list[i+1];
    list[i+1] = list[r];
    list[r]   = swapper;
    return i + 1;
  }

  public static int quickSelectNonRecursive (int[] list, int l, int k) {
    int index = 0;
    int mid = (l+k)/2;
    index = lomutoPartitionWithLastPivot(list,l,k);
    while( index != mid){
      if(index < mid) {
        index = lomutoPartitionWithLastPivot(list,mid,k);
      }
       else{ index = lomutoPartitionWithLastPivot(list,l,mid); }
    }
    return index;
  }

  public static int[] quickSort(int list[], int left, int right, String pivotType) {
    int pivot,swapper,i,j;

    i = left;
    j = right;
    switch(pivotType) {
      case "random":
        pivot = list[randInt(left, right)];
        break;
      case "first":
        pivot = list[left+1];
        break;
      case "median":
        pivot = list[(left + right) / 2];
        break;
      default:
        pivot = list[left];
    }

    do {
      while(list[i] < pivot) {
          i++;
      }
      while(pivot < list[j]) {
          j--;
      }
      comparisonCount++;
      if (i <= j) {
        swapper = list[i];
        list[i] = list[j];
        list[j] = swapper;
        i++;
        j--;
      }
    } while (i <= j);
    if (left < j) {
      quickSort(list,left,j, pivotType);
    }
    if (i < right) {
      quickSort(list,i,right, pivotType);
    }

    return list;
  }

  public static int inversionCount(int list[]) {
      comparisonCount = 0;
      int n = list.length;
      return mergeSort(list, 0, n - 1);
  }

  public static int mergeSort(int list[], int start, int end) {
      int middle;

      if(start >= end) return 0;
      middle = (start + end) / 2;

      return  mergeSort(list, start, middle) +
              mergeSort(list, middle+1, end) +
              merge(list, start, middle, end);
  }

  public static int merge(int list[], int start, int middle, int end) {
      int temp[] = new int[end+1];
      int inv_count = 0;
      int i = start;
      int j = end;
      int k = start;

      for(i = start; i <= middle; i++) {
        temp[i] = list[i];
      }

      for(j = end; i <= end; i++, j--) {
        temp[i] = list[j];
      }

      for(j = end, i = k; k <= end; k++) {
        comparisonCount++;
        if(temp[i] <= temp[j]) {
          list[k] = temp[i++];
        }
        else {
          list[k] = temp[j--];
          inv_count = inv_count + (middle - i + 1);
        }
      }
      return inv_count;
  }


  public static int assignment1inversionCount(int[] list) {
    comparisonCount = 0;
    int inv_count = 0;
    int n                 = list.length;
    for(int i = 0; i < n-1; i++) {
      for(int j = i+1; j < n; j++) {
        comparisonCount++;
        if(list[i] > list[j]) {
          inv_count++;
        }
      }
    }
    return inv_count;
  }


  /* Util Functions */


  public static boolean isSorted(int[] list) {
    boolean sorted = true;
    for (int i = 0; i < list.length - 1; i++) {
      if (list[i] > list[i+1]) {
          sorted = false;
          break;
      }
    }
    return sorted;
  }

  public static int[] buildRandomArray(int n) {
    int[] array = new int[n];
    for(int i = 0; i < n; i++) {
      array[i] = randInt(0, 1000);
    }
    return array;
  }

  public static int[] buildReversedArray(int n) {
    int[] array = new int[n];
    int j = 0;
    for(int i = n; i >= 1; i--) {
      array[j] = i;
      j++;
    }
    return array;
  }

  public static int randInt(int min, int max) {
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }

  public static int[] buildArray(int[] list, String filename) throws FileNotFoundException {
    try {
      Scanner file = new Scanner(new File(filename));
      int i = 0;
      while(file.hasNextLine()) {
        String line = file.nextLine();
        Scanner scanner = new Scanner(line);
        list[i] = scanner.nextInt();
        scanner.close();
        ++i;
      }
      file.close();
    } catch(FileNotFoundException e) {
      System.out.println("Error: File Not Found...Exiting");
      System.exit(-1);
    }
    return list;
  }

}



