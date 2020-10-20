import heap_oom.OutOfMemoryErrorCreator;
import meta_space.OutOfMetaspaceErrorCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        //new OutOfMemoryErrorCreator().generateOOM();

        new OutOfMetaspaceErrorCreator().generateOOME();
    }


}
