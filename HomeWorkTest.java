import Lesson_6.HW_MyClass;
import org.junit.*;

public class HomeWorkTest {
    HW_MyClass myClass;

    @Before
    public void init() {
        myClass = new HW_MyClass();
    }

    @Test
    public void testRepack1() {
        int[] myArray = {1,2,5,3,6,4,7,5,3,6,2,3,5};
        int[] resultArray = {7,5,3,6,2,3,5};
        Assert.assertNotEquals(resultArray, myClass.repack(myArray));
    }

    @Test
    public void testRepack2() {
        int[] myArray = {1,2,5,3,6,4,7,4,3,6,1,3,5};
        int[] resultArray = {3,6,1,3,5};
        Assert.assertNotEquals(resultArray, myClass.repack(myArray));
    }

    @Test
    public void testRepack3() {
        int[] myArray = {1,2,5,3,6,4,7,5,3,6,4,4,5};
        int[] resultArray = {5};
        Assert.assertNotEquals(resultArray, myClass.repack(myArray));
    }
    @Test
    public void testFind1() {
        int[] myArray2 = {2,2,5,3,6,4,7,5,3,6,4,4,5};
        Assert.assertEquals(true, myClass.findNum(myArray2));
    }
    @Test
    public void testFind2() {
        int[] myArray2= {1,2,5,3,6,2,7,5,3,6,1,3,5};
        Assert.assertEquals(true, myClass.findNum(myArray2));
    }
    @Test
    public void testFind3() {
        int[] myArray2 = {0,2,5,3,6,8,7,5,3,6,7,0,5};
        Assert.assertEquals(false, myClass.findNum(myArray2));
    }

}
