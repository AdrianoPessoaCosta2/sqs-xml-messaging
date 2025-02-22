package util;

import exception.DatabaseConnectionException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUnitConfig {
    public static IDatabaseConnection getDatabaseConnection() throws DatabaseConnectionException {
        String url = "jdbc:postgresql://localhost:5432/postgres_db?currentSchema=main";
        String user = "postgres";
        String password = "postgres";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            IDatabaseConnection dbUnitConnection = new DatabaseConnection(connection);

            DatabaseConfig config = dbUnitConnection.getConfig();
            IDataTypeFactory dataTypeFactory = new PostgresqlDataTypeFactory();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, dataTypeFactory);

            return dbUnitConnection;
        } catch (SQLException | DatabaseUnitException e) {
            throw new DatabaseConnectionException("Erro ao conectar ao banco de dados", e);
        }
    }
}
