import java.util.Arrays;
import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class Autocomplete {
    private Term[] terms; // Array of terms

    // Constructs an autocomplete data structure from an array of terms.
    public Autocomplete(Term[] terms) {
        // If terms is null, throw a warning sign.
        if (terms == null) {
            throw new NullPointerException("terms is null");
        }
        // We are accepting the input terms as the new terms.
        this.terms = Arrays.copyOf(terms, terms.length); // Defensive copy.
        Arrays.sort(this.terms); // Sort in lexicographic order.
    }

    // Returns all terms that start with prefix, in descending order of their weights.
    public Term[] allMatches(String prefix) {
        // If prefix is null, throw a warning sign.
        if (prefix == null) {
            throw new NullPointerException("prefix is null");
        }
        // Create the Term prefixTerm to look for the prefix with weight 0.
        Term prefixTerm = new Term(prefix, 0);

        // Checking the first term in terms that starts with prefix.
        int i = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm,
                // byPrefixOrder is a comparator to order the Term by prefix.
                Term.byPrefixOrder(prefix.length()));

        // Similarly, checking the last index of the last terms that start with prefix.
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm,
                Term.byPrefixOrder(prefix.length()));

        // If the startIndex i is -1, it means that there is no element in the txt file.
        if (i == -1 || lastIndex == -1) {
            // return an empty Term[] array.
            return new Term[0]; // No match found.
        }

        // n is the number of terms in terms that start with prefix.
        int n = lastIndex - i + 1; // +1 is to include the last term.

        // Create Term matches to keep the original input of terms.
        // i + n is to include the end of index.
        // For i + n, I can also do lastIndex + 1. It's the same thing.
        Term[] matches = Arrays.copyOfRange(terms, i, i + n);
        // Sort the elements in reverse order.
        Arrays.sort(matches, Term.byReverseWeightOrder());

        return matches; // return matches
    }

    // Returns the number of terms that start with prefix.
    public int numberOfMatches(String prefix) {
        // If prefix is null, throw a warning sign.
        if (prefix == null) {
            throw new NullPointerException("prefix is null");
        }
        // Create the Term prefixTerm to look for the prefix with weight 0.
        Term prefixTerm = new Term(prefix, 0);

        // Checking the first term in terms that starts with prefix.
        int i = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm,
                // byPrefixOrder is a comparator to order the Term by prefix.
                Term.byPrefixOrder(prefix.length()));

        // If the i is -1, it means that there is no element in the txt file.
        if (i == -1) {
            // return an empty Term[] array.
            return 0; // no match found
        }

        // Similarly, checking the last index of the last terms that start with prefix.
        int j = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));

        // n is the number of terms in terms that start with prefix.
        int n = j - i + 1; // +1 is to include the last term.
        return n;
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
        Autocomplete autocomplete = new Autocomplete(terms);
        StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            String msg = " matches for \"" + prefix + "\", in descending order by weight:";
            if (results.length == 0) {
                msg = "No matches";
            } else if (results.length > k) {
                msg = "First " + k + msg;
            } else {
                msg = "All" + msg;
            }
            StdOut.printf("%s\n", msg);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println("  " + results[i]);
            }
            StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        }
    }
}
