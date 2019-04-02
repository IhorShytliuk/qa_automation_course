package lesson2.hw;

import java.util.Scanner;

public class SumDigits {
    /*
        2. В переменной n хранится натуральное двузначное число.
        Создайте программу, вычисляющую и выводящую на экран сумму цифр числа n.

        3. В переменной n хранится вещественное число с ненулевой дробной частью. Создайте программу, округляющую число n до ближайшего целого и выводящую результат на экран.
    */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please type number:");
        String s = in.next();

        int sum = 0;

        for(char c: s.toCharArray()) {
            sum += Integer.parseInt(String.valueOf(c));
        }

        System.out.println("Digits sum of " + s + " is " + sum);
    }
}
