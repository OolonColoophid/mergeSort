import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * merge sort
 * This implements the merge sort algorithm
 * together with commentary
 *
 *  One of the clearer expressions of the algorithm is to
 *  be found here: https://www.programiz.com/dsa/merge-sort
 *  and this is what I'm following.
 *
 * It might be helpful to use the example from Wikipedia (the diagram
 * is especially good) and have it alongside while the output
 * is produced.
 *
 * @version 1.0
 * @since 2019-12-29
 */

public class mergeSort {

  // The mergeSort method repeatedly divides the array
  // into two halves until we reach a stage where we try
  // to perform mergeSort on a subarray of size 1 i.e. p == r.
  //
  // a = array (remember this is a pointer, so changes to a are live)
  // p = leftmost index of array
  // r = rightmost index of array
  // q = halfpoint point between p and r
  public static void mergeSort(int a[], int p, int r){

    System.out.println("- FUNC mergeSort starting");
    int[] myArray = Arrays.copyOfRange(a, p, r+1);
    System.out.println("  ...with array " + Arrays.toString(myArray));


    // Base condition
    // i.e. If leftmost and rightmost index are the same
    // when have an array of one element
    if (p == r) {
      System.out.println("  Base condition (array indivisible)");
      System.out.println("  Recursion OUT of FUNC mergeSort following base condition");
      return;
    }


    // q = midpoint
    // e.g. (0 + 4) / 2 = 2
    int q = (p + r)/2;

    System.out.println("  Recursion INTO FUNC mergeSort for left half");
    mergeSort(a, p, q); // split left half down to base condition

    System.out.println("  Recursion INTO FUNC mergeSort right half");
    mergeSort(a, q+1, r); // split right half down to base condition

    System.out.println("  Base condition met for left and right halves!");
    merge(a, p, q, r);

  }

  // The merge method compares sibling arrays and sorts them into
  // one parent array
  public static void merge(int a[], int p, int q, int r){

    System.out.println("- FUNC merge starting");

    //  Create copies of the sub arrays (L is leftmost; M is rightmost)
    int n1 = q - p + 1; // e.g. for 6 element array and p = 1; size is 3 - 1 + 1 = 3
    int n2 = r - q; // e.g. for 6 element and r = 4; size is 2

    int[] L = new int[n1];
    int[] M = new int[n2];

    for (int i = 0; i < n1; i++)
      L[i] = a[p + i];
    for (int j = 0; j < n2; j++)
      M[j] = a[q + 1 + j];

    // Maintain the current index of sub-arrays and main array
    // i maintains current index of L, starting at 1
    // j maintains current index of M, starting at 1
    // k maintains current index of A[p..q], starting at p
    int i = 0;
    int j = 0;
    int k = p;

    // Until we reach either end of either L or M, pick larger among
    // elements L and M and place them in the correct position at A[p..r]
    while (i < n1 && j < n2)
      {

        if (L[i] <= M[j])
          {
            System.out.println("  - " + L[i] + " <= " + M[j] + " so add " + L[i] + " to master array and move left pointer");
            a[k] = L[i];
            i++;
          }
        else
          {
            System.out.println("  - " + L[i] + " > " + M[j] + " so add " + M[j] + " to master array and move right pointer");;
            a[k] = M[j];
            j++;
          }
        k++;
      }

    // When we run out of elements in either L or M,
    // pick up the remaining elements and put in A[p..r]
    while (i < n1)
      {
        a[k] = L[i];
        i++;
        k++;
      }

    while (j < n2)
      {
        a[k] = M[j];
        j++;
        k++;
      }

        int[] myArray = Arrays.copyOfRange(a, p, k);
        System.out.println("  Making array: " + Arrays.toString(myArray) + ", now stored master array");
        System.out.println();
  }

  public static void main(String[] args) {

    // Create standard input file object
    BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));

    try {
      String si = standardInput.readLine();                  // Put standard input into a string

      String[] siSplit = si.split(" ");                 // Split stand inp by " "


      int i = 0;
      for (String s : siSplit)
          i++;

      int[] list = new int[i];

      int c = 0;
      for (String b : siSplit){
        list[c] = Integer.parseInt(b);
        c++;
      }

      mergeSort(list, 0, list.length-1);
      System.out.println("---");
      System.out.println("Sorted result:" + Arrays.toString(list));

    } catch (IOException e) {
      System.out.println("Could not read standard input");
    }
  }
}
