package sample;

import com.sun.jdi.event.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.lang.String;
import java.io.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // initialize all elements used in scene builder
    @FXML
    private Button exportbutton1;

    @FXML
    private Button gobackbtn;

    @FXML
    private Button searchbutton1;

    @FXML
    private Button modifybutton1;

    @FXML
    private Button submitbutton;

    @FXML
    private TableColumn<Entry, String> colEntID = new TableColumn<Entry, String>("nodeid");

    @FXML
    private TableColumn<Entry, Integer> colXCoord = new TableColumn<Entry, Integer>("xcoord");

    @FXML
    private TableColumn<Entry, Integer> colYCoord = new TableColumn<Entry, Integer>("ycoord");

    @FXML
    private TableColumn<Entry, String> colFloor = new TableColumn<Entry, String>("floor");

    @FXML
    private TableColumn<Entry, String> colBuilding = new TableColumn<Entry, String>("building");

    @FXML
    private TableColumn<Entry, String> colNodeType = new TableColumn<Entry, String>("nodetype");

    @FXML
    private TableColumn<Entry, String> colLongName = new TableColumn<Entry, String>("longname");

    @FXML
    private TableColumn<Entry, String> colShortName = new TableColumn<Entry, String>("shortname");

    @FXML
    private TableView entryTable = new TableView();

    @FXML
    private Button csvExport;

    @FXML
    private TextField txtnodeid = new TextField();

    @FXML
    private TextField txtxcoord = new TextField();

    @FXML
    private TextField txtycoord = new TextField();

    @FXML
    private TextField txtfloor = new TextField();

    @FXML
    private TextField txtbuilding = new TextField();

    @FXML
    private TextField txtnodetype = new TextField();

    @FXML
    private TextField txtlongname = new TextField();

    @FXML
    private TextField txtshortname = new TextField();

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = null;

        //Set up export button on the home screen
        if (event.getSource() == exportbutton1) {
            // get reference to the button
            stage = (Stage) exportbutton1.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("ExportScreen.fxml"));
        } else if (event.getSource() == gobackbtn) {
            // get reference to the button
            stage = (Stage) gobackbtn.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        }

        //Set up modify button on the home screen
        else if (event.getSource() == modifybutton1) {
            // get reference to the button
            stage = (Stage) modifybutton1.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("ModifyScreen.fxml"));
        } else if (event.getSource() == searchbutton1) {
            // get reference to the button
            stage = (Stage) searchbutton1.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("SearchScreen_.fxml"));
        }
