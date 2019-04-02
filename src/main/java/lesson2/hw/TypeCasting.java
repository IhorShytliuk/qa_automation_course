package lesson2.hw;

public class TypeCasting {
    public static void main(String[] args) {

        //int => char
        //float => char
        //float => char int

        int i1 = 1048;
        System.out.println((char) i1);

        float f1 = 1024.5f;
        System.out.println((char) f1);

        System.out.println((int) ((char) f1));


    }
}
