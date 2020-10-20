package menu;

import heap_oom.OutOfMemoryErrorCreator;
import meta_space.OutOfMetaspaceErrorCreator;

import java.util.Scanner;

/**
 * Консольное меню проекта.
 * Дает возможнсоть запуска методов из консоли.
 */
public class ConsoleMenu {
    private final Scanner scanner;

    public ConsoleMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printMenu() {
        System.out.println("\n1. Вызвать ошибку OutOfMemoryError c пометкой Java Heap Space.");
        System.out.println("2. Вызвать ошибку OutOfMemoryError в Metaspace.");
        System.out.println("3. Выход из приложения.");
    }

    /**
     * Метод запуска консольного меню.
     * 1) Запуск метода OutOfMemoryErrorCreator().generateOOM()
     * Чтобы через некоторое время программа завершилась
     * с ошибкой OutOfMemoryError c пометкой Java Heap Space.
     *
     * 2) Запуск метода OutOfMetaspaceErrorCreator().generateOOME()
     * Чтобы через некоторое время программа завершилась
     * с ошибкой OutOfMemoryError в Metaspace.
     *
     * 3) Завершение программы.
     * При невалидном вводе, выводит "заглушку".
     */
    public void start() {
        if (this.scanner != null) {
            int key;
            do {
                printMenu();
                System.out.print("\nВведите номер меню: ");
                key = this.scanner.nextInt();
                switch (key) {
                    case 1:
                        new OutOfMemoryErrorCreator().generateOOM();
                        break;
                    case 2:
                        new OutOfMetaspaceErrorCreator().generateOOME();
                        break;
                    case 3:
                        System.out.println("\nЗавершение программы...");
                        break;
                    default:
                        System.out.println("\nВы ввели неверное значение меню...");
                }
            } while (key != 3);
        }
    }
}
