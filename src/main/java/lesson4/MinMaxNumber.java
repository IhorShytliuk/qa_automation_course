package lesson4;

import java.util.ArrayList;
import java.util.List;

public class MinMaxNumber {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            int tmp = (int) (Math.random()*10);
            nums.add(tmp);
            System.out.print(nums.get(i) + " ");
        }

        int max = nums.get(0);
        int min = nums.get(0);

        for (int i : nums) {
            max = i > max ? i : max;
            min = i < min ? i : min;
        }
        System.out.println("\nMin: " + min);

        int maxCount = 0;
        for (int i : nums) {
            if (i == max) {
                maxCount++;
            }
        }
        System.out.println("Max: " + max + "\nMaxCount: " + maxCount);

    }
}
