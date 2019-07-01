package collections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MinOfNumbers {
    public static void main(String[] args) throws Exception {
        List<Integer> integerList = getIntegerList();

        System.out.println(getMinimum(integerList));
    }

    public static int getMinimum(List<Integer> list) {
        //Ваш код
        int min = list.get(0);
        for(Integer num : list) {
            min = min > num ? num : min;
        }

        return min;
    }

    public static List<Integer> getIntegerList() throws IOException {
        //Ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Type count of numbers: ");
        int cntNumbers = Integer.parseInt(reader.readLine());

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < cntNumbers; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }

        return list;
    }
}
