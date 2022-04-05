/**
 * Zigzag Conversion
Medium

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
 

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:

Input: s = "A", numRows = 1
Output: "A"
 

Constraints:

1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
 */

class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
        
        ArrayList<String> list = new ArrayList<String>();
        
        //creating no. of list with empty string
        for(int i=0;i<numRows;i++)
            list.add("");
        
        int top=0, bottom;
        
        //string s index
        int j=0;
        
        while(j<s.length()){
            
            while(top<numRows && j<s.length()){
                String s1=list.get(top);
                String s2=Character.toString(s.charAt(j));
                s1 = s1 + s2;
                list.set(top,s1);
                top++;
                j++;
            }
            
            //top will be 3 here, we need to start bottom as from previous last row
            bottom=top-2;
            while(bottom>=0 && j<s.length()){
                String s1=list.get(bottom);
                String s2=Character.toString(s.charAt(j));
                s1 = s1 + s2;
                list.set(bottom,s1);
                bottom--;
                j++;
            }
            
            //because bottom will be -1 here
            //we cant start top from 0 again as bottom finished placing its value
            //making top as 1 or we can hard code it to 1 directly
            top=bottom+2;
        }
        
        //create string builder and append values
        StringBuilder result = new StringBuilder();
        for(String str: list){
            result.append(str);
        }
        
        return result.toString();
    }
}