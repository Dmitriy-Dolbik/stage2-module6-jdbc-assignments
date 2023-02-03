package jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomDataSource implements DataSource {
    private static volatile CustomDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    private CustomDataSource(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.name = name;
        this.password = password;
    }

    public static CustomDataSource getInstance() {
        if (instance == null) {
            instance = new CustomDataSource("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/myfirstdb", "password", "user" );
        }
        return instance;
    }
    @Override
    public Connection getConnection() throws SQLException
    {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new CustomConnector().getConnection(url, name, password);
    }

    @Override
    public Connection getConnection(final String username, final String password) throws SQLException
    {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException
    {
        return null;
    }

    @Override
    public void setLogWriter(final PrintWriter out) throws SQLException
    {

    }

    @Override
    public void setLoginTimeout(final int seconds) throws SQLException
    {

    }

    @Override
    public int getLoginTimeout() throws SQLException
    {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException
    {
        return null;
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException
    {
        return null;
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException
    {
        return false;
    }
}
