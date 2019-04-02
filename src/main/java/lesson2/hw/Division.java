package lesson2.hw;

import java.util.Scanner;

public class Division {
    /*
        1. В переменных q и w хранятся два натуральных числа.
        Создайте программу, выводящую на экран результат деления q на w с остатком.
        Пример вывода программы (для случая, когда в q хранится 21, а в w хранится 8):
        21 / 8 = 2 и 5 в остатке
    */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please type first number:");
        int first = in.nextInt();

        System.out.println("Please type second number:");
        int second = in.nextInt();

        System.out.println(first + " / " + second + " = " + first/second + " rest " + first%second);
    }
}
