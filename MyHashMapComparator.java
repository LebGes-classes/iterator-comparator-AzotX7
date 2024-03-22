package ru.relex;

import java.util.Comparator;

public class MyHashMapComparator<K,V> implements Comparator<MyMapEntry<K,V>> {
    public final Comparator<K>  keyComparator;

    public MyHashMapComparator(Comparator<K> keyComparator) {
        this.keyComparator = keyComparator;
    }

    @Override
    public int compare(MyMapEntry<K, V> o1, MyMapEntry<K, V> o2) {
        return keyComparator.compare(o1.getKey(),o2.getKey());
    }
}
