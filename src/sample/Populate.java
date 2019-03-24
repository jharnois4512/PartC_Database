package sample;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.String;

public class Populate{
    public static void populate(String[] args) {
        System.out.println("-------Embedded Java DB Connection Testing --------");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Java DB Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println("Select JARs or directories. Go to the folder where the Java JDK is installed");
            System.out.println("Select the folder java/jdk1.8.xxx/db/lib where xxx is the version.");
            System.out.println("Click OK, compile the code and run it.");
            e.printStackTrace();
            return;
        }

        System.out.println("Java DB driver registered!");


        //  try {
        // substitute your database name for myDB
        //Connection conn = DriverManager.getConnection("jdbc:derby:myDB;create=true");
        File file = new File("/Users/JeffHarnois/Downloads/PrototypeNodes.csv");

        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try {
            inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.nextLine();
                String[] values = line.split(",");
                lines.add(Arrays.asList(values));
            }
            inputStream.close();
        } catch (Exception q) {
            q.printStackTrace();
        }

        try {
            int lineNum = 1;
            for (List<String> line : lines) {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby:myDB;create=true");
                String query = "insert into SoftEng_PartC (nodeid, xcoord, ycoord, floor, building, nodetype, longname, shortname) values (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preStmt = conn.prepareStatement(query);
                for (int columnNum = 0; columnNum < 8; columnNum++) {
                    if (lineNum != 1) {
                        if (columnNum == 1 || columnNum == 2 || columnNum == 3) {
                            preStmt.setInt(columnNum + 1, Integer.parseInt(line.get(columnNum)));
                        }
                        else if (columnNum == 0 || columnNum == 4 || columnNum == 5 || columnNum == 6) {
                            preStmt.setString(columnNum + 1, line.get(columnNum));
                        }
                        else if (columnNum == 7) {
                            preStmt.setString(columnNum + 1, line.get(columnNum));
                            preStmt.executeUpdate();
                            conn.close();
                        }
                    }
                    else {
                        conn.close();
                    }
                }
                lineNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //conn.close();

      /* } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return;
        }*/
        System.out.println("Java DB connection established!");
    }
}

/*
CREATE TABLE SOFTENG_PARTC
(
    NODEID varchar(20) PRIMARY KEY NOT NULL,
    XCOORD int,
    YCOORD int,
    FLOOR int,
    BUILDING varchar(15),
    NODETYPE varchar(4),
    LONGNAME varchar(40),
    SHORTNAME varchar(20)
);
CREATE UNIQUE INDEX SQL190323122638210 ON SOFTENG_PARTC (NODEID);
 */


