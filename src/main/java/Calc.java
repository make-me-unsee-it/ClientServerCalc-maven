import exception.UserInputException;

import java.util.regex.Pattern;

public class Calc {

    String run(String input) throws UserInputException {
        String[] elements = elementSelection(input);
        if (elements.length != 3) throw new UserInputException("условия для вычисления введены некорректно");

        int firstElement = Integer.parseInt(elements[0]);
        int secondElement = Integer.parseInt(elements[2]);

        if (elements[1].charAt(0) == '+') return Integer.toString((firstElement + secondElement));
        else if (elements[1].charAt(0) == '-') return Integer.toString((firstElement - secondElement));
        else if (elements[1].charAt(0) == '*') return Integer.toString((firstElement * secondElement));
        else if (elements[1].charAt(0) == '/') return Double.toString((double) firstElement / secondElement);
        else throw new UserInputException("условия для вычисления введены некорректно");
    }

    private String[] elementSelection(String input) {
        Pattern p = Pattern.compile("\s");
        return p.split(input);
    }
}
