package lesson06;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
 private static final ArrayList<Hero> heroes = new ArrayList<>();
 private static final Scanner scanner = new Scanner(System.in);

         public static void main(String[] args) {
 heroes.add(new Hero("Борис", "Рыцарь с боязнью высоты", 7));
 heroes.add(new Hero("Луна", "Маг бытовых заклинаний", 12));
 heroes.add(new Hero("Плюш", "Укротитель диванов", 3));

 boolean running = true;
 while (running) {
 printMenu();
 int command = readInt("Команда: ", 0, 6);

 switch (command) {
 case 1:
 showHeroes();
 break;
 case 2:
 addHero();
 break;
 case 3:
 searchHero();
 break;
 case 4:
 changeLevel();
 break;
case 5:
 removeHero();
 break;
  case 6:
   selectParty();
   break;
 case 0:
 running = false;
 break;
 }
 }

 scanner.close();
 System.out.println("Гильдия закрыта. Данные этого запуска не сохранены.");
 }
 private static void selectParty(){
  int level = readInt("Уровень: ", 1, 100);
  int count = 0;
  for (Hero hero : heroes){
   if (hero.getLevel() >= level){
      count++;
   }
  }
  if (count > 0){
   System.out.println("На квест подойдут Героев: " + count+".");
  }
  else {
   System.out.println("На квест не кого не нашли! Пусть качаются." );
  }
 }

         private static void printMenu() {
 System.out.println();
 System.out.println("=== ГИЛЬДИЯ ПРИКЛЮЧЕНЦЕВ ===");
 System.out.println("1 — Показать героев");
 System.out.println("2 — Зарегистрировать героя");
 System.out.println("3 — Найти героя");
System.out.println("4 — Изменить уровень");
 System.out.println("5 — Исключить героя из гильдии");
          System.out.println("6 — Сборы на квест");
 System.out.println("0 — Выход");
 }

         private static void showHeroes() {
if (heroes.isEmpty()) {
 System.out.println("В гильдии пока никого нет. Даже бард ушёл.");
return;
 }
 System.out.println("Участников: " + heroes.size());
 for (Hero hero : heroes) {
 hero.printInfo();
 }
 }

         private static void addHero() {
 String name = readText("Имя нового героя: ");
 if (findByName(name) != null) {
 System.out.println("Такое имя уже занято. Выберите другое при регистрации.");
 return;
 }

 String profession = readText("Профессия: ");
int level = readInt("Уровень: ", Hero.MIN_LEVEL, Hero.MAX_LEVEL);
 heroes.add(new Hero(name, profession, level));
 System.out.println("Герой принят. Ключ от комнаты выдаёт дракон на проходной.");
 }

         private static Hero findByName(String name) {
 for (Hero hero : heroes) {
 if (hero.getName().equalsIgnoreCase(name)) {
return hero;
 }
 }
 return null;
 }

        private static void searchHero() {
 String name = readText("Кого ищем: ");
 Hero hero = findByName(name);
if (hero == null) {
 System.out.println("Герой не найден.");
 return;
 }
 hero.printInfo();
 }

         private static void changeLevel() {
String name = readText("Чей уровень изменить: ");
 Hero hero = findByName(name);
 if (hero == null) {
 System.out.println("Герой не найден.");
 return;
 }

 System.out.println("Текущий уровень: " + hero.getLevel());
 int level = readInt("Новый уровень: ", Hero.MIN_LEVEL, Hero.MAX_LEVEL);
 if (hero.setLevel(level)) {
 System.out.println("Уровень изменён.");
 hero.printInfo();
 } else {
 System.out.println("Уровень не изменён: недопустимое значение.");
 }
 }

         private static void removeHero() {
 String name = readText("Кого исключить из гильдии: ");
 Hero hero = findByName(name);
 if (hero == null) {
 System.out.println("Герой не найден.");
 return;
 }

hero.printInfo();
 int confirmation = readInt("Исключить? 1 — да, 0 — отмена: ", 0, 1);
  if (confirmation == 0) {
 System.out.println("Удаление отменено.");
 return;
 }

 if (heroes.remove(hero)) {
 System.out.println("Герой исключён из гильдии. Тапочки оставил себе.");
 } else {
 System.out.println("Удаление не выполнено.");
 }
 }

         private static String readText(String prompt) {
 while (true) {
System.out.print(prompt);
 String text = scanner.nextLine().strip();
 if (!text.isBlank()) {
 return text;
 }
 System.out.println("Пустая строка не подходит. Попробуйте ещё раз.");
 }
 }

         private static int readInt(String prompt, int min, int max) {
 while (true) {
 System.out.print(prompt);
 String text = scanner.nextLine().strip();
 try {
 int value = Integer.parseInt(text);
 if (value < min || value > max) {
 System.out.println("Введите число от " + min + " до " + max + ".");
 continue;
 }
 return value;
 } catch (NumberFormatException exception) {
 System.out.println("Нужно целое число, например 7.");
 }
 }
 }
}