package Arrays;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Problem statement : Implement an algorithm to determine if a String has all unique characters.
 * Follow up : What if you cannot use additional data structures.
 *
 * Author : Srinath
 */
public class IsUnique {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        System.out.println("output  1: " + isUnique_BruteForce(input));
        System.out.println("output 2 : " + isUnique_HashSet(input));
        System.out.println("output 2 : " + isUnique_NoExtraSpace(input));
    }

    /**
     * Brute force approach
     * TC : O(N*N)
     * SC : O(1)
     */
    private static boolean isUnique_BruteForce(String input) {
        int length = input.length();
        for(int i=0; i<length; i++) {
            for(int j=i+1; j<length; j++) {
                if(input.charAt(i) == input.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * using Hash Set
     * TC : O(N)
     * SC : O(N)
     */
    private static boolean isUnique_HashSet(String input) {
        HashSet<Character> hash = new HashSet<Character>();
        for(char ch : input.toCharArray()) {
            if(hash.contains(ch)) {
                return false;
            }
            hash.add(ch);
        }
        return true;
    }

    /**
     * with no extra space assuming String has atmost 26 lowercase alphabets.
     * Does not work for unicode strings
     * TC : O(N)
     * SC : O(N)
     */
    private static boolean isUnique_NoExtraSpace(String input) {
        int unique_checker = 0;
        for(char ch : input.toCharArray()) {
            int bit_position = ch - 'a';
            if(((unique_checker >> bit_position) & 1) == 1) {
                return false;
            }
            unique_checker |= (1 << bit_position);
        }
        return true;
    }
}
