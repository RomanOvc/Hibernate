import models.*;
import services.*;
import utils.HibernateUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final String USER = "root";
    static final String PASS = "";
    static final String HOST = "localhost:3307";
    private static String File = "test1.csv";
    private static String File1 = "photo.csv";

    public static void main(String[] args) throws Exception {
        DBConn conn = null;
        try {
            conn = DBConn.OpenConnection(HOST, USER, "");
            conn.setupPhotoFrameDB();
            logic_hibernate.addField(File);

            logic_hibernate.main();
            conn.DropShema();
            // HibernateUtil.shutdown();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
