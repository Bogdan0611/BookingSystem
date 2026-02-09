package server;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.util.logging.Logger;

@QuarkusMain
public class ServerApplication implements QuarkusApplication {

    private static final Logger LOG = Logger.getLogger(ServerApplication.class.getName());

    public static void main(String[] args) {
        System.out.println("Starting Booking Server...");
        Quarkus.run(ServerApplication.class, args);
    }

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Server is running on ports 8080 (http) and 9090 (socket)");
        System.out.println("Press Ctrl+C to stop.");
        
        Quarkus.waitForExit();
        return 0;
    }
}
