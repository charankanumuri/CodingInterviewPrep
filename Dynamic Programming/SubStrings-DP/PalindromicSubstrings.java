public class PalindromicSubstrings {
    // Given a string s, return the number of palindromic substrings in it.

    // A string is a palindrome when it reads the same backward as forward.

    // A substring is a contiguous sequence of characters within the string.
    


    // commented code is recursive approach
    
    // int res = 0;
    public int countSubstrings(String s) {
        // recursive(s, 0); 
        // return res;
        
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            // in case of odd length palindrome
            res += countPalindromes(s, i, i);
            // in case of even length palindrome
            res += countPalindromes(s, i, i + 1);
        }
        return res;
    }
    
    // for every substring check for palindromes on both left & right of the given indices
    public int countPalindromes(String s, int low, int high) {
        int ans = 0;
        while(low >= 0 && high < s.length()) {
            if(s.charAt(low) != s.charAt(high))
                break;
            
            low--;
            high++;
            ans++;
        }
        return ans;
    }
    
//     public void recursive(String s, int index) {
//         if(index == s.length() - 1) {
//             res++;
//             return;
//         }
        
//         for(int i = index + 1; i <= s.length(); i++) {
//             String str = s.substring(index, i);
//             if(isPalindrome(str))
//                 res++;
//         }
//         recursive(s, index + 1);
//         return;
//     }
    
//     public boolean isPalindrome(String str){  
//         StringBuilder sb = new StringBuilder(str);  
//         sb.reverse();  
//         String rev=sb.toString();  
//         if(str.equals(rev)) 
//             return true;  
//         return false;  
//     }  
}
