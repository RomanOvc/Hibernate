import java.sql.*;
import java.util.List;

public class DBConn {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    Connection conn;

    private DBConn(Connection conn) {
        this.conn = conn;
    }

    public static DBConn OpenConnection(String host, String userName, String password) throws SQLException, IllegalArgumentException, ClassNotFoundException {
        if (host != null && host.isEmpty() && userName != null && userName.isEmpty()) {
            throw new IllegalArgumentException("Host and username  cannot be empty");
        }
        String jdbc_url;
        if (password != null && !password.isEmpty()) {
            jdbc_url = "jdbc:mysql://" + userName + ":" + password + "@" + host;
        } else {
            jdbc_url = "jdbc:mysql://" + userName + "@" + host;
        }

        Connection conn = null;

        //Register JDBC driver
        Class.forName(JDBC_DRIVER);

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(jdbc_url);
        DBConn newConn = new DBConn(conn);
        return newConn;
    }

    public void setupPhotoFrameDB() throws Exception {
        // class Statement need for SQL query
        Statement stmt = null;
        //Execute a query
        System.out.println("Creating database...");
        stmt = conn.createStatement();

        String create_db_sql = "CREATE DATABASE PhotoFrame_Hiber";
        stmt.executeUpdate(create_db_sql);

        //photoframe
        String create_tPhotoframes_sql = "CREATE TABLE photoframe_hiber.photoframe(" +
                "  id int(11) NOT NULL AUTO_INCREMENT," +
                " name varchar(255) NOT NULL," +
                " price varchar(255) NOT NULL," +
                " color varchar(255) NOT NULL," +
                " type varchar(255) NOT NULL," +
                " PRIMARY KEY (`id`) )ENGINE = InnoDB AUTO_INCREMENT = 164 DEFAULT CHARSET = utf8";
        stmt.executeUpdate(create_tPhotoframes_sql);


        String create_tDigitalfrmaes_sql = "CREATE TABLE photoframe_hiber.digitalframe ("
                + " id int(11) NOT NULL AUTO_INCREMENT,"
                + "memory varchar(255) NOT NULL,"
                + "viewing_angle VARCHAR(255) not null,"
                + "size VARCHAR(255) not null,"
                + "photoframe_id int(11) DEFAULT NULL,"
                + "PRIMARY KEY (id),\n" +
                "        KEY FK_28498m2ap6ybm5xn8tce6iwme (photoframe_id),\n" +
                "        CONSTRAINT FK_28498m2ap6ybm5xn8tce6iwme FOREIGN KEY (photoframe_id) REFERENCES photoframe (id)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;";
        stmt.executeUpdate(create_tDigitalfrmaes_sql);

        String create_tPlainfrmaes_sql = "CREATE TABLE photoframe_hiber.plainframe("
                + "id int(11) NOT NULL AUTO_INCREMENT,"
                + "material VARCHAR(255) not null,"
                + "width VARCHAR(255) not null,"
                + "material_insert VARCHAR(255) not null,"
                + " photoframe_id int(11) NOT NULL,"
                + "        PRIMARY KEY (id),"
                + "KEY FK_28498m2ap6ybm5xn8tce6iwzh (photoframe_id),"
                + "        CONSTRAINT FK_28498m2ap6ybm5xn8tce6iwzh FOREIGN KEY (photoframe_id) REFERENCES photoframe (id)"
                + ") ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8";
        stmt.executeUpdate(create_tPlainfrmaes_sql);


    }

    public void InsertPhotoFrame(List<String[]> mass) throws Exception {

        String sql_photoframe = "INSERT INTO photoframe_hiber.photoframe(id,name,price,color,type) VALUES(?,?,?,?,?)";
        String sql_digitalframe = "INSERT INTO photoframe_hiber.digitalframe(memory,viewing_angle,size,photoframe_id) VALUES(?,?,?,?)";
        String sql_plainframe = "INSERT INTO photoframe_hiber.plainframe(material,width,material_insert,photoframe_id) VALUES(?,?,?,?)";


        for (String[] row : mass) {
            for (String a : row) {
                String[] k = a.split(";");
                PreparedStatement stmt1 = conn.prepareStatement(sql_photoframe);
                stmt1.setString(1, k[0]);
                stmt1.setString(2, k[1]);
                stmt1.setString(3, k[2]);
                stmt1.setString(4, k[3]);
                stmt1.setString(5, k[4]);
                stmt1.executeUpdate();


                if (k[4].equals("digital")) {
                    PreparedStatement stmt2 = conn.prepareStatement(sql_digitalframe);
//                    stmt2.setString(1, k[0]);
                    stmt2.setString(1, k[5]);
                    stmt2.setString(2, k[6]);
                    stmt2.setString(3, k[7]);
                    stmt2.setString(4, k[0]);

                    stmt2.executeUpdate();

                } else if (k[4].equals("plain")) {
                    PreparedStatement stmt3 = conn.prepareStatement(sql_plainframe);
//                    stmt3.setString(1, k[0]);
                    stmt3.setString(1, k[5]);
                    stmt3.setString(2, k[6]);
                    stmt3.setString(3, k[7]);
                    stmt3.setString(4, k[0]);
                    stmt3.executeUpdate();
                }
            }
        }
    }

    public void DropShema() throws SQLException {
        Statement stmt = null;
        //Execute a query

        stmt = conn.createStatement();

        String create_db_sql = "drop schema PhotoFrame_Hiber";
        stmt.executeUpdate(create_db_sql);
        System.out.println("База удалена");

    }

    public void showDB() throws SQLException {
        String sql = "show databases like 'photoframe_hiber'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            if (rs.getString("Database").equals("photoframe_hiber")) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }
}
