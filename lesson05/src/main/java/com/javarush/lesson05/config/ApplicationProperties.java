package com.javarush.lesson05.config;

import lombok.SneakyThrows;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

import static org.hibernate.cfg.JdbcSettings.JAKARTA_JDBC_DRIVER;


@ToString
public class ApplicationProperties extends Properties {

    @SneakyThrows
    public ApplicationProperties(@Value("application.properties") String fileProperties) {
        //String fileProperties = "application.properties";
        this.load(new FileReader(CLASSES_ROOT + "/" + fileProperties));
        try {
            String driver = this.getProperty(JAKARTA_JDBC_DRIVER);
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //any runtime
    public final static Path CLASSES_ROOT = Paths.get(URI.create(
            Objects.requireNonNull(
                    ApplicationProperties.class.getResource("/")
            ).toString()));


}
