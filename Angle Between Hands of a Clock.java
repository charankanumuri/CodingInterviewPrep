/**
 * Angle Between Hands of a Clock
Medium

Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.

 

Example 1:


Input: hour = 12, minutes = 30
Output: 165
Example 2:


Input: hour = 3, minutes = 30
Output: 75
Example 3:


Input: hour = 3, minutes = 15
Output: 7.5
 

Constraints:

1 <= hour <= 12
0 <= minutes <= 59
 */

class Solution {
    public double angleClock(int hour, int minutes) {
        double one_minAngle = 6;
        double one_hrAngle = 30;
        
        double mins = one_minAngle * minutes;
        double hrs = (hour % 12 + (minutes/60.0)) * one_hrAngle;
        
        double difference = Math.abs(mins-hrs);
        return Math.min(difference, 360-difference);
    }
}