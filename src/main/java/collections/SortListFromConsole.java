package collections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SortListFromConsole {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = new ArrayList<>();

        //заполните список строками при помощи reader.readLine();
        String tmp;
        do {
            System.out.println("Type string number " + list.size() + ": ");
            tmp = reader.readLine();
            list.add(tmp);
        } while (!tmp.equals(""));

        sort(list); //метод для сортировки списка

        //вывести список в отсортированном порядке
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void sort(List<String> list) {
        //реализуйте свой алгоритм сортировки списка при помощи  метода isGreaterThan(String a, String b)
        Collections.sort(list, new StrCustomComparator());
    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static int isGreaterThan(String a, String b) {
        return a.compareTo(b);
    }

    static class StrCustomComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return isGreaterThan(s1, s2);
        }
    }
}