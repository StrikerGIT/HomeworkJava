package DZ;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Box<Fruit> boxf = new Box<>();
        Box<Apple> boxa = new Box<>();
        Box<Orange> boxo = new Box<>();
        boxa.addFruit(new Apple(), new Apple(), new Apple());
        boxo.addFruit(new Orange(),new Orange());
        boxa.transfer(boxf);
        if (boxf.compare(boxo)) {
            System.out.println("Ящики совпадают");
        }

    }

}

class Box<T extends Fruit> {
    private ArrayList<T> list;

    public Box(T... arr) {
        list = new ArrayList<T>(Arrays.asList(arr));
    }

    public float weight() {
        if (list.size() == 0) return 0.0f;
        return list.get(0).weight() * list.size();
    }

    public void addFruit(T fruit) {
        list.add(fruit);
    }

    public void addFruit(T... fruit) {
        for (int i = 0; i <fruit.length; i++) {
            list.add(fruit[i]);
        }
    }

    public boolean compare(Box another) {
        return Math.abs(this.weight() - another.weight()) < 0.00001;
    }

    public void transfer(Box<? super T> another) {
        another.list.clear();
        another.list.addAll(this.list);
        this.list.clear();
    }
}

abstract class Fruit {
    protected float weight;

    public float weight() {
        return weight;
    }

    public Fruit(float weight) {
        this.weight = weight;
    }
}

class Orange extends Fruit {
    public Orange() {
        super(1.5f);
    }

}

class Apple extends Fruit {
    public Apple() {
        super(1.0f);
    }

}