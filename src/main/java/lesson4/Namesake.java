package lesson4;

import java.util.ArrayList;
import java.util.List;

public class Namesake {

    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        names.add("Vova");
        names.add("Petro");
        names.add("Yulia");

        String name1 = names.get((int)(Math.random()*names.size()));
        String name2 = names.get((int)(Math.random()*names.size()));

        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name1.equals(name2) ? "These people are namesakes" : "These people aren't namesakes");
    }
}
