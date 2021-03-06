package de.yellow.medienverwaltung.database.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Factory-Klasse zum erzeugen einer {@link DataSource}. Mit der {@link DataSource} werden {@link JdbcTemplate}s erzeugt.
 * 
 * @author krispin
 * 
 */
public class ConnectionFactory {

    private String host;
    private String db;
    private String port;
    private String user;
    private String pw;

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);

    public DataSource getDataSource() {

        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        try {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Konnte Properties nicht laden");
        }

        host = prop.getProperty("host");
        db = prop.getProperty("db");
        port = prop.getProperty("port");
        user = prop.getProperty("user");
        pw = prop.getProperty("pw");

        LOG.trace("Aus den Parametern ein DataSource für {} auf {} erstellen", db, host);

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, pw);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

}
