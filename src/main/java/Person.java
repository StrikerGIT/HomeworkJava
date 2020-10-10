public class Person {
    public final String firstName;
    public final String lastName;
    public final String middleName;
    public final String country;
    public final String address;
    public final String phone;
    public final int age;
    public final String gender;

    //Реализация Builder через статический внутренний класс
    public static class Builder {

        //Обязательные параметры
        public String firstName;
        public String lastName;
        public String middleName;

        //Необязательные параметры со значениями по умолчанию
        public String country = "";
        public String address = "";
        public String phone = "";
        public int age = 0;
        public String gender = "";


        //Конструктор с обязательными параметрами
        public Builder(String firstName, String lastName, String middleName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
        }

        //Методы с возвращающим типом Builder для необязательных параметров
        public Builder country(String val) {
            country = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder gender(String val) {
            gender = val;
            return this;
        }

        //Метод с возвращающим типом Good для генерации объекта
        public Person buidl() {
            return new Person(this);
        }
    }

    private Person(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        middleName = builder.middleName;
        country = builder.country;
        address = builder.address;
        phone = builder.phone;
        age = builder.age;
        gender = builder.gender;
    }
}