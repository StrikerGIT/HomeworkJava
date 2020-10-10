import Polymorphism.Circle;
import Polymorphism.Shape;
import Polymorphism.Square;
import Polymorphism.Triangle;

public class Main {

    public static void main(String[] args) {
        Person goodClass = new Person.Builder("Ivan", "Petrov", "Alexandrovich")
                .address("Test")
                .age(30)
                .country("Russia")
                .gender("M")
                .phone("+79999999")
                .buidl();

        Shape[] a = new Shape[] {new Shape(), new Triangle(), new Square(), new Circle()};

        // Перебор в цикле элементов массива
        for(int i = 0; i < a.length; i++) {
            a[i].draw();
        }

    }
}

