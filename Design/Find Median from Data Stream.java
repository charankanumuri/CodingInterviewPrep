/**
 * Find Median from Data Stream
Hard

The median is the middle value in an ordered integer list. If the size of the list is even, 
there is no middle value and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 */

/*

We are breaking this problem into 2 pieces - lowerHalf and UpperHalf

Eg: 2,3,4,6,7,8,10

lower Half must be: [2,3,4,6]
Upper Half must be: [7,8,10]

So the median here will be 6, bcz we had odd number of elements.

We are creating heaps to maintain these halfs.. lower Half is basically maxHeap structure and upper half is minHeap structure

If total elements is odd, then we can peek lowerHalf heap which is obtained in O(1) time.
If total elements are even, we can peek both the heaps and divide by 2.

Point to note is we always make sure lowerHalf heap >= upperHalf heap size.
*/

class MedianFinder {

    PriorityQueue<Integer> lowerHalf;
    PriorityQueue<Integer> upperHalf;
    
    
    public MedianFinder() {
        
        //lowerHalf is my max Heap
        lowerHalf = new PriorityQueue<>((a,b)->b-a);
        upperHalf = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        
        //add to lower half and remove it and send it to upperhalf
        //we do this to balance the heap if we get a mixture of different values in different order
        lowerHalf.add(num);
        upperHalf.add(lowerHalf.poll());
        
        //if lowerHalf is less in size, balance it out
        if(lowerHalf.size()<upperHalf.size())
            lowerHalf.add(upperHalf.poll());
    }
    
    public double findMedian() {
        
        //if there is no lowerHalf definitely we wouldn't have had upper half
        if(lowerHalf.size() == 0)
            return 0;
        
        //if its odd number of elements, return lowerHalf peek()
        //otherwise, take peek of both heaps and divide by 2
        if(lowerHalf.size()>upperHalf.size())
            return lowerHalf.peek();
        else
            return (lowerHalf.peek()+upperHalf.peek()) * 0.5;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */