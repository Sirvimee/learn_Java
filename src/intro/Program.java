package intro;


public class Program {

    public static void main(String[] args) {

        int integer = asInteger("11001101");
        String string = asBinaryString(205);
        System.out.println(integer); // 205
        System.out.println(string);
    }

    public static String asBinaryString(int input) {
        String result = "";
        int number = input;
        while (number != 0) {
            if (number % 2 == 1) {
                result = "1" + result;
            }
            if (number % 2 == 0) {
                result = "0" + result;
            }
            number = number / 2;
        }
        return result;
    }

    public static int asInteger(String input) {

        //reverse the word
        char ch;
        String reversedWord = "";

        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            reversedWord = ch + reversedWord;
        }

        int result = 0;

        for (int i = 0; i < reversedWord.length(); i++) {
            int number = reversedWord.charAt(i) - '0';
            result = result + number * pow(2, i);
        }
        return result;
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.
        int result = 1;
        for (int i = 0; i < power; i++) {
            result = result * arg;
        }
        return result;
    }
}
