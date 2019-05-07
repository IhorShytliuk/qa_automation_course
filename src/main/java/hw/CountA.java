package hw;

import java.util.ArrayList;
import java.util.List;

public class CountA {
    public static void main(String[] args) {
        String input = "aasss,assdfaasdqweaaa,asdaaaas,adsasdasd,aaa,asdghnzzzzzza,zzzzxxxaaaa,zxvvxvxcvxcv,xcvxv.fsdfsdfeweqwejhsdf sf sdfs a a sdfsdf sdf";

        int countA = 0;
        char[] inputArr = input.toCharArray();
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < inputArr.length; i++) {
            if(inputArr[i] == 'a') {
                countA++;
                positions.add(i);
            }
        }

        System.out.println("Кол-во символов \"a\" - " + countA + ", индексы символа \"a\" в строке - " + positions);
    }
}
