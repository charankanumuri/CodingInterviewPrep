/**
 * Design Circular Queue
Medium

Design your implementation of the circular queue. 
The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and 
the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. 
In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. 
But using the circular queue, we can use the space to store new values.

Implementation the MyCircularQueue class:

MyCircularQueue(k) Initializes the object with the size of the queue to be k.
int Front() Gets the front item from the queue. If the queue is empty, return -1.
int Rear() Gets the last item from the queue. If the queue is empty, return -1.
boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
boolean isEmpty() Checks whether the circular queue is empty or not.
boolean isFull() Checks whether the circular queue is full or not.
You must solve the problem without using the built-in queue data structure in your programming language. 

 

Example 1:

Input
["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 3, true, true, true, 4]

Explanation
MyCircularQueue myCircularQueue = new MyCircularQueue(3);
myCircularQueue.enQueue(1); // return True
myCircularQueue.enQueue(2); // return True
myCircularQueue.enQueue(3); // return True
myCircularQueue.enQueue(4); // return False
myCircularQueue.Rear();     // return 3
myCircularQueue.isFull();   // return True
myCircularQueue.deQueue();  // return True
myCircularQueue.enQueue(4); // return True
myCircularQueue.Rear();     // return 4
 

Constraints:

1 <= k <= 1000
0 <= value <= 1000
At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
 */


/**
 * 
 * We can use arrays to implement the same, but linked list is more memory efficient bcz we don't have to define the size before hand.
 * Also, using arrays can lead to concurrency issues, what if multiple processes are trying to insert newNode at the same time when 
 * capacity remaining is just 1. We can use locks to block other processes entering the function to add element at the same time.
 * And the release the lock when enqueue is completed
 *  
 * */ 

//Node definition
class Node{
    Node next;
    int val;
    
    public Node(int value){
        this.val = value;
        this.next=null;
    }
}

class MyCircularQueue {

    //I need a head and tail to keep track of start and end
    Node head, tail;
    
    //size is to know how much capacity we can hold, total is to count the no.of nodes in the queue
    int size, total;
    
    public MyCircularQueue(int k) {
        this.size = k;
        this.total = 0;
    }
    
    public boolean enQueue(int value) {
        
        //if its full, return false
        if(this.total == this.size)
            return false;
        
        //create new Node with the given value
        Node newNode = new Node(value);
        
        //if this is my first node, assign head and tail to this node
        if(this.total==0){
            this.head = newNode;
            this.tail = newNode;
        }
        
        //otherwise, point the tail's next to the new Node and change the tail pointer
        else{
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.total++;
        return true;
    }
    
    public boolean deQueue() {
        if(this.total == 0)
            return false;
        
        //just move the head pointer to head's next, we have new head now
        this.head = this.head.next;
        this.total--;
        return true;
    }
    
    public int Front() {
        if(this.total == 0)
            return -1;
        
        return this.head.val;
    }
    
    public int Rear() {
        if(this.total==0)
            return -1;
        
        return this.tail.val;
    }
    
    public boolean isEmpty() {
        if(this.total == 0)
            return true;
        
        return false;
    }
    
    public boolean isFull() {
        if(this.total == this.size)
            return true;
        
        return false;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */