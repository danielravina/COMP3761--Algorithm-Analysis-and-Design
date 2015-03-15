// Assignment 6
// Daniel Ravina
// A00844542

import java.io.File;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
  public static int[] inOrder;
  public static int[] postOrder;
  public static Random rand = new Random();

  public static void main(String[] args) {
    // Question 1
    System.out.println();
    System.out.println("Question 1");
    System.out.println("===========");
    buidInOrderAndPostOrder();
    BinaryTree binaryTree = new BinaryTree( inOrder, postOrder );
    binaryTree.displayPreorderList();

    // Question 2
    System.out.println();
    System.out.println("Question 2");
    System.out.println("===========");
    int[] randomArray = buildRandomArray(1000);
    randomArray = heapify(randomArray);
    randomArray = heapSort(randomArray, randomArray.length);

    System.out.println("Is Array sorted? (random array of 1000 integers");
    System.out.println(isSorted(randomArray));
    System.out.println("My Heap sort doesn't work for large arrays.");


    // Question 3
    int[] test2 = {90,85,80,40,65,55,0};
    System.out.println();
    System.out.println("Question 3");
    System.out.println("===========");
    System.out.println("Testing with a heap");
    printArray(test2);
    System.out.println("is it a heap? " + isHeap(test2, 0, test2.length - 1, true));
    System.out.println("Adding 100 to the heap...");
    test2 = addNode(test2, 0, 5, 100);
    System.out.println("is it still a heap? " + isHeap(test2, 0, test2.length - 1, true));

  }

  public static void printArray(int[] array) {
    System.out.println();
      System.out.print("[");
    for(int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
      System.out.print("]");
      System.out.println();
  }

  public static int[] heapify(int[] array) {

    int swapper = 0;
    for(int i = 1; i < array.length; i++) {
      int parent_index = (i - 1)/2;
      while(array[parent_index] < array[i]) {
        swapper = array[i];
        array[i] = array[parent_index];
        array[parent_index] = swapper;
        i            = parent_index;
        parent_index = (i - 1)/2;
      }
    }
    return array;
  }

  public static int[] heapSort(int[] heap, int n) {
    int i = 0;
    int j = 0;
    int temp = heap[i];

    if (i == n-1) {
      heap[i] = heap[n];
      heap[n] = temp;
    } else {
      heap[i]   = heap[n-1];
      heap[n-1] = temp;
    }

    while(i < n) {
      int leftNode  = (i*2)+1;
      int rightNode = (i*2)+2;
      if (leftNode >= n || rightNode >= n -1) {
        break;
      }


      if (heap[leftNode] >= heap[rightNode]) {
        j = leftNode;
      } else {
        j = rightNode;
      }

      temp    = heap[i];
      heap[i] = heap[j];
      heap[j] = temp;
      i       = j;
    }

    if(n > 1) {
      heapSort(heap, --n);
    }

    return heap;
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

  public static int[] addNode(int[] heap, int start, int currentEnd, int newItem) {
    int new_index   = currentEnd+1;
    heap[new_index] = newItem;
    int parent_index      = (new_index - 1)/2;
    while(heap[new_index] < heap[parent_index]) {
      int temp      = heap[new_index];
      heap[new_index] = heap[parent_index];
      heap[parent_index]  = temp;
      new_index    = parent_index;
      parent_index = (parent_index - 1)/2;
    }
    return heap;
  }

  public static boolean isHeap(int[] heap, int start, int end, boolean isMax) {
    // left   (i * 2)  + 1
    // right  (i * 2)  + 2
    // parent (i - 1)/2
    for (int i = start; i < end; ++i) {
      int root = heap[i];
      int leftNode  = (i * 2) + 1;
      int rightNode = (i * 2) + 2;
      if(isMax) {
        if(root < leftNode || root < rightNode) {
          return false;
        }
      } else {
        if(root > leftNode || root > rightNode) {
          return false;
        }
      }
    }
    return true;
  }

  public static int[] buildRandomArray(int n) {
    int[] array = new int[n];
    for(int i = 0; i < n; i++) {
      array[i] = randInt(0, 1000);
    }
    return array;
  }

  public static void buidInOrderAndPostOrder()  {
    try {
      Scanner file   = new Scanner(new File("tree.txt"));
      String[] lines = new String[3];
      int i          = 0;
      while(file.hasNextLine()) {
        String line     = file.nextLine();
        Scanner scanner = new Scanner(line);
        lines[i]        = line;
        i++;
        scanner.close();
      }

      int n = Integer.parseInt(lines[0]);
      inOrder   = new int[n];
      postOrder = new int[n];
      String[] inOrderValues   = lines[1].split("\\s+");
      String[] postOrderValues = lines[2].split("\\s+");

      for (int j = 0 ;j < inOrderValues.length; j++) {
        inOrder[j]   = Integer.parseInt(inOrderValues[j]);
        postOrder[j] = Integer.parseInt(postOrderValues[j]);
      }

      file.close();
    } catch(FileNotFoundException e) {
      System.out.println("Error: File Not Found...Exiting");
      System.exit(-1);
    }
  }
  public static int randInt(int min, int max) {
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }

}