import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class Term implements Comparable<Term> {
    private String query; // Query string.
    private long weight; // Query weight.

    // Constructs a term given the associated query string, having weight 0.
    public Term(String query) {
        // If query is null, throw a warning sign.
        if (query == null) {
            throw new NullPointerException("query is null");
        }
        // Initialize the variables.
        weight = 0;
        this.query = query;
    }

    // Constructs a term given the associated query string and weight.
    public Term(String query, long weight) {
        // If query is null, throw a warning sign.
        if (query == null) {
            throw new NullPointerException("query is null");
        } else if (weight < 0) {
            // If weight is negative value, throw a warning sign.
            throw new IllegalArgumentException("Illegal weight");
        }
        // Initialize the variables.
        this.weight = weight;
        this.query = query;
    }

    // Returns a string representation of this term.
    public String toString() {
        return weight + "\t" + query; // \t is tap.
    }

    // Returns a comparison of this term and other by query.
    public int compareTo(Term other) {
        // Use compareTo method to compare queries.
        // It will return the +, -, 0 integrals depending on queries.
        return this.query.compareTo(other.query);
    }

    // Returns a comparator for comparing two terms in reverse order of their weights.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // Returns a comparator for comparing two terms by their prefixes of length r.
    public static Comparator<Term> byPrefixOrder(int r) {
        // If length r is negative value, throw a warning sign.
        if (r < 0) {
            throw new IllegalArgumentException("Illegal r");
        }
        return new PrefixOrder(r);
    }

    // Reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        // Returns a comparison of terms v and w by their weights in reverse order.
        public int compare(Term v, Term w) {
            if (v.weight > w.weight) {
                return -1;
            } else if (v.weight < w.weight) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // Prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private int r; // Prefix length

        // Constructs a new prefix order given the prefix length.
        PrefixOrder(int r) {
            this.r = r;
        }

        // Returns a comparison of terms v and w by their prefixes of length r.
        public int compare(Term v, Term w) {
            // Substring methode between 0 and minimum of the length.
            // The substring a and b are the length between r and query.
            String a = v.query.substring(0, Math.min(r, v.query.length()));
            String b = w.query.substring(0, Math.min(r, w.query.length()));
            return a.compareTo(b); // Return corresponding value +, -, and 0.
        }
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
