package top.caodong0225.usst_noteboard.manager;

import lombok.Getter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author jyzxc
 * @since 2024-11-30
 */
public class DatabaseConnectionManager {
    @Getter
    private static DataSource dataSource;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/MySQL");
        } catch (ClassNotFoundException e) {
            Logger.getLogger("RegisterServlet").severe("Cannot load MySQL Driver: " + e.getMessage());
        } catch (NamingException e) {
            Logger.getLogger("RegisterServlet").severe("Cannot find DataSource: " + e.getMessage());
        }
    }

    public static void setDataSource(DataSource dataSource) {
        DatabaseConnectionManager.dataSource = dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
