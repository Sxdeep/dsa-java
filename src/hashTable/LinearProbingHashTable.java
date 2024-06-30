package hashTable;

import java.lang.reflect.Array;

public class LinearProbingHashTable {
    public class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private Entry[] entries;
    private int size;

    public LinearProbingHashTable(int size) {
        entries = new Entry[size];
    }

    public int hash(int key) {
        return key % entries.length;
    }

    public void put(int key, String value) {
        for(int i = 0; i < entries.length; i++) {
            if(entries[i] != null && entries[i].key == key){
                entries[i].value = value;
                return;
            }
        }

        int index = hash(key);
        for(int i = index; i < entries.length; i++) {
            if (entries[i] == null) {
                entries[i] = new Entry(key, value);
                return;
            }
        }
    }
    public String get(int key) {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null && entries[i].key == key) {
                return entries[i].value;
            }
        }
        return null;
    }
}