/***********************************************************
 * @Description : Trie和BSTSet查询、添加单词的性能比较
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 19:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section2To4TrieBasicAddContainPrefix;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.BSTSet;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());
            // ++++++++++++++++++++++计算BSTSet的处理时间++++++++++++++++++++++++
            long startTime = System.nanoTime();
            BSTSet<String> set = new BSTSet<>();
            // 添加单词
            for (String word : words) {
                set.add(word);
            }
            // 查询单词
            for (String word : words) {
                set.contain(word);
            }
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet : " + time + " s");

            // 计算Trie的处理时间
            startTime = System.nanoTime();
            Trie trie = new Trie();
            // 添加单词
            for (String word : words) {
                trie.add(word);
            }
            // 查询单词
            for (String word : words) {
                trie.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + set.getSize());
            System.out.println("Trie : " + time + " s");
        }

        System.out.println();
    }
}
/**
 * 单词量越大，Trie的性能越高
 * <p>
 * Pride and Prejudice
 * Total words: 125901
 * Total different words: 6530
 * BSTSet : 0.0392041 s
 * Total different words: 6530
 * Trie : 0.0381644 s
 */