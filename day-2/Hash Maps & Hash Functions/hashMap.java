
import java.util.LinkedList;
import java.util.List;

// Class to represent a key-value pair
class MapEntry<K, V> {
    K key;
    V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}

// Class to represent the custom hash map
class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16; // Initial capacity of the hash table
    private static final double LOAD_FACTOR = 0.75;    // Load factor to determine when to resize
    private int capacity;                           // Current capacity of the hash table
    private List<MapEntry<K, V>>[] table;         // Array of linked lists to store key-value pairs
    private int size;                              // Current number of key-value pairs in the hash map

    // Constructor for the CustomHashMap class
    public CustomHashMap() {
        this(DEFAULT_CAPACITY); // Use default capacity
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        this.size = 0;
    }

    // Method to calculate the hash code for a given key
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return Math.abs(h) % capacity; // Ensure non-negative index
    }

    // Method to insert a key-value pair into the hash map
    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        // Check if the key already exists, if it exists update the value.
        for (MapEntry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new MapEntry<>(key, value));
        size++;

        // Check if resizing is needed
        if ((double) size / capacity > LOAD_FACTOR) {
            resize();
        }
    }

    // Method to retrieve the value associated with a given key
    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (MapEntry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null; // Key not found
    }

    // Method to remove a key-value pair from the hash map
    public V remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (MapEntry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    V value = entry.value;
                    table[index].remove(entry);
                    size--;
                    return value;
                }
            }
        }
        return null; // Key not found
    }

    // Method to resize the hash table
    private void resize() {
        int newCapacity = capacity * 2; // Double the capacity
        List<MapEntry<K, V>>[] newTable = new LinkedList[newCapacity];
        capacity = newCapacity;

        // Rehash and copy all key-value pairs to the new table
        for (List<MapEntry<K, V>> entries : table) {
            if (entries != null) {
                for (MapEntry<K, V> entry : entries) {
                    int newIndex = hash(entry.key);
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new LinkedList<>();
                    }
                    newTable[newIndex].add(entry);
                }
            }
        }
        table = newTable;
    }

     // Method to get the current size of the hash map
    public int size() {
        return size;
    }

    // Method to check if the hash map is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to clear all the elements in the hash map
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
        size = 0;
    }

    // Method to get all the keys in the hashmap
    public List<K> keySet()
    {
        List<K> keyList = new LinkedList<>();
         for (List<MapEntry<K, V>> entries : table) {
            if (entries != null) {
                for (MapEntry<K, V> entry : entries) {
                    keyList.add(entry.key);
                }
            }
        }
        return keyList;
    }

    // Method to get all the values in the hashmap
     public List<V> values()
    {
        List<V> valueList = new LinkedList<>();
         for (List<MapEntry<K, V>> entries : table) {
            if (entries != null) {
                for (MapEntry<K, V> entry : entries) {
                    valueList.add(entry.value);
                }
            }
        }
        return valueList;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (List<MapEntry<K, V>> entries : table) {
            if (entries != null) {
                for (MapEntry<K, V> entry : entries) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(entry.key).append("=").append(entry.value);
                    first = false;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}

// Main class to demonstrate the CustomHashMap
public class hashMap {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Insert key-value pairs
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);
        map.put("David", 40);
        map.put("Alice", 26); //update

        System.out.println("Map: " + map);

        // Get values
        System.out.println("Age of Bob: " + map.get("Bob")); // Output: 30
        System.out.println("Age of Alice: " + map.get("Alice"));

        // Remove a key-value pair
        System.out.println("Removed: " + map.remove("Charlie")); // Output: 35
        System.out.println("Map: " + map);

        //check size
        System.out.println("Size of map: " + map.size());

        // Check is empty or not
        System.out.println("Is map empty: " + map.isEmpty());

        //clear map
        map.clear();
        System.out.println("Map after clearing: " + map);
        System.out.println("Is map empty: " + map.isEmpty());

        //test keySet() and values()
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);
        System.out.println("Keys: " + map.keySet());
        System.out.println("Values: " + map.values());
    }
}

