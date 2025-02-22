package util;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.yaml.YamlDataSet;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.InputStream;

public class DataSetAfterExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getTestMethod().isPresent() && context.getTestMethod().get().isAnnotationPresent(DataSetAfter.class)) {

            String methodName = context.getTestMethod().get().getName();

            String yamlFilePath = "dataset/" + methodName + ".yml";

            loadAndValidateYamlDataSet(loadYamlDataSet(yamlFilePath));

        }
    }

    private IDataSet loadYamlDataSet(String yamlFilePath) throws Exception {
        try (InputStream yamlStream = getClass().getClassLoader().getResourceAsStream(yamlFilePath)) {
            if (yamlStream == null) {
                throw new IllegalArgumentException("Arquivo YAML não encontrado: " + yamlFilePath);
            }

            return new YamlDataSet(yamlStream);
        }
    }

    public static void loadAndValidateYamlDataSet(IDataSet dataSet) throws Exception {
        IDatabaseConnection dbUnitConnection = DbUnitConfig.getDatabaseConnection();

        for (String tableName : dataSet.getTableNames()) {
            ITable expectedTable = dataSet.getTable(tableName);
            ITable actualTable = dbUnitConnection.createTable(tableName);

            assertTablesAreEqual(expectedTable, actualTable);
        }
    }

    private static void assertTablesAreEqual(ITable expectedTable, ITable actualTable) throws Exception {
        int expectedRowCount = expectedTable.getRowCount();
        int actualRowCount = actualTable.getRowCount();

        if (expectedRowCount != actualRowCount) {
            throw new AssertionError("Número de linhas diferentes: esperado " + expectedRowCount + ", mas encontrado " + actualRowCount);
        }

        for (int i = 0; i < expectedRowCount; i++) {
            for (int j = 0; j < expectedTable.getTableMetaData().getColumns().length; j++) {
                String columnName = expectedTable.getTableMetaData().getColumns()[j].getColumnName();
                String expectedValue = expectedTable.getValue(i, columnName).toString();
                String actualValue = actualTable.getValue(i, columnName).toString();

                if (!expectedValue.equals(actualValue)) {
                    throw new AssertionError("Valor diferente na coluna '" + columnName + "' na linha " + i);
                }
            }
        }
    }
}