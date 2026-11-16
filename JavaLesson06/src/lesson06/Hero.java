package lesson06;

public class Hero {
    private int Id;
 public static final int MIN_LEVEL = 1;
 public static final int MAX_LEVEL = 100;

         private final String name;
 private final String profession;
 private int level;

         public Hero(String name, String profession, int level) {
 if (name == null || name.isBlank()) {
 throw new IllegalArgumentException("Имя не должно быть пустым");
 }
 if (profession == null || profession.isBlank()) {
 throw new IllegalArgumentException("Профессия не должна быть пустой");
 }
 if (level < MIN_LEVEL || level > MAX_LEVEL) {
 throw new IllegalArgumentException("Уровень должен быть от 1 до 100");
 }
 this.name = name.strip();
 this.profession = profession.strip();
 this.level = level;
 }

         public String getName() {
 return name;
 }

         public int  GetId(){return Id;}

         public int getLevel() {
 return level;
 }

         public boolean setLevel(int level) {
 if (level < MIN_LEVEL || level > MAX_LEVEL) {
 return false;
 }
 this.level = level;
 return true;
 }

         public void printInfo() {
 System.out.println(Id + " | " +name + " | " + profession + " | уровень " + level);
     }
}