package lesson2.hw;

import java.util.Scanner;

public class ConsoleCharToInt {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please type one symbol:");
        String s = in.next();
        char c = s.charAt(0);

        System.out.println("Symbol " + c + " has code: " + (int)c);
    }
}
