package fr.istic.taa;


import com.google.common.base.Joiner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.UnknownHostException;

/**
 * Created by Antoine Brezillon on 21/01/16.
 */
@SpringBootApplication
@EnableSwagger2
public class ApplicationSpring {

    public static void main( String[] args ) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ApplicationSpring.class);
        addLiquibaseScanPackages();
        app.run(args);
    }

    /**
     * Set the liquibases.scan.packages to avoid an exception from ServiceLocator.
     */
    private static void addLiquibaseScanPackages() {
        System.setProperty("liquibase.scan.packages", Joiner.on(",").join(
                "liquibase.change", "liquibase.database", "liquibase.parser",
                "liquibase.precondition", "liquibase.datatype",
                "liquibase.serializer", "liquibase.sqlgenerator", "liquibase.executor",
                "liquibase.snapshot", "liquibase.logging", "liquibase.diff",
                "liquibase.structure", "liquibase.structurecompare", "liquibase.lockservice",
                "liquibase.ext", "liquibase.changelog"));
    }
}
