package Shop;

public class Item {
    private final String name;
    private final int price;

    public Item(String name, int price) {
        if (name == null || name.isBlank() || name.contains(";")
                || name.contains("\n") || name.contains("\r")) {
            throw new IllegalArgumentException("Неверное название товара.");
        }
        if (price < 1 || price > 999) {
            throw new IllegalArgumentException("Цена должна быть от 1 до 999.");
        }
        this.name = name.strip();
        this.price = price;
    }
    public void printInfo(){
        System.out.println(name+" — "+price+" монет");
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String toLine() {
        return name + ";" + price;
    }

    public static Item fromLine(String line) {
        String[] parts = line.split(";", -1);
        if (parts.length != 2) {
            throw new IllegalArgumentException("В строке должны быть название и цена.");
        }
        return new Item(parts[0], Integer.parseInt(parts[1]));
    }
}

