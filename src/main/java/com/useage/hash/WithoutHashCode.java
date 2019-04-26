package com.useage.hash;

import java.util.HashMap;

public class WithoutHashCode {

    public static void main(String[] args) {
        Key k1 = new Key(1);
        Key k2 = new Key(1);
        HashMap<Key, String> hm = new HashMap<Key, String>(2);
        hm.put(k1, "Key with id is 1");
        System.out.println(hm.get(k2));
    }
}
