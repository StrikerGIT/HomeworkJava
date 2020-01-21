package lesson6;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        MyTreeMap<Integer, String> map = new MyTreeMap<>();
//
//        map.put(5,"five");
//        map.put(1,"one");
//        map.put(3,"tree");
//        map.put(2,"two");
//        map.put(4,"four");
//
//        System.out.println(map.get(1));
//        System.out.println(map.get(2));
//        map.delete(2);
//        System.out.println(map.contains(2));
//
//        System.out.println(map);

        ArrayList myTrees = new ArrayList <MyTreeMap>();

        for (int i = 0; i < 20; i++) {
            MyTreeMap<Integer, String> newMap = new MyTreeMap<>();

            do {
                newMap.put(rnd(-100, 100),"");
            } while (newMap.getMaxHigh() < 6);

            System.out.println(newMap);
            System.out.println(newMap.isBalancedTree());
            myTrees.add(newMap);
        }
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
