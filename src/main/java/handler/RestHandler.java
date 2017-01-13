package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.Constants;
import utils.ParamsUtilsService;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by elena on 1/12/17.
 * There is no need to cover this Class with tests
 * due to usage of external/JDK internal solutions which have been already tested
 */
public class RestHandler implements HttpHandler {

    private static Logger logger = Logger.getLogger(RestHandler.class.getName());

    /**
     * This method is needed to handle requests
     */
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        String operation = path.substring(path.indexOf(Constants.URL_SEPARATOR) + Constants.URL_SEPARATOR.length());

        //to evaluate a math expression given in string form JDK built-in Javascript engine is used
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(Constants.ENGINE_NAME);
        Map<String, Object> vars = new HashMap<>();
        String result = Constants.EMPTY_STRING;

        try {
            String parameters[] = ParamsUtilsService.splitParametersString(httpExchange.getRequestURI().getQuery());

            for (int i = 0; i <= parameters.length - 1; i++) {
                if (ParamsUtilsService.isEmpty(parameters[i]) || !parameters[i].contains(Constants.EQUALS)) {
                    throw new Exception();
                }
                String[] pair = parameters[i].split(Constants.EQUALS);
                vars.put(pair[0], ParamsUtilsService.getParamValue(pair[1]));
            }
            result = String.valueOf(engine.eval(operation, new SimpleBindings(vars)));
        } catch (Exception e) {
            result = Constants.BAD_REQUEST_ERROR;
            logger.warning(e.getCause().getMessage());
        } finally {
            sendResponse(httpExchange, result.equals(Constants.NaN) ? "Obtained result is not a number." : result);
        }
    }


    /**
     * This method helps to send obtained result to client
     */
    private void sendResponse(HttpExchange httpExchange, String result) {
        if (result.contains(Constants.BAD_REQUEST_ERROR)) {
            prepareResponse(httpExchange, result, Constants.RESPONSE_CODE_ERROR);
        } else {
            prepareResponse(httpExchange, result, Constants.RESPONSE_CODE_OK);
        }
    }

    /**
     * This method helps to prepare response body
     */
    private void prepareResponse(HttpExchange httpExchange, String result, int code) {
        try {
            httpExchange.sendResponseHeaders(code, Constants.CONTENT_LENGTH);
            try (BufferedOutputStream out = new BufferedOutputStream(httpExchange.getResponseBody())) {
                out.write(result.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

}
