package meta_space;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OutOfMetaspaceErrorCreator {

    public void generateOOME() {
        List<MyClassLoader> classLoaderList = new ArrayList<>();
        //Получаем список областей памяти JVM
        List<MemoryPoolMXBean> memPool = ManagementFactory.getMemoryPoolMXBeans();
        //Ищем среди областей ту, у которой есть имя "Metaspace"
        final Optional<MemoryPoolMXBean> metaspaceBean = memPool.stream().filter(
                (bean) -> bean.getName().equals("Metaspace")).findAny();

        while (true) {
            for (int i = 0; i < 10_000; i++) {
                MyClassLoader myClassLoader = new MyClassLoader();
                Class<?> c = null;
                try {
                    c = myClassLoader.findClass("BigBoy");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                classLoaderList.add(myClassLoader);
            }
            if (metaspaceBean.isPresent()) {
                System.out.println(metaspaceBean.get().getUsage());
            }
        }
    }

    private static class MyClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] buff = Files.readAllBytes(new File("BigBoy.class").toPath());
                return defineClass(null, buff, 0, buff.length);
            } catch (IOException e) {
                throw new ClassNotFoundException();
            }
        }
    }


}
