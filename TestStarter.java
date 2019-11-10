import java.lang.reflect.Method;
import java.util.*;
import java.util.Comparator;

public class TestStarter {
    public static void start (Object inClass, Class t) throws Exception{


        Method[] methods = t.getDeclaredMethods();
        ArrayList<Method> testsMetods = new ArrayList<Method>();
        int repitMetod = 0;

        for (Method m: methods ) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                repitMetod++;
                if (repitMetod == 2){
                    System.out.println("Метод с аннтоацией BeforeSuite использован более одного раза");
                    throw new RuntimeException();
                }
                m.invoke(inClass);
            }
        }

        for (Method m: methods ) {
            if (m.isAnnotationPresent(Test.class)) {
                testsMetods.add(m); //m.getAnnotation(Test.class).priotity());
            }
        }
        Comparator<Method> comparator = Comparator.comparing(Method -> Method.getAnnotation(Test.class).priotity());
        Collections.sort(testsMetods, comparator);

        for (Method m:testsMetods) {
            m.invoke(inClass);
        }
        repitMetod = 0;
        for (Method m: methods ) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                repitMetod++;
                if (repitMetod == 2){
                    System.out.println("Метод с аннтоацией AfterSuite использован более одного раза");
                    throw new RuntimeException();
                }
                m.invoke(inClass);
            }
        }
    }
}
