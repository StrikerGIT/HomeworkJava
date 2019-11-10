public class Main {

    public static void main(String[] args) {
        Test1 test = new Test1();
        Class t1 = Test1.class;
        try {
            TestStarter.start(test,t1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
