/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/*
Examples

i/p: 1->2->3->2->null
o/p: 1->3-null

i/p: 2->1->1->2->null
o/p: null


i/p: 3->2->2->1->3->2->4->null
o/p: 1->4->null
*/
class Solution {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        if(head == null)
            return head;
        
        //map to maintain the frequency of no's repeated
        HashMap<Integer, Integer> map = new HashMap<>();
        
        //dummy node to help track the result
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        //helper node to skip and tranverse
        ListNode helper = dummy;
        
        
        //while loop to count the frequencies
        ListNode current = head;
        while(current!=null){
            if(map.containsKey(current.val))
                map.put(current.val, map.get(current.val)+1);
            else
                map.put(current.val, 1);
            
            current = current.next;
        }
        
        while(head!=null){
            if(map.get(head.val)>1){
                head = head.next;
            }
            else{
                helper.next = head;
                helper = head;
                head = head.next;
            }   
        }
        
        //point helper next to null at the end
        helper.next = null;
        
        return dummy.next;
    }
}