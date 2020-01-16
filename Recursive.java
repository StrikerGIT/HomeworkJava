package lesson5;

import java.util.List;

public class Recursive {
    public static void main(String[] args) {
        //проверка первого задания
        System.out.println(recPow(2,10));

        //проверка второго задания
        Backpack backpack = new Backpack(5);
        backpack.showItems(backpack.getItems());

        backpack.makeAllSets(backpack.getItems());
        List<Backpack.Item> solve = backpack.getBestSet();

        if(solve == null)
        {
            System.out.println("Нет решения!");
        }
        else
        {
            backpack.showItems(solve);
        }
    }

    static int recPow(int base, int pow) {
        if (pow == 0) return 1;
        if (pow % 2 == 0) {
            return recPow(base*base, pow/2);
        }
        else {
            return  base * recPow(base*base, pow/2);
        }
    }
}
