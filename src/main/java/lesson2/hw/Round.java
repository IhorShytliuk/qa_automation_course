package lesson2.hw;

import java.util.Scanner;

public class Round {
    /*
        3. В переменной n хранится вещественное число с ненулевой дробной частью.
        Создайте программу, округляющую число n до ближайшего целого и выводящую результат на экран.
    */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please type float number:");
        float s = in.nextFloat();

        System.out.println("Integer part of " + s + " is " + Math.round(s));
    }
}
