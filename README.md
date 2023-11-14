# Autocomplete

Autocomplete is an important feature of many modern applications. As the user types, the program predicts the complete
query (typically a word or phrase) that the user intends to type. Autocomplete is most effective when there are a limited
number of likely queries. For example, the Internet Movie Database uses it to display the names of movies as the user types;
search engines use it to display suggestions as the user enters web search queries; cell phones use it to speed up text input.

### Term.java

(Autocomplete Term) Implement an immutable comparable data type called Term that represents an autocomplete
term: a string query and an associated real-valued weight. You must implement the following API, which supports comparing
terms by three different orders: lexicographic order by query; in descending order by weight; and lexicographic order by query
but using only the rst r characters. The last order may seem a bit odd, but you will use it in Problem 3 to find all terms
that start with a given prex (of length r).

### BinarySearchDeluxe.java

(Binary Search Deluxe) When binary searching a sorted array that contains more than one key equal to the
search key, the client may want to know the index of either the first or the last such key. Accordingly, implement a library
called BinarySearchDeluxe

### Autocomplete.java

(Autocomplete) In this part, you will implement a data type that provides autocomplete functionality for a
given set of strings and weights, using Term and BinarySearchDeluxe. To do so, sort the terms in lexicographic order; use binary
search to find the set of terms that start with a given prex; and sort the matching terms in descending order by weight.
Organize your program by creating an immutable data type called Autocomplete

### Autocomplete.java

Accepts filename (String) and k (int) as command-line arguments; provides a GUI for the user to enter queries against the file; and presents the top $k$ matching terms in real-time.
