public class ImplementingTrie {

    // A trie (pronounced as "try") or prefix tree is a tree data structure used to
    // efficiently store and retrieve keys in a dataset of strings. There are
    // various applications of this data structure, such as autocomplete and
    // spellchecker.

    // Implement the Trie class:

    // Trie() Initializes the trie object.
    // void insert(String word) Inserts the string word into the trie.
    // boolean search(String word) Returns true if the string word is in the trie
    // (i.e., was inserted before), and false otherwise.
    // boolean startsWith(String prefix) Returns true if there is a previously
    // inserted string word that has the prefix prefix, and false otherwise.

    // Imagine a trie as a tree like structure with:

    // chars from a-z in the nodes and each node has 26 brances
    // If inserting a word is completed then make isWord = true for the last node

    class Node {
        char c;
        boolean isWord;
        Node[] children;

        public Node(char c) {
            this.c = c;
            isWord = false;
            children = new Node[26];
        }
    }

    // like a dummy node used for traversal
    Node root;

    public Trie() {
        root = new Node('\0');
    }

    // Eg: if word is "batman" the the trie structure will be like

    // b->a->t->m->a->n

    // node at "n" is updated with isWord = true

    public void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new Node(ch);
            curr = curr.children[ch - 'a'];
        }
        curr.isWord = true;
    }

    // is the node is present in trie and isWord is true then word is found
    public boolean search(String word) {
        Node node = getNode(word);
        return node != null && node.isWord;
    }

    // is the node is present in trie then prefix is found
    public boolean startsWith(String prefix) {
        return getNode(prefix) != null;
    }

    // traverse from the first char to the very end char of the given word
    // return the last node where the given word ends.
    // if this node is already present in the trie then return last node
    // or else return null.
    public Node getNode(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null)
                return null;
            curr = curr.children[ch - 'a'];
        }
        return curr;
    }
}
