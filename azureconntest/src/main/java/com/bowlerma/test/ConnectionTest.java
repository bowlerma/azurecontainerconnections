package com.bowlerma.test;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConnectionTest {


    public static void main(String[] args) throws SQLException {

        int numberOfConnections = Integer.valueOf(args[2]);
        String user = args[0];
        String password = args[1];

        String connectionURL = "jdbc:sqlserver://pfeudevintsql.database.windows.net:1433;databaseName=kitkat-mbr1;" +
                "loginTimeout=60;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows" +
                ".net;userName=" + user + ";password=" + password;

        String sql = "select count(*) from trentadm.schema_version";

        List<Connection> connections = new ArrayList<>();


        try {
            for (int i = 1; i <= numberOfConnections; i++) {
                Connection connection = DriverManager.getConnection(connectionURL);
                connections.add(connection);
                Statement statement = null;
                ResultSet results = null;
                try {
                    statement = connection.createStatement();
                    results = statement.executeQuery(sql);
                    results.next();
                    System.out.println(i + ":" + results.getInt(1));
                } finally {
                    if (Objects.nonNull(results)) {
                        results.close();
                    }
                    if (Objects.nonNull(statement)) {
                        statement.close();
                    }
                }

                try {
                    Thread.sleep(1000);
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
