package top.zoowayss.simple.spellword;

/**
 * @Author: <a href="https://github.com/zoowayss">zoowayss</a>
 * @Date: 2024/3/7 20:41
 */

public class Main {


    // spell world
    public static void main(String[] args) {
        String[] words = new String[]{"hello", "world", "leetcode"};
        String chars = "welldonehoneyr";

        int[] charsMap = new int[26];
        fillCharsMap(chars.toCharArray(), charsMap);
        int canSpellNum =0;
        for (String word : words) {
            if(canSpellWord(word.toCharArray(),charsMap))
                canSpellNum += word.length();
        }
        System.out.println(canSpellNum);
    }

    private static boolean canSpellWord(char[] toCharArray, int[] charsMap) {

        int[] temMap = new int[26];
        fillCharsMap(toCharArray, temMap);

        for (int i = 0; i < temMap.length; i++) {
            if(temMap[i]>charsMap[i])
                return false;
        }
        return true;
    }

    private static void fillCharsMap(char[] chars, int[] charsMap) {
        for (char ch : chars) {
            charsMap[((int) ch) - 97]++;
        }
    }

    private static void printCharsArray(int[] charsMap) {
        for (int i : charsMap) {
            System.out.print(i);
        }
    }
}
