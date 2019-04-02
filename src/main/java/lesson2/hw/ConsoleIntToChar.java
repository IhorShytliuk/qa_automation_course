package lesson2.hw;

import java.util.Scanner;

public class ConsoleIntToChar {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please type symbol code:");
        int code = in.nextInt();

        System.out.println("Code " + code + " matched symbol " + (char)code);
    }
}
