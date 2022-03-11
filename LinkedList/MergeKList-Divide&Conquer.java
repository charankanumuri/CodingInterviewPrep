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
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
 
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)
            return null;
        
        //starting recursive call, with start and end values
        return mergeSortKList(lists, 0, lists.length-1);
    }
    

    private ListNode mergeSortKList(ListNode[] lists, int start, int end){
        
        //when we have only one list on the recursive call, we just return the list
        if(start == end)
            return lists[start];
        
        //calculate mid, follow this formula to avoid overflow -> happens when start and end is too big
        int mid = start + (end-start)/2;
        
        //now make recursive calls for the left and right
        ListNode leftSide = mergeSortKList(lists, start, mid);
        ListNode rightSide = mergeSortKList(lists, mid+1, end);
        
        //merge both lists
        return mergeList(leftSide, rightSide);
    }
    
    private ListNode mergeList(ListNode l1, ListNode l2){
        ListNode result = new ListNode(-1);
        ListNode helper = result; //to keep track of list movement
        
        while(l1!=null || l2!=null){
            if(l1==null){
                helper.next = l2;
                l2 = l2.next;
            }
            else if(l2==null){
                helper.next = l1;
                l1 = l1.next;
            }
            else if(l1.val<=l2.val){
                helper.next = l1;
                l1 = l1.next;
            }
            else
            {
                helper.next = l2;
                l2 = l2.next;
            }
            helper = helper.next;
        }
        
        return result.next;
    }
}