package sample;

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
import javafx.stage.Stage;
import javafx.scene.Parent;

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
    private Button showAllBtn;

    @FXML
    private TableColumn<Entry, String> colEntID;

    @FXML
    private TableColumn<Entry, Integer> colXCoord;

    @FXML
    private TableColumn<Entry, Integer> colYCoord;

    @FXML
    private TableColumn<Entry, String> colFloor;

    @FXML
    private TableColumn<Entry, String> colBuilding;

    @FXML
    private TableColumn<Entry, String> colNodeType;

    @FXML
    private TableColumn<Entry, String> colLongName;

    @FXML
    private TableColumn<Entry, String> colShortName;

    @FXML
    private TableView entryTable;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = null;

        //Set up export button on the home screen
        if (event.getSource() == exportbutton1) {
            // get reference to the button
            stage = (Stage) exportbutton1.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("ExportScreen.fxml"));
        }


        else if  (event.getSource() == gobackbtn) {
            // get reference to the button
            stage = (Stage) gobackbtn.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        }

        //Set up modify button on the home screen
        else if  (event.getSource() == modifybutton1) {
            // get reference to the button
            stage = (Stage) modifybutton1.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("ModifyScreen.fxml"));
        }


        else if  (event.getSource() == searchbutton1) {
            // get reference to the button
            stage = (Stage) searchbutton1.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("SearchScreen_.fxml"));
        }


        else if  (event.getSource() == submitbutton) {
            // get reference to the button
            stage = (Stage) submitbutton.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("Modify2.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colEntID.setCellValueFactory(cellData -> cellData.getValue().nodeidProperty());
        colXCoord.setCellValueFactory(cellData -> cellData.getValue().getNodeid());
    }

    public static ObservableList<Entry> getAllRecords() throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM SOFTENG_PARTC";
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby:myDB;create=true");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ObservableList<Entry> entryList = getEntryObjects(rs);
            return entryList;
        }
        catch(SQLException e){
            System.out.println("Error while trying to fetch all records");
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Entry> getEntryObjects(ResultSet rs) throws ClassNotFoundException, SQLException{
        ObservableList<Entry> entList = FXCollections.observableArrayList();
        try{
            while(rs.next()){
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
        }
        catch(SQLException e){
            System.out.println("Error while trying to fetch all records");
            e.printStackTrace();
            throw e;
        }
    }

}
