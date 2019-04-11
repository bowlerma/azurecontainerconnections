package com.bowlerma.test;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConnectionTest {


    public static void main(String[] args) throws SQLException {

        String url = args[0];
        String database = args[1];
        String user = args[2];
        String password = args[3];
        int numberOfConnections = Integer.valueOf(args[4]);
        int sleepTime = Integer.valueOf(args[5]);

        String connectionURL = "jdbc:sqlserver://" + url + ":1433;databaseName=" + database + ";" +
                "loginTimeout=60;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows" +
                ".net;userName=" + user + ";password=" + password;

        String sql = "select count(*) from trentadm.schema_version";

        List<Connection> connections = new ArrayList<>();


        try {
            for (int i = 1; i <= numberOfConnections; i++) {
                Instant connStart = Instant.now();
                Connection connection = DriverManager.getConnection(connectionURL);
                Instant connEnd = Instant.now();
                connections.add(connection);
                Statement statement = null;
                ResultSet results = null;
                try {
                    statement = connection.createStatement();
                    Instant queryStart = Instant.now();
                    results = statement.executeQuery(sql);
                    Instant queryEnd = Instant.now();
                    results.next();
                    System.out.println(i + ":" + results.getInt(1) + ":"
                            + Duration.between(connStart, connEnd).toMillis() + ":"
                            + Duration.between(queryStart, queryEnd).toMillis());
                } finally {
                    if (Objects.nonNull(results)) {
                        results.close();
                    }
                    if (Objects.nonNull(statement)) {
                        statement.close();
                    }
                }

                try {
                    Thread.sleep(sleepTime );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            for (Connection c : connections) {
                c.close();
            }
        }
    }
}
