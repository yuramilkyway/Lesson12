package heap_oom;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorCreator {

    public void generateOOM() {
        List<BigObject> permanent = new ArrayList<BigObject>();
        List<BigObject> temp = new ArrayList<BigObject>();

        while (true) {
            for (int i = 0; i < 100; i++) {
                permanent.add(new BigObject());
                temp.add(new BigObject());
            }
            System.out.println("Used MB: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024*1024));
            System.out.println("Free MB: " + (Runtime.getRuntime().freeMemory()) / (1024*1024));
            temp.clear();
        }
    }

    private class BigObject {
        private Double[] array = new Double[1_000_000];
    }
}
