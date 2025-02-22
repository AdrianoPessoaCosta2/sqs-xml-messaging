package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseCleanup {
    public static void clearDatabase() {
        Logger logger = LoggerFactory.getLogger(DatabaseCleanup.class);
        String url = "jdbc:postgresql://localhost:5432/postgres_db?currentSchema=main";
        String user = "postgres";
        String password = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            conn.setAutoCommit(false);

            stmt.execute("SET session_replication_role = 'replica';");

            stmt.execute("TRUNCATE TABLE " +
                    "invoice_hist," +
                    "customer_hist," +
                    "invoice, " +
                    "customer" +
                    " RESTART IDENTITY CASCADE;");

            conn.commit();

            stmt.execute("SET session_replication_role = 'origin';");
        } catch (Exception e) {
            logger.error("Erro ao apagar banco." + e.getMessage());
        }
    }
}
