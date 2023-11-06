import java.util.Scanner;

public class Knapsack {

  public static void knapsack01(int[] values, int[] weights, int capacity) {
    int n = values.length;
    int[][] dp = new int[n + 1][capacity + 1];

    for (int i = 0; i <= n; i++) {
      for (int w = 0; w <= capacity; w++) {
        if (i == 0 || w == 0) {
          dp[i][w] = 0;
        } else if (weights[i - 1] <= w) {
          dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
        } else {
          dp[i][w] = dp[i - 1][w];
        }
      }
    }

    int i = n, j = capacity;
    int[] ans = new int[n + 1];
    while (i > 0 && j > 0) {
      if (dp[i][j] == dp[i - 1][j]) {
        ans[i - 1] = 0;
        i--;
      } else {
        ans[i - 1] = 1;
        i--;
        j = j - weights[i];
      }
    }

    System.out.println();
    System.out.print("The selected elements are: ");
    for (int k = 0; k < n; k++) {
      System.out.print(ans[k] + " ");
    }
    System.out.println();

    System.out.println("The Maximum profit is: " + dp[n][capacity]);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of elements: ");
    int n = sc.nextInt();
    int[] weights = new int[n];
    int[] values = new int[n];
    for (int i = 0; i < n; i++) {
      System.out.print("Enter weight: ");
      weights[i] = sc.nextInt();
    }
    for (int i = 0; i < n; i++) {
      System.out.print("Enter Profit: ");
      values[i] = sc.nextInt();
    }
    System.out.print("Enter Capacity of knapsack: ");
    int capacity = sc.nextInt();

    long start = System.nanoTime();
    knapsack01(values, weights, capacity);
    long end = System.nanoTime();
    System.out.println("Elasped time is: " + (end - start) + " nano seconds");
    sc.close();
  }

}
