package com.kenzie.discussion.cli.input;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Scanner;

/**
 * Gets input from the user.
 */
public class ATAUserInput {
    private final Scanner scanner = new Scanner(new InputStreamReader(System.in, Charset.forName("UTF-8")));

    /**
     * Get a single letter from the user.
     *
     * @return user input
     */
    public char getLetter() {
        return getLetter(null);
    }

    /**
     * Get a single letter from the user.
     *
     * @param message Message to prompt the user with
     * @return user input
     */
    public char getLetter(String message) {
        printMessage(message);
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            line = line.trim();
            if (line.isEmpty() || line.length() > 1 || !line.matches("[a-zA-Z]")) {
                System.out.println(String.format("Invalid letter: '%s'.", line));
                printMessage(message);
            } else {
                break;
            }
        }
        return line.charAt(0);
    }

    /**
     * Get an integer from the user.
     *
     * @return user input
     */
    public int getInteger() {
        return getInteger(null);
    }

    /**
     * Get an integer from the user.
     *
     * @param message Message to prompt the user with
     * @return user input
     */
    public int getInteger(String message) {
        return getInteger(Integer.MIN_VALUE, Integer.MAX_VALUE, message);
    }

    /**
     * Get an integer from the user inclusively between two numbers.
     *
     * @param min The minimum possible acceptable answer
     * @param max The maximum possible acceptable answer
     * @return user input
     */
    public int getInteger(int min, int max) {
        return getInteger(min, max, null);
    }

    /**
     * Get an integer from the user inclusively between two numbers.
     *
     * @param min     The minimum possible acceptable answer
     * @param max     The maximum possible acceptable answer
     * @param message Message to prompt the user with
     * @return user input
     */
    public int getInteger(int min, int max, String message) {
        while (true) {
            printMessage(message);
            if (scanner.hasNextInt()) {
                final int userInput = scanner.nextInt();
                consumeTrailingNewline();

                if (userInput >= min && userInput <= max) {
                    return userInput;
                } else {
                    System.out.println(String.format("Number not between %d and %d.", min, max));
                }
            } else {
                System.out.println(String.format("Invalid whole number: '%s'.", scanner.nextLine()));
            }
        }
    }

    /**
     * Get a long from the user.
     *
     * @return user input
     */
    public long getLong() {
        return getLong(null);
    }

    /**
     * Get a long from the user.
     *
     * @param message Message to prompt the user with
     * @return user input
     */
    public long getLong(String message) {
        return getLong(Long.MIN_VALUE, Long.MAX_VALUE, message);
    }

    /**
     * Get a long from the user inclusively between two numbers.
     *
     * @param min The minimum possible acceptable answer
     * @param max The maximum possible acceptable answer
     * @return user input
     */
    public long getLong(long min, long max) {
        return getLong(min, max, null);
    }

    /**
     * Get a long from the user inclusively between two numbers.
     *
     * @param min     The minimum possible acceptable answer
     * @param max     The maximum possible acceptable answer
     * @param message Message to prompt the user with
     * @return user input
     */
    public long getLong(long min, long max, String message) {
        while (true) {
            printMessage(message);
            if (scanner.hasNextLong()) {
                final long userInput = scanner.nextLong();
                consumeTrailingNewline();

                if (userInput >= min && userInput <= max) {
                    return userInput;
                } else {
                    System.out.println(String.format("Number not between %d and %d (%d).", min, max, userInput));
                }
            } else {
                System.out.println(String.format("Invalid whole number: '%s'.", scanner.nextLine()));
            }
        }
    }

    /**
     * Get a double from the user.
     *
     * @return user input
     */
    public double getDouble() {
        return getDouble(null);
    }

    /**
     * Get a double from the user.
     *
     * @param message Message to prompt the user with
     * @return user input
     */
    public double getDouble(String message) {
        return getDouble(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, message);
    }

    /**
     * Get a double from the user inclusively between two numbers.
     *
     * @param min The minimum possible acceptable answer
     * @param max The maximum possible acceptable answer
     * @return user input
     */
    public double getDouble(double min, double max) {
        return getDouble(min, max, null);
    }

    /**
     * Get a double from the user inclusively between two numbers.
     *
     * @param min     The minimum possible acceptable answer
     * @param max     The maximum possible acceptable answer
     * @param message Message to prompt the user with
     * @return user input
     */
    public double getDouble(double min, double max, String message) {
        while (true) {
            printMessage(message);
            if (scanner.hasNextDouble()) {
                final double userInput = scanner.nextDouble();
                consumeTrailingNewline();

                if (Double.compare(userInput, min) >= 0 && Double.compare(userInput, max) <= 0) {
                    return userInput;
                } else {
                    System.out.println(String.format("Number not between %f and %f (%f).", min, max, userInput));
                }
            } else {
                System.out.println(String.format("Invalid number: '%s'.", scanner.nextLine()));
            }
        }
    }

    /**
     * Get a BigDecimal from the user.
     *
     * @return user input
     */
    public BigDecimal getBigDecimal() {
        return getBigDecimal(null);
    }

    /**
     * Get a BigDecimal from the user.
     *
     * @param message Message to prompt the user with
     * @return user input
     */
    public BigDecimal getBigDecimal(String message) {
        while (true) {
            printMessage(message);
            if (scanner.hasNextBigDecimal()) {
                BigDecimal nextBigDecimal = scanner.nextBigDecimal();
                consumeTrailingNewline();

                return nextBigDecimal;
            } else {
                System.out.println(String.format("Invalid number: '%s'.", scanner.nextLine()));
            }
        }
    }

    /**
     * Get a BigDecimal from the user inclusively between two numbers.
     *
     * @param min The minimum possible acceptable answer
     * @param max The maximum possible acceptable answer
     * @return user input
     */
    public BigDecimal getBigDecimal(BigDecimal min, BigDecimal max) {
        return getBigDecimal(min, max, null);
    }

    /**
     * Get a BigDecimal from the user inclusively between two numbers.
     *
     * @param min     The minimum possible acceptable answer
     * @param max     The maximum possible acceptable answer
     * @param message Message to prompt the user with
     * @return user input
     */
    public BigDecimal getBigDecimal(BigDecimal min, BigDecimal max, String message) {
        while (true) {
            printMessage(message);
            if (scanner.hasNextBigDecimal()) {
                final BigDecimal userInput = scanner.nextBigDecimal();
                consumeTrailingNewline();

                if (userInput.compareTo(min) >= 0 && userInput.compareTo(max) <= 0) {
                    return userInput;
                } else {
                    System.out.println(String.format("Number not between %f and %f (%f).", min, max, userInput));
                }
            } else {
                System.out.println(String.format("Invalid number: '%s'.", scanner.nextLine()));
            }
        }
    }

    /**
     * Get a boolean from the user with no prompting.
     *
     * @return user input
     */
    public boolean getBoolean() {
        return getBoolean(null);
    }

    /**
     * Get a boolean from the user, printing a user prompt first.
     *
     * @param message Message to prompt the user with
     * @return user input
     */
    public boolean getBoolean(String message) {
        while (true) {
            printMessage(message);
            if (scanner.hasNextBoolean()) {
                boolean nextBoolean = scanner.nextBoolean();
                consumeTrailingNewline();

                return nextBoolean;
            } else {
                System.out.println(String.format("Invalid boolean: '%s'.", scanner.nextLine()));
            }
        }
    }

    /**
     * Get a String from the user with no prompting and no validation.
     *
     * @return user input
     */
    public String getString() {
        return getString((String) null);
    }

    /**
     * Get a String from the user, printing a user prompt first.
     *
     * @param message      Message to prompt the user with
     * @return user input
     */
    public String getString(String message) {
        return getString(message, null);
    }

    /**
     * Get a String from the user, printing a user prompt first, and printing an optional string
     * at the beginning of the line the user types on (e.g. {@literal"> "}).
     *
     * @param message      Message to prompt the user with
     * @param inlinePrompt What to display at beginning of line user types on
     * @return user input
     */
    public String getString(String message, String inlinePrompt) {
        while (true) {
            printMessage(message);
            printInlinePrompt(inlinePrompt);
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            } else {
                System.out.println(String.format("Invalid entry: '%s'.", scanner.nextLine()));
            }
        }
    }

    /**
     * Get a String from the user without printing any prompt, repeating the request until a
     * response in the valid values is received.
     *
     * @param possibleValues The set of possible valid responses
     * @return user input
     */
    public String getString(Collection<String> possibleValues) {
        return getString(possibleValues, null, null);
    }

    /**
     * Get a String from the user, first printing a prompt to the user, then repeating the request until a
     * response in the valid values is received.
     *
     * @param possibleValues The set of possible valid responses
     * @param message        Message to prompt the user with
     * @return user input
     */
    public String getString(Collection<String> possibleValues, String message) {
        return getString(possibleValues, message, null);
    }

    /**
     * Get a String from the user, first printing a prompt to the user, then repeating the request until a
     * response in the valid values is received. The inlinePrompt parameter allows for printing a string
     * at the beginning of the line the user types on (e.g. {@literal "> "}).
     *
     * @param possibleValues The set of possible valid responses
     * @param message        Message to prompt the user with
     * @param inlinePrompt   What to display at beginning of line user types on
     * @return user input
     */
    public String getString(Collection<String> possibleValues, String message, String inlinePrompt) {
        while (true) {
            printMessage(message);
            printInlinePrompt(inlinePrompt);
            if (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                if (possibleValues.contains(line)) {
                    return line;
                } else {
                    System.out.println(
                        String.format("Answer not valid: '%s'.  Valid responses: %s.", line, possibleValues)
                    );
                }
            } else {
                System.out.println(String.format("Invalid entry: '%s.'", scanner.nextLine()));
            }
        }
    }

    private void printMessage(String message) {
        if (message != null) {
            System.out.println(message);
        }
    }

    private void printInlinePrompt(String prompt) {
        if (prompt != null && !prompt.isEmpty()) {
            System.out.print(prompt);
        }
    }

    /*
     * See https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
     */
    private void consumeTrailingNewline() {
        scanner.nextLine();
    }
}
