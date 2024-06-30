package hashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTable {
    public class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    LinkedList<Entry>[] entries;

    public HashTable() {
        entries = new LinkedList[10];
    }
    public int hash(int key) {
        return key % entries.length;
    }
    public void put(int key, String value) {
        int index = hash(key);
        if (entries[index] == null) {
            entries[index] = new LinkedList<>();
        }
        var bucket = entries[index];
        for(var entry : bucket) {
            if(entry.key == key) {
                entry.value = value;
                return;
            }
        }
        bucket.addLast(new Entry(key, value));
    }
    public String get(int key) {
        int index = hash(key);
        if (entries[index] == null) {
            throw new NoSuchElementException();
        }
        var bucket = entries[index];
        for(var entry : bucket) {
            if(entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }
    public void remove(int key) {
        int index = hash(key);
        if (entries[index] == null) {
            throw new NoSuchElementException();
        }
        var bucket = entries[index];
        for(var entry : bucket) {
            if(entry.key == key) {
                entry.value = null;
            }
        }
    }
    public static int mostFrequent(int[] arr) {
        var map = new HashMap<Integer, Integer>();
        for(int i = 0; i < arr.length; i++) {
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            }
            else{
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        int max = Integer.MIN_VALUE;
        int key = -1;
        for(var entry : map.entrySet()) {
            if(entry.getValue() > max) {
                max = entry.getValue();
                key = entry.getKey();
            }
        }
        return key;
    }
    public static int countPairsWithDiff(int[] arr, int k) {
        var set = new HashSet<Integer>();
        for(int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        int count = 0;
        for(var element : arr) {
           if(set.contains(element - k)) {
               count++;
           }
           if(set.contains(element + k)) {
               count++;
           }
           set.remove(element);
        }
        return count;
    }
    public static int[] twoSum(int[] arr, int k) {
        var map = new HashMap<Integer, Integer>();
        for(int i = 0; i < arr.length; i++) {
            if(map.containsKey(k - arr[i])) {
                return new int[] {map.get(k - arr[i]), i};
            }
            map.put(arr[i], i);
        }
        return null;
    }
}




/*
public static char firstCharacter(String str) {
        Map<Character, Integer> map = new HashMap<>();
        char[] character = str.toCharArray();
        System.out.println(character);
        for(char c : character) {
            if(!map.containsKey(c)){
                map.put(c, 1);
            }
            else{
                map.put(c, map.get(c) + 1);
            }
        }
        for(var item : map.entrySet()) {
            System.out.print(item + " ");
        }
        for(char c : character) {
            if(map.get(c) == 1) {
                return c;
            }
        }
        throw new NoSuchElementException();
    }
 */
