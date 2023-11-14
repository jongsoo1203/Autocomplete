import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a that equals the search key, or -1, according to
    // the order induced by the comparator c.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> c) {
        // If the inputs are null, throw the warning sign.
        if (a == null || key == null || c == null) {
            throw new NullPointerException("a, key, or c is null");
        }
        // Set variables to find mid.
        int lo = 0; // Lowest will be 0.
        int hi = a.length - 1; // Highest will be length - 1.
        int index = -1; // Set index to -1 for possible invalid outcome.

        // While the length of key array a is positive number as it is supposed to be.
        // While hi is higher than lo.
        while (lo <= hi) {
            // Find location of the middle value. Therefore, a[mid] will be mid.
            int mid = lo + (hi - lo) / 2;

            // We use the Comparator c to compare the value key and the mid-value in array a.
            // From the Comparator, it will give -, +, or 0.
            int i = c.compare(key, a[mid]);

            // If key is less than the a[mid]
            if (i < 0) {
                hi = mid - 1; // Change hi to mid - 1. We only look at the left part of the array.
            } else if (i > 0) {
                // If key is larger than a[mid], bring the lo to mid + 1.
                // we only look at right part of the array.
                lo = mid + 1;
            } else {
                // If they are the same, set index to mid.
                index = mid;
                hi = mid - 1; // This is to get the first array of the key.
            }
        }
        // return index. If we could not find index, then just return -1.
        return index;
    }

    // Returns the index of the last key in a that equals the search key, or -1, according to
    // the order induced by the comparator c.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {
        if (a == null || key == null || c == null) {
            throw new NullPointerException("a, key, or c is null");
        }
        // Set variables to find mid.
        int lo = 0; // Lowest will be 0.
        int hi = a.length - 1; // Highest will be length - 1.
        int index = -1; // Set index to -1 for possible invalid outcome.

        // While hi is higher than lo.
        while (lo <= hi) {

            // Find location of the middle value. Therefore, a[mid] will be mid.
            int mid = lo + (hi - lo) / 2;
            // We use the Comparator c to compare the value key and the mid-value in array a.
            // From the Comparator, it will give -, +, or 0.
            int i = c.compare(key, a[mid]);

            // If key is less than the a[mid]
            if (i < 0) {
                hi = mid - 1; // Change hi to mid - 1. We only look at the left part of the array.
            } else if (i > 0) {
                // If key is larger than a[mid], bring the lo to mid + 1.
                // We only look at right part of the array.
                lo = mid + 1;
            } else {
                // If they are the same, set index to mid.
                index = mid;
                // By changing the lo to the mid + 1, we will keep look for the last key.
                lo = mid + 1; // This is to get the last array of the key.
            }
        }
        return index; // Return index.
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Arrays.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println("firstIndexOf(" + prefix + ") = " + i);
        StdOut.println("lastIndexOf(" + prefix + ")  = " + j);
        StdOut.println("frequency(" + prefix + ")    = " + count);
    }
}
