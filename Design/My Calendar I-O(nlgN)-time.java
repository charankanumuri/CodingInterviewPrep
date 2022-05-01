class MyCalendar {

    TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        
        //previouse and next intervals? find and store in a variable
        Integer prevStart = map.floorKey(start);
        Integer nextStart = map.ceilingKey(start);
        
        //if previouse interval exists? check for our start>= previous end
        //if there is next interval, check end is <= next start interval value
        if((prevStart == null || start >= map.get(prevStart)) && 
           (nextStart == null || end <= nextStart)){
            
            
            map.put(start,end);
            return true;
        }
        
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */