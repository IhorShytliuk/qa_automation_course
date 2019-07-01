package collections;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

import java.util.*;

public class TenCats
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        //Ваш код
        Map<String, Cat> cats = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            String catName = "Cat" + i;
            cats.put(catName, new Cat(catName));
        }

        return cats;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        //Ваш код
        return new LinkedHashSet<>(map.values());
    }

    public static void printCatSet(Set<Cat> set) {
        //Ваш код
        for(Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }
}
