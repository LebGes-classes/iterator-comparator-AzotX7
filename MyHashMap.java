package ru.relex;

import java.util.*;


public class MyHashMap<K,V> implements Iterable<MyMapEntry<K,V>>{
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public int size;
    public int numBuckets;
    public LinkedList<MyMapEntry<K,V>>[] buckets;

    public MyHashMap() {
        this.numBuckets = INITIAL_CAPACITY;
        size = 0;
        buckets = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
    }
    private int hash(K key){
        return Math.abs(key.hashCode() % numBuckets);
    }
    public void put(K key,V value){
        if(shouldResize()){
            resize();
        }
        int index = hash(key);
        LinkedList<MyMapEntry<K,V>> bucket = buckets[index];

        for(MyMapEntry<K,V> entry : bucket){
            if(entry.getKey().equals(key)){
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new MyMapEntry<>(key,value));
        size++;
    }

    public V get(K key){
        int index = hash(key);
        LinkedList<MyMapEntry<K,V>> bucket = buckets[index];

        for(MyMapEntry<K,V> entry: bucket){
            if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }
    public void remove(K key){
        int index = hash(key);
        LinkedList<MyMapEntry<K,V>> bucket = buckets[index];

        for(MyMapEntry<K,V> entry : bucket){
            if(entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    private void resize(){
        LinkedList<MyMapEntry<K,V>>[] oldBuckets = buckets;
        numBuckets *= 2;
        size = 0;
        buckets = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
        for(LinkedList<MyMapEntry<K,V>> bucket : oldBuckets){
            for(MyMapEntry<K,V> entry : bucket){
                put(entry.getKey(),entry.getValue());
            }
        }
    }
    private boolean shouldResize(){
        return (float) size / numBuckets >= LOAD_FACTOR;
    }
    public int size(){
        return size;
    }
    public boolean containsKey(K key){
        int index = hash(key);
        LinkedList<MyMapEntry<K,V>> bucket = buckets[index];

        for (MyMapEntry<K,V> entry : bucket){
            if(entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }
    public boolean containsValue(V value){
        for(LinkedList<MyMapEntry<K,V>> bucket: buckets){
            for (MyMapEntry<K,V> entry : bucket){
                if(entry.getValue().equals(value)){
                    return true;
                }
            }
        }
        return false;
    }
    public Iterator<MyMapEntry<K,V>> iterator(){
        return new MyHashMapIterator<>(this);
    }
    public Set<MyMapEntry<K, V>> entrySet() {
        Set<MyMapEntry<K, V>> set = new HashSet<>();
        for (LinkedList<MyMapEntry<K, V>> bucket : buckets) {
            for (MyMapEntry<K, V> entry : bucket) {
                set.add(entry);
            }
        }
        return set;
    }
}
