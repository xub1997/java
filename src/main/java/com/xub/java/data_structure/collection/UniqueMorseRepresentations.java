package com.xub.java.data_structure.collection;

import java.util.TreeSet;

/**
 * @author xub
 * @Name: UniqueMorseRepresentations
 * @Description: TODO
 * @date 2020/1/14  21:30
 */
public class UniqueMorseRepresentations {

    public int uniqueMorseRepresentations(String[] words){
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> treeSet = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i)-'a']);
            }
            treeSet.add(res.toString());
        }
        return treeSet.size();
    }
}
