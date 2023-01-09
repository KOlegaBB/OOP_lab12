package task_2;

import java.sql.*;

public class CashedDocument implements Document {
    private final Document document;

    public CashedDocument(Document document) {
        this.document = document;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:documents.db";
            Connection conn = DriverManager.getConnection(dbURL);
            try {
                String sql = "create table documents (path text, message text)";
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
            } catch (Exception ex) {
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getGcsPath() {
        return document.getGcsPath();
    }

    @Override
    public String parse() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:documents.db";
            Connection conn = DriverManager.getConnection(dbURL);
            PreparedStatement statement1 = conn.prepareStatement("select message from documents where path = ?");
            statement1.setString(1, document.getGcsPath());
            ResultSet result = statement1.executeQuery();
            if (result.next()) {
                return result.getString(1);
            } else {
                String message = document.parse();
                PreparedStatement statement2 = conn.prepareStatement("insert into documents values(?, ?);");
                statement2.setString(1, document.getGcsPath());
                statement2.setString(2, message);
                statement2.addBatch();
                conn.setAutoCommit(false);
                statement2.executeBatch();
                conn.setAutoCommit(true);
                return message;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
