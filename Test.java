package ru.relex;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        MyHashMap<String,Integer> map = new MyHashMap<>();
        map.put("One",1);
        map.put("Two",2);
        map.put("Three",3);
        map.put("Four",4);
        map.put("Five",5);
        Comparator<String> keyComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };


        Set<MyMapEntry<String,Integer>> set = new TreeSet<>(new MyHashMapComparator<>(keyComparator));
        set.addAll(map.entrySet());

//        System.out.println(map.size());
//        System.out.println(map.containsKey("One"));
//        System.out.println(map.containsKey("One"));
//        System.out.println(map.containsValue(2));

        for(MyMapEntry<String,Integer> entry: set){
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }



    }
}
