package Arrays_and_Strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Problem statement : Given two strings, write a method to decide if one is permutation of the other.
 *
 * Author : Srinath
 */
public class Q2_CheckPermutation {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input1 = scan.next();
        String input2 = scan.next();
        if (input1.length() == input2.length()) {
            System.out.println("Sorting output : " + checkPermutations_Sorting(input1, input2));
            System.out.println("hash map output : " + checkPermutations_HashMap(input1, input2));
            System.out.println("constant space output : " + checkPermutations_WithConstantSpace(input1, input2));
            System.out.println("optimised space output : " + checkPermutations_WithOptimisedSpace(input1, input2));
        }
    }

    /**
     * Sort both the Strings and then compare, we use inbuilt quick sort for this problem,
     * we will be seeing how to implement sorting algorithms in Sorting package.
     *
     * O(N*log(N)) for sorting, O(N) for comparing.
     * TC: O(N*log(N))
     * SC : O(N)
     */
    private static boolean checkPermutations_Sorting(String input1, String input2) {
        char[] string1 = input1.toCharArray();
        char[] string2 = input2.toCharArray();
        Arrays.sort(string1);
        Arrays.sort(string2);
        input1 = new String(string1);
        input2 = new String(string2);
        return input1.equals(input2);
    }

    /**
     * Input all the string characters with their counts into the unordered map respectively,
     * then check if both the unordered_maps have same characters with same count.
     *
     * O(N+N) for inserting, O(N) for checking.
     * TC: O(N)
     * SC : O(N)
     */
    private static boolean checkPermutations_HashMap(String input1, String input2) {
        HashMap<Character, Integer> hashMap1 = new HashMap<Character, Integer>();
        for(char ch : input1.toCharArray()) {
            int count = hashMap1.containsKey(ch) ? hashMap1.get(ch) : 0;
            hashMap1.put(ch, count+1);
        }
        HashMap<Character, Integer> hashMap2 = new HashMap<Character, Integer>();
        for(char ch : input2.toCharArray()) {
            int count = hashMap2.containsKey(ch) ? hashMap2.get(ch) : 0;
            hashMap2.put(ch, count+1);
        }
        for(Map.Entry<Character, Integer> map : hashMap1.entrySet()) {
            if(!hashMap2.containsKey(map.getKey()) || hashMap2.get(map.getKey()) != map.getValue()) {
                return false;
            }
            hashMap2.remove(map.getKey());
        }
        return (hashMap2.size() == 0);
    }

    /**
     * Assuming the both the strings consists of lowercase alphabets, we create count arrays.
     * Since the space is only of 52 integers, we can round it to constant space.
     *
     * O(N+N) for inserting, O(N) for checking.
     * TC: O(N)
     * SC : O(1)
     */
    private static boolean checkPermutations_WithConstantSpace(String input1, String input2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for(char ch : input1.toCharArray()) {
            int index = ch-'a';
            count1[index]++;
        }

        for(char ch : input2.toCharArray()) {
            int index = ch-'a';
            count2[index]++;
        }

        for(int i = 0; i < 26; i++) {
            if(count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Small optimisation to the previous approach instead of two count arrays, we maintain a single array.
     *
     * O(N+N) for inserting, O(N) for checking.
     * TC: O(N)
     * SC : O(1)
     */
    private static boolean checkPermutations_WithOptimisedSpace(String input1, String input2) {
        int[] count1 = new int[26];
        for(char ch : input1.toCharArray()) {
            int index = ch-'a';
            count1[index]++;
        }

        for(char ch : input2.toCharArray()) {
            int index = ch-'a';
            if(count1[index] == 0) {
                return false;
            }
            count1[index]--;
        }

        for(int i = 0; i < 26; i++) {
            if(count1[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
