package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void clearDB() {
        var cleanCreditRequest = "DELETE FROM credit_request_entity;";
        var cleanOrder = "DELETE FROM order_entity;";
        var cleanPayment = "DELETE FROM payment_entity;";
        try (var connection = getConn()) {
            QUERY_RUNNER.execute(connection, cleanCreditRequest);
            QUERY_RUNNER.execute(connection, cleanOrder);
            QUERY_RUNNER.execute(connection, cleanPayment);
        } catch (SQLException e) {
            System.out.println("SQL exception in clearDB: " + e.getMessage());
        }
    }

    public static String getPaymentStatus() {
        var codesSQL = "SELECT status FROM payment_entity;";
        return getData(codesSQL);
    }

    public static String getCreditRequestStatus() {
        var codesSQL = "SELECT status FROM credit_request_entity;";
        return getData(codesSQL);
    }

    private static String getData(String query) {
        String data = "";
        try (var conn = getConn()) {
            data = QUERY_RUNNER.query(conn, query, new ScalarHandler<>());
        } catch (SQLException e) {
            System.out.println("SQL exception in getData: " + e.getMessage());
        }
        return data;
    }
}
