/**
 * Eg: arr=[1,3,4,5,1,2,3]
 * 
 * o/p: 5 -> becauses number 3 is maxDistance away from next 3.. at index1 and at index6
 */


import java.util.*;

class Solution {
    int solution(int[] A) {

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int N = A.length;
        for(int i=0;i<N;i++){

            if(!map.containsKey(A[i])){
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(i);
                map.put(A[i], list);
            }
            else{
                List<Integer> list = map.get(A[i]);
                //Integer first = list.get(0);
                Integer second = list.get(1);

                // if(first>i)
                //     list.set(0,i);
                            
                if(second<i)
                    list.set(1,i);

                map.put(A[i],list);
            }
        }

        int result = 0;
        
        for(int i=0;i<N;i++){
            List<Integer> list = map.get(A[i]);
            result = Math.max(result, Math.abs(list.get(0)-list.get(1)));
        }
        // for (int i = 0; i < N; i++)
        //     for (int j = 0; j < N; j++)
        //         if (A[i] == A[j])
        //             result = Math.max(result, Math.abs(i - j));
        return result;
    }
}
