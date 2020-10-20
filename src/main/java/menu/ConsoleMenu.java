package menu;

import heap_oom.OutOfMemoryErrorCreator;
import meta_space.OutOfMetaspaceErrorCreator;

import java.util.Scanner;

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