//        else if (event.getSource() == submitbutton) {
//            // get reference to the button
//            stage = (Stage) submitbutton.getScene().getWindow();
//
//            root = FXMLLoader.load(getClass().getResource("Modify2.fxml"));
//        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList<Entry> entList = getAllRecords();
            colEntID.setCellValueFactory(new PropertyValueFactory<>("nodeid"));
            colXCoord.setCellValueFactory(new PropertyValueFactory<>("xcoord"));
            colYCoord.setCellValueFactory(new PropertyValueFactory<>("ycoord"));
            colFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));
            colBuilding.setCellValueFactory(new PropertyValueFactory<>("building"));
            colNodeType.setCellValueFactory(new PropertyValueFactory<>("nodeType"));
            colLongName.setCellValueFactory(new PropertyValueFactory<>("longName"));
            colShortName.setCellValueFactory(new PropertyValueFactory<>("shortName"));
            entryTable.setItems(entList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        try{
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public static ObservableList<Entry> getAllRecords() throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM APP.SOFTENG_PARTC";
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby:myDB;create=true");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ObservableList<Entry> entryList = getEntryObjects(rs);
            return entryList;
        } catch (SQLException e) {
            System.out.println("Error while trying to fetch all records");
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Entry> getEntryObjects(ResultSet rs) throws SQLException {
        ObservableList<Entry> entList = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Entry ent = new Entry();
                ent.setNodeid(rs.getString("nodeid"));
                ent.setXcoord(rs.getInt("xcoord"));
                ent.setYcoord(rs.getInt("ycoord"));
                ent.setFloor(rs.getString("floor"));
                ent.setBuilding(rs.getString("building"));
                ent.setNodeType(rs.getString("nodetype"));
                ent.setLongName(rs.getString("longname"));
                ent.setShortName(rs.getString("shortname"));
                entList.add(ent);
            }
            return entList;
        } catch (SQLException e) {
            System.out.println("Error while trying to fetch all records");
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    private void exportToCsv(ActionEvent event) throws  SQLException,ClassNotFoundException{
        String filename = "Romminfo.csv";
        try {
            FileWriter fw = new FileWriter(filename);
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby:myDB;create=true");
            String query = "select * from SOFTENG_PARTC ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                fw.append(rs.getString(1));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append(',');
                fw.append(rs.getString(6));
                fw.append(',');
                fw.append(rs.getString(7));
                fw.append(',');
                fw.append(rs.getString(8));
                fw.append('\n');
                //System.out.println("It works");
            }
            fw.flush();
            fw.close();
            conn.close();
            System.out.println("CSV File is created successfully.");
        } catch (Exception ev) {
            ev.printStackTrace();
        }

    }

    @FXML
    public Entry getSingleEntry(String myID) throws SQLException, ClassNotFoundException {
        Entry myEnt = new Entry();
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conn = DriverManager.getConnection("jdbc:derby:myDB;create=true");
        PreparedStatement stmt = conn.prepareStatement("select * from SOFTENG_PARTC where nodeid = ?");
        stmt.setString(1, myID);
        ResultSet rs = stmt.executeQuery();
        myEnt.setNodeid(txtnodeid.getText());
        while (rs.next()) {
            myEnt.setXcoord(rs.getInt("xcoord"));
            myEnt.setYcoord(rs.getInt("ycoord"));
            myEnt.setFloor(rs.getString("floor"));
            myEnt.setBuilding(rs.getString("building"));
            myEnt.setFloor(rs.getString("floor"));
            myEnt.setNodeType(rs.getString("nodeType"));
            myEnt.setLongName(rs.getString("longName"));
            myEnt.setShortName(rs.getString("shortName"));
        }
        conn.close();
        return myEnt;
    }

    @FXML
    public void updateSQL(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {
            Stage stage = new Stage();
            Parent root = null;
            if (event.getSource() == modifybutton1) {
                // get reference to the button
                stage = (Stage) modifybutton1.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Modify2.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);

                Entry myEntry = getSingleEntry(txtnodeid.getText());
                txtxcoord.setText(Integer.toString(myEntry.getXcoord()));
                txtycoord.setText(Integer.toString(myEntry.getYcoord()));
                txtfloor.setText(myEntry.getFloor());
                txtbuilding.setText(myEntry.getBuilding());
                txtnodetype.setText(myEntry.getNodeType());
                txtlongname.setText(myEntry.getLongName());
                txtshortname.setText(myEntry.getShortName());
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void updateNback(ActionEvent event){
        try{
            Stage stage = new Stage();
            Parent root = null;
            if(event.getSource() == submitbutton){
                stage = (Stage) submitbutton.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            }
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby:myDB;create=true");
            PreparedStatement stmt = conn.prepareStatement("update SOFTENG_PARTC set xcoord = ?, ycoord = ?, floor = ?, building = ?, nodetype = ?, longname = ?, shortname = ? where nodeid = ?");
            stmt.setInt(1, Integer.parseInt(txtxcoord.getText()));
            stmt.setInt(2, Integer.parseInt(txtycoord.getText()));
            stmt.setString(3, txtfloor.getText());
            stmt.setString(4, txtbuilding.getText());
            stmt.setString(5, txtnodetype.getText());
            stmt.setString(6, txtlongname.getText());
            stmt.setString(7, txtshortname.getText());
            stmt.setString(8, txtnodeid.getText());
            stmt.execute();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
