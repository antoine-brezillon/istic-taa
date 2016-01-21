package fr.istic.taa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

/**
 * Created by Antoine Brezillon on 21/01/16.
 */
@SpringBootApplication
public class ApplicationSpring {

    public static void main( String[] args ) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ApplicationSpring.class);
        app.run(args);
    }
}
