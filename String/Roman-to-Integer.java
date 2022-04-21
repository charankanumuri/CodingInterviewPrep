class Solution {
    public int romanToInt(String s) {
        int answer=0;
        
        int[] values = new int[s.length()];
        
        int index=0;
        for(char c: s.toCharArray()){
                if(c=='I')
                    values[index++]=1;
            if(c=='V')
                    values[index++]=5;
            if(c=='X')
                    values[index++]=10;
            if(c=='L')
                    values[index++]=50;
            if(c=='C')
                    values[index++]=100;
            if(c=='D')
                    values[index++]=500;
            if(c=='M')
                    values[index++]=1000;
        }
        
        int i=0;
        while(i<values.length-1)
        {
            if(values[i]>=values[i+1]){
                answer = answer + values[i];
            }
            else{
                answer-=values[i];
            }
            i++;
        }
        
        return answer+values[i];
    }
}