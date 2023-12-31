import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;


/*
 * Huffman Coding Complexity
The time complexity for encoding each unique character based on its frequency is O(nlog n).

*Extracting minimum frequency from the priority queue takes place 2*(n-1) times and its complexity is
O(log n). Thus the overall complexity is O(nlog n).
 */


class HuffmanNode {
  int item;
  char c;
  HuffmanNode left;
  HuffmanNode right;
}

// For comparing the nodes
class ImplementComparator implements Comparator<HuffmanNode> {
  public int compare(HuffmanNode x, HuffmanNode y) {
    return x.item - y.item;
  }
}

public class huffman {

  public static void printCode(HuffmanNode root, String s) {
    if (root.left == null && root.right == null && Character.isLetter(root.c)) {

      System.out.println(root.c + "   |  " + s);

      return;
    }
    printCode(root.left, s + "0");
    printCode(root.right, s + "1");
  }

  public static void huffmanEncoding(char[] charArray, int[] charfreq, int n) {
    PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new ImplementComparator());

    for (int i = 0; i < n; i++) {
      HuffmanNode hn = new HuffmanNode();

      hn.c = charArray[i];
      hn.item = charfreq[i];

      hn.left = null;
      hn.right = null;

      q.add(hn);
    }

    HuffmanNode root = null;

    while (q.size() > 1) {

      HuffmanNode x = q.peek();
      q.poll();

      HuffmanNode y = q.peek();
      q.poll();

      HuffmanNode f = new HuffmanNode();

      f.item = x.item + y.item;
      f.c = '-';
      f.left = x;
      f.right = y;
      root = f;

      q.add(f);
    }
    System.out.println("Char | Huffman code ");
    System.out.println("--------------------");
    printCode(root, "");

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number of elements: ");
    int n = sc.nextInt();
    char[] charArray = new char[n];
    int[] charfreq = new int[n];

    for (int i = 0; i < n; i++) {
      System.out.print("Enter Character: ");
      charArray[i] = sc.next().charAt(0);
    }
    for (int j = 0; j < n; j++) {
      System.out.print("Enter the character frequency: ");
      charfreq[j] = sc.nextInt();
    }
    long startTime = System.nanoTime();
    huffmanEncoding(charArray, charfreq, n);
    long endTime = System.nanoTime();
    System.out.println("Elasped time is: " + (endTime - startTime) + " nano seconds");
    sc.close();
  }

}
