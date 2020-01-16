package lesson5;

import java.util.*;

public class Backpack {

    private List<Item> bestItems = null;
    private List<Item> items;

    private int maxW;
    private double bestPrice;



    public List<Item> getBestItems() {
        return bestItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public Backpack(int _maxW)
    {
        maxW = _maxW;
        items = new ArrayList<Item>(10);
        addItems();
    }

    //вычисляет общий вес набора предметов
    private double calcWeigth(List<Item> items)
    {
        double sumW = 0;
        for (Item i:items) {
            sumW += i.weigth;
        }

        return sumW;
    }

    //вычисляет общую стоимость набора предметов
    private double calcPrice(List<Item> items)
    {
        double sumPrice = 0;

        for (Item i : items)
        {
            sumPrice += i.price;
        }

        return sumPrice;
    }

    //проверка, является ли данный набор лучшим решением задачи
    private void checkSet(List<Item> items)
    {
        if (bestItems == null)
        {
            if (calcWeigth(items) <= maxW)
            {
                bestItems = items;
                bestPrice = calcPrice(items);
            }
        }
        else
        {
            if(calcWeigth(items) <= maxW && calcPrice(items) > bestPrice)
            {
                bestItems = items;
                bestPrice = calcPrice(items);
            }
        }
    }

    //создание всех наборов перестановок значений
    public void makeAllSets(List<Item> items)
    {
        if (items.size() > 0)
            checkSet(items);

        for (int i = 0; i < items.size(); i++)
        {

            List<Item> newSet = new ArrayList<Item>(items) ;
            newSet.remove(i);

            makeAllSets(newSet);
        }

    }

    //возвращает решение задачи (набор предметов)
    public List<Item> getBestSet()
    {
        return bestItems;
    }

    public class Item
    {
        public String name;

        public double weigth;

        public double price;

        public Item(String _name, double _weigth, double _price)
        {
            name = _name;
            weigth = _weigth;
            price = _price;
        }

    }

    private void addItems()
    {
        items.add(new Item("Книга", 1, 600));
        items.add(new Item("Бинокль", 2, 5000));
        items.add(new Item("Аптечка", 4, 1500));
        items.add(new Item("Ноутбук", 2, 40000));
        items.add(new Item("Котелок", 1, 500));
    }

    public void showItems(List<Item> _items)
    {   StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (Item i : _items)
        {
            sb.append("{" + i.name + " " + i.weigth + " " + i.price + "}");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
