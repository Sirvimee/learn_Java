package junit;

public class Code {

    public static boolean isSpecial(int candidate) {
        return candidate % 11 < 4;
    }

    public static int longestStreak(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return 0;
        }

        int longest = 0;
        Character lastChar = null;
        int currentStreakLength = 0;

        for (Character currentChar : inputString.toCharArray()) {
            if (currentChar.equals(lastChar)) {
                currentStreakLength++;
            } else {
                currentStreakLength = 1;
            }

            if (currentStreakLength > longest) {
                longest = currentStreakLength;
            }

            lastChar = currentChar;
        }
        return longest;
    }

    public static Character mode(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        } else {
            int currentCount = 0;
            char letter = 0;
            char targetCharacter;
            for (int i = 0; i < input.length(); i++) {
                targetCharacter = input.charAt(i);
                int count = getCharacterCount(input, targetCharacter);
                if (count > currentCount) {
                    currentCount = count;
                    letter = targetCharacter;
                }
            }
            return letter;
        }
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        int count = 0;
        for (int i = 0; i < allCharacters.length(); i++) {
            if (allCharacters.charAt(i) == targetCharacter) {
                count ++;
            }
        }
        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        String numbers = "";
        for (int num : integers) {
            String number = String.valueOf(num);
            boolean result = numberIsInString(numbers, number);
            if (!result) {
                numbers += num + " ";
            }
        }

        String[] numberStrings = numbers.trim().split("\\s+");
        int[] newIntegers = new int[numberStrings.length];

        for (int i = 0; i < numberStrings.length; i++) {
            newIntegers[i] = Integer.parseInt(numberStrings[i]);
        }
        return newIntegers;
    }

    public static boolean numberIsInString(String numbers, String number) {
        String[] words = numbers.split("\\s+");

        for (String word : words) {
            if (word.equals(number)) {
                return true;
            }
        }
        return false;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int sum = 0;
        for (int value : removeDuplicates(integers)) {
            sum += value;
        }
        return sum;
    }

}
