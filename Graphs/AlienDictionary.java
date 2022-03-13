import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AlienDictionary {

    // leetcode question number: 269

    // There is a new alien language that uses the English alphabet. However, the
    // order among the letters is unknown to you.

    // You are given a list of strings words from the alien language's dictionary,
    // where the strings in words are sorted lexicographically by the rules of this
    // new language.

    // Return a string of the unique letters in the new alien language sorted in
    // lexicographically increasing order by the new language's rules. If there is
    // no solution, return "". If there are multiple solutions, return any of them.

    // A string s is lexicographically smaller than a string t if at the first
    // letter where they differ, the letter in s comes before the letter in t in the
    // alien language. If the first min(s.length, t.length) letters are the same,
    // then s is smaller if and only if s.length < t.length.

    // Input: words = ["wrt","wrf","er","ett","rftt"]
    // Output: "wertf"

    // Input: words = ["z","x","z"]
    // Output: ""
    // Explanation: The order is invalid, so return "".

    public static void main(String[] args) {
        String[] words = { "wrt", "wrf", "er", "ett", "rftt" };
        // String[] words = {"z","x"};
        System.out.println(alienOrder(words));
    }

    public static String alienOrder(String[] words) {

        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
        // map to store indegree
        Map<Character, Integer> counts = new HashMap<Character, Integer>();

        // keeping default values in adjeceny list as to build a graph and indegree maps
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, 0);
                map.put(c, new ArrayList<Character>());
            }
        }

        // "wrt", "wrf", "er", "ett", "rftt"

        // compare 1, 2: t -> f [w & r are same in both; t comes before f in alien
        // dictionary]
        // compare 2, 3: w -> e
        // compare 3, 4: r -> t [since e is same in both]
        // compare 4, 5: e -> r, t -> f(already observed in first comparision)

        // In case w -> e means that e has indegree 1.

        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];

            // speacial case where first word is more in length and second word is
            // completely present in first word
            // then no possible ordering of Alien dictionary.
            if (first.length() > second.length() && first.startsWith(second))
                return "";

            // building graph as mentioned in the above explanation
            int len = Math.min(first.length(), second.length());
            for (int j = 0; j < len; j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    // Eg: e -> r : map.get(e).add(r)
                    map.get(first.charAt(j)).add(second.charAt(j));
                    // making indegree++ of char in second word
                    counts.put(second.charAt(j), counts.get(second.charAt(j)) + 1);
                    break;
                }
            }
        }

        // Step 2: Topological sort using BFS
        // Refer this video for Topological using BFS Kahn's Alg:
        // https://www.youtube.com/watch?v=rZv_jHZva34&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=14&t=983s
        // Idea is first push the node with indegree = 0 to queue
        // then traverse to the adjacent nodes of the node in queue
        // when the adj node is reached make indegree of that node -1
        // if this decreased indegree becomes 0, then push this adj node into queue
        // Do this till the queue is empty

        // Note: When ever removing a node from queue, add it to Toposort Array or
        // string in this case.
        // *** There can be many Topological sorts for a graph.
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : counts.keySet()) {
            if (counts.get(c).equals(0)) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : map.get(c)) {
                counts.put(next, counts.get(next) - 1);
                if (counts.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }

        return sb.length() < counts.size() ? "" : sb.toString();
    }
}
