package utils;

/**
 * Created by elena on 1/13/17.
 */
public class Constants {

    public static final int NUMBER_OF_THREADS = 10;
    public static final int PORT_NUMBER = 8081;

    public static final int RESPONSE_CODE_OK = 200;
    public static final int RESPONSE_CODE_ERROR = 400;
    public static final int CONTENT_LENGTH = 0;


    public static final String ENGINE_NAME = "JavaScript";

    public static final String BAD_REQUEST_ERROR =
            "Server responded with Error 400 (Bad Request). " +
                    "Failed to perform calculation. " +
                    "Please, use the following operation pattern: operation?x1=value&x2=value. Example: x1+x2?x1=2&x2=3.";

    public static final String URL_SEPARATOR = "/";
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String EMPTY_STRING = "";
    public static final String EQUALS = "=";
    public static final String AMPERSAND = "&";
    public static final String NOT_NUMBER = "[^\\d.]";
    public static final String NaN = "NaN";

    //assertEquals(double expected, double actual) has been deprecated.
    //new assertEquals(double expected, double actual, delta) is recommended for usage.
    public static final Double DELTA_PRECISION_LOSS = 0.01;

    public static final String PARAM_TO_TEST = "x1=100";
    public static final String PARAM_TO_TEST_2 = "x1=100";
    public static final String WARNING = "Wrong number of parameters obtained.";

}
