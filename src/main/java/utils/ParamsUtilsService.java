package utils;

/**
 * Created by elena on 1/12/17.
 */
public class ParamsUtilsService {

    /**
     * This method helps to split parameters from URI
     */
    public static String[] splitParametersString(String parametersString) {
        if (isEmpty(parametersString)) {
            return new String[0];
        }
        return parametersString.split(Constants.AMPERSAND);
    }

    /**
     * This method helps to get a value from parameterAndValue String
     */
    public static float getParamValue(String parameter) {
        String value = replaceAllNonNumericCharacters(parameter);
        if (!isEmpty(value)) {
            return Float.parseFloat(value);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * This method replaces all letters and special characters and leaves only numbers and dots
     */
    private static String replaceAllNonNumericCharacters(String value) {
        if(isEmpty(value)){
            return Constants.EMPTY_STRING;
        }

        String newValue = replaceAllCommas(value);
        return newValue.replaceAll(Constants.NOT_NUMBER, Constants.EMPTY_STRING);
    }

    /**
     * This method replaces all commas to dots
     */
    private static String replaceAllCommas(String value) {
        return value.replaceAll(Constants.COMMA, Constants.DOT);
    }
}
