import com.sun.net.httpserver.HttpServer;
import handler.RestHandler;
import utils.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by elena on 1/12/17.
 * Ignore WARNING: ReferenceError: "favicon" is not defined
 */
public class Application {

    private static Logger logger = Logger.getLogger(Application.class.getName());

    public static final void main(String[] args) {
        HttpServer server;

        try {
            server = HttpServer.create(new InetSocketAddress(Constants.PORT_NUMBER), 0);
        } catch (IOException e) {
            logger.warning(e.getMessage());
            return;
        }

        server.createContext(Constants.URL_SEPARATOR, new RestHandler());
        server.setExecutor(Executors.newFixedThreadPool(Constants.NUMBER_OF_THREADS));
        logger.info("Started listening on port: " + Constants.PORT_NUMBER);
        server.start();
    }
}
