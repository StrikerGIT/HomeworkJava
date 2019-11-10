

public class Test1 {

    @BeforeSuite
    public void init() {
        System.out.println("BeforeSuite");
    }

    @Test(priotity = 1)
    public void test1() {
        System.out.println("Test 1");
    }

    @Test(priotity = 2)
    public void test2() {
        System.out.println("Test 2");
    }

    @Test(priotity = 3)
    public void test3() {
        System.out.println("Test 3");
    }

    @Test(priotity = 4)
    public void test4() {
        System.out.println("Test 4");
    }

    @AfterSuite
    public void after() {
        System.out.println("AfterSuite");
    }

}
