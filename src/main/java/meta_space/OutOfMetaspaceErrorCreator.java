package meta_space;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс для заполнения памяти в Metaspace.
 * Загружает преобразованный байт-код класса BigBoss
 * в список, пока приложение не завершится
 * с ошибкой OutOfMemoryError в Metaspace.
 */
public class OutOfMetaspaceErrorCreator {

    /**
     * Метод для завершения программы с ошибкой OutOfMemoryError в Metaspace.
     */
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

    /**
     * Метод записывает класс BigBoy в байткод и
     * возвращает преобразованный байт-код в java.lang.Class, осуществляя его валидацию;
     */
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
