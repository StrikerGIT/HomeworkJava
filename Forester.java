import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Forester {
    public HashMap<Integer, Integer> forest;

    Forester() {
        forest = new HashMap<Integer, Integer>();
    }

    public void start(int [] trees){
        for (int tree:trees) {
            if (!forest.containsKey(tree)) {
                forest.put(tree,1);
            }
            else {
                forest.put(tree,forest.get(tree) + 1);
            }
        }
        for (Map.Entry<?, ?> entry : forest.entrySet()) {
            System.out.printf("key = %s, value = %s\r\n", entry.getKey(), entry.getValue());
        }    }
}
