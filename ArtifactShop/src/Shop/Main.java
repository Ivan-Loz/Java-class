package Shop;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Path FILE = Path.of("shop.txt");
    private static ArrayList<Item> items = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Лавка странных артефактов");
        System.out.println("Файл: " + FILE.toAbsolutePath());
        while (true) {
            showMenu();
            int command = readInt("Команда: ", 0, 8);
            switch (command) {
                case 1 -> showItems();
                case 2 -> addItem();
                case 3 -> saveItems();
                case 4 -> loadItems();
                case 5 -> showCheapItems();
                case 6 -> showTotalPrice();
                case 7 -> removeItem();
                case 8 -> findGiftPair();
                case 0 -> {
                    System.out.println("Лавка закрыта. Несохранённые изменения потеряются.");
                    return;
                }
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n1 — Показать товары | 2 — Добавить товар");
        System.out.println("3 — Сохранить | 4 — Загрузить");
        System.out.println("5 — Недорогие товары | 6 — Общая стоимость");
        System.out.println("7 — Удалить товар | 8 — Выбрать подарок");
        System.out.println("0 — Выход без сохранения");

    }

    private static void showItems() {
        if (items.isEmpty()) {
            System.out.println("Лавка пуста. Даже пауки съехали.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName()
                    + " — " + item.getPrice() + " монет");
        }
    }

    private static void addItem() {
        String name;
        while (true) {
            System.out.print("Название: ");
            name = scanner.nextLine().strip();
            if (!name.isBlank() && !name.contains(";")) {
                break;
            }
            System.out.println("Введите непустое название без ;.");
        }
        int price = readInt("Цена: ", 1, 999);
        items.add(new Item(name, price));
        System.out.println("Товар добавлен.");
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().strip());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Введите число от " + min + " до " + max + ".");
            } catch (NumberFormatException exception) {
                System.out.println("Нужно целое число.");
            }
        }
    }

    private static void saveItems() {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE, StandardCharsets.UTF_8)) {
            for (Item item : items) {
                writer.write(item.toLine());
                writer.newLine();
            }
        } catch (IOException exception) {
            System.out.println("Ошибка сохранения: " + exception.getMessage());
            return;
        }
        System.out.println("Записано товаров: " + items.size());
    }

    private static void loadItems() {
        try {
            ArrayList<Item> loaded = new ArrayList<>();
            for (String line : Files.readAllLines(FILE, StandardCharsets.UTF_8)) {
                loaded.add(Item.fromLine(line));
            }
            items = loaded;
            System.out.println("Загружено товаров: " + items.size());
        } catch (NoSuchFileException exception) {
            System.out.println("Файл не найден. Сначала сохраните товары.");
        } catch (IOException exception) {
            System.out.println("Ошибка чтения: " + exception.getMessage());
        } catch (IllegalArgumentException exception) {
            System.out.println("Неверные данные в файле: " + exception.getMessage());
        }
    }


    private static void showCheapItems() {
        if (items.isEmpty()) {
            System.out.println("Лавка пуста. Искать нечего.");
            return;
        }

        System.out.println("Выберите максимальную цену.");
        int maxPrice = readInt("Цена: ", 1, 999);

        ArrayList<Item> cheapItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getPrice() <= maxPrice) {
                cheapItems.add(item);
            }
        }

        if (cheapItems.isEmpty()) {
            System.out.println("Товаров не нашли.");
        }
        else {
            System.out.println("Товаров найдено: " + cheapItems.size() + ".");
            for (int i = 0; i < cheapItems.size(); i++) {
                System.out.print((i + 1) + ". ");
                cheapItems.get(i).printInfo();
            }
        }
    }

    private static void showTotalPrice() {
        int summaPrice = 0;
        int count = 0;
        if (items.isEmpty()) {
            System.out.println("Лавка пуста. Искать нечего.");
            return;
        }
        for (Item item : items) {
            count++;
            summaPrice += item.getPrice();
        }
            System.out.println("Товаров : " + count + ".");

                System.out.println("Общая стоимость товаров: "+ summaPrice);
    }


    private static void removeItem()
    {
        if (items.isEmpty()) {
            System.out.println("Лавка пуста. Искать нечего.");
            return;
        }
        showItems();
        int removeId = readInt("Номер товара: ", 1, items.size());
        if (items.get(removeId-1) != null){
            items.remove(removeId-1);
            System.out.println("Товар удалён");
        }
    }
    private static void findGiftPair(){
        System.out.println("Выберите цену.");
        int Price = readInt("Цена: ", 1, 1998);
        int change= 1998;
        int summa = 0;
        int temporaryСhange;
        ArrayList<Item> itemsGood = new ArrayList<>();
        ArrayList<Item> cheapItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getPrice() <= Price) {
                cheapItems.add(item);
            }
        }
        if (cheapItems.isEmpty()) {
            System.out.println("Лавка пуста. Искать нечего.");
            return;
        }
        while (true){
            if (cheapItems.size() % 2 ==0){
                int count = 0;
                for (int i = 0;i < cheapItems.size();i++){
                    summa += cheapItems.get(i).getPrice();
                    count++;
                    if(count == 2){
                        count = 0;
                        if(Price - summa >= 0){
                            temporaryСhange = Price - summa;
                            if (temporaryСhange <= change){
                                change = temporaryСhange;
                                itemsGood.add(cheapItems.get(i));
                            }
                        }
                    }

                }
                for (Item item : itemsGood) {
                    item.printInfo();
                }
                System.out.println("сумма: "+ summa);
                System.out.println("здача: "+ change);
                break;
            }
            else {
                cheapItems.remove(cheapItems.size()-1);
            }
        }


    }

}
