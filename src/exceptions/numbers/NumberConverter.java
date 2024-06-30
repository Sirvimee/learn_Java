package exceptions.numbers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class NumberConverter {

    Properties properties = new Properties();

    public NumberConverter(String lang) {

        String filePath = "src/exceptions/numbers/numbers_" + lang + ".properties";
        FileInputStream is = null;

        try {
            is = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(
                    is, StandardCharsets.ISO_8859_1);

            properties.load(reader);
        } catch (FileNotFoundException e) {
            throw new MissingLanguageFileException(lang, e);
        } catch (IllegalArgumentException e) {
            throw new BrokenLanguageFileException(lang, e);
        } catch (IOException e) {
            if (e.getMessage().contains("numbers_" + lang + ".properties")) {
                throw new MissingLanguageFileException(lang, e);
            } else {
                throw new BrokenLanguageFileException(lang, e);
            }
        } finally {
            close(is);
        }
    }

    public String numberInWords(Integer number) {
        String numberAsString = String.valueOf(number);

        if (number < 10 && !properties.containsKey(numberAsString)) {
            throw new MissingTranslationException(numberAsString);
        } else if (properties.containsKey(numberAsString)) {
            return properties.getProperty(numberAsString);
        } else if (number < 20) {
            return convertTeens(number);
        } else if (number < 100) {
            return convertTens(number);
        } else if (number < 1000) {
            return convertHundreds(number);
        } else if (number < 1000000) {
            return convertThousands(number);
        } else if (number < 1000000000) {
            return convertMillions(number);
        } else if (number == 1000000000) {
            String billionSingular = properties.getProperty("billion-singular");
            return properties.getProperty("1") + properties.getProperty("billion-before-delimiter") + billionSingular;
        }
        return "Number is too large";
    }

    private String convertTeens(Integer number) {
        return properties.getProperty(String.valueOf(number % 10)) + properties.getProperty("teen");
    }

    private String convertTens(Integer number) {
        String tens = properties.getProperty(String.valueOf(number / 10 * 10));
        Integer ones = number % 10;
        String tensSuffix = properties.getProperty("tens-suffix");
        String tensDelimiter = properties.getProperty("tens-after-delimiter");

        if (tens != null) {
            if (ones == 0) {
                return tens;
            } else {
                return tens + tensDelimiter + properties.getProperty(String.valueOf(number % 10));
            }
        } else {
            if (ones == 0) {
                return properties.getProperty(String.valueOf(number / 10)) + tensSuffix;
            }
            tens = properties.getProperty(String.valueOf(number / 10));
            return tens + tensSuffix + tensDelimiter + properties.getProperty(String.valueOf(number % 10));
        }
    }

    private String convertHundreds(Integer number) {
        String hundreds = properties.getProperty(String.valueOf(number / 100));
        Integer tens = number % 100;
        String hundred = properties.getProperty("hundred");
        String hundredBeforeDelimiter = properties.getProperty("hundred-before-delimiter");

        if (tens == 0) {
            return hundreds + hundredBeforeDelimiter + hundred;
        }
        return hundreds + hundredBeforeDelimiter + hundred +
                properties.getProperty("hundred-after-delimiter") + numberInWords(tens);
    }

    private String convertThousands(Integer number) {
        Integer thousands = number / 1000;
        Integer hundreds = number % 1000;
        String thousand = properties.getProperty("thousand");
        String thousandBeforeDelimiter = properties.getProperty("thousand-before-delimiter");

        if (hundreds == 0) {
            return numberInWords(thousands) + thousandBeforeDelimiter + thousand;
        }
        return numberInWords(thousands) + thousandBeforeDelimiter + thousand +
                properties.getProperty("thousand-after-delimiter") + numberInWords(hundreds);
    }

    private String convertMillions(Integer number) {
        Integer millions = number / 1000000;
        String millionBeforeDelimiter = properties.getProperty("million-before-delimiter");
        String millionSingular = properties.getProperty("million-singular");

        if (number == 1000000) {
            return properties.getProperty(String.valueOf(millions)) + millionBeforeDelimiter + millionSingular;
        }
        if (millions < 2) {
            return properties.getProperty(String.valueOf(millions)) + millionBeforeDelimiter + millionSingular +
                    properties.getProperty("million-after-delimiter") + numberInWords(number % 1000000);
        }
        if (number % 1000000 == 0) {
            return numberInWords(millions) + millionBeforeDelimiter + properties.getProperty("million-plural");
        }
        return numberInWords(millions) + millionBeforeDelimiter + properties.getProperty("million-plural") +
                properties.getProperty("million-after-delimiter") + numberInWords(number % 1000000);
    }

    private static void close(FileInputStream is) {
        if (is == null) {
            return;
        }

        try {
            is.close();
        } catch (IOException ignore) {}
    }

}