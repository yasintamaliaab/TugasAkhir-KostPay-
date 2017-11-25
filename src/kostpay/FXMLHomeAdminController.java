/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kostpay;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLHomeAdminController implements Initializable {

    @FXML
    private JFXTextField code;
    @FXML
    private JFXButton btadd;
    public String codeAd ;
    
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    @FXML
    private TableColumn<?, ?> id_user;
    @FXML
    private TableColumn<?, ?> nama_user;
    @FXML
    private TableColumn<?, ?> username;
    @FXML
    private TableColumn<?, ?> password;
    @FXML
    private TableColumn<?, ?> bulan;
    @FXML
    private TableColumn<?, ?> code_;
    @FXML
    private TableColumn<?, ?> bayar;
    @FXML
    private TableColumn<?, ?> ket;
    @FXML
    private TableView<Data_table> tabeladmin;
    ObservableList<Data_table> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Koneksi.kon.Konek();
        setdatatable();
        ambildatabase();
    }    

    public void setdatatable(){
        nama_user.setCellValueFactory(new PropertyValueFactory<>("nm_user"));
        bulan.setCellValueFactory(new PropertyValueFactory<>("bln"));
        code_.setCellValueFactory(new PropertyValueFactory<>("code1"));
        bayar.setCellValueFactory(new PropertyValueFactory<>("bayar1"));
    }
    
    public void ambildatabase(){
        String sql = "SELECT * FROM `pembayaran`";
        
            try {
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while(rs.next()){
                    data.add(new kostpay.Data_table(rs.getString(1), rs.getString(2), rs.getString(4),
                            rs.getString(5)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLHomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tabeladmin.setItems(data);}
    
    @FXML
    private void add(ActionEvent event) {
        conn = Koneksi.kon.Konek();
        codeAd = code.getText();
        String sql =" INSERT INTO `admin_access`( `codeA`) VALUES (?)";
        try{
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, codeAd);
        int i = stmt.executeUpdate();
        if (i == 1){
            JOptionPane.showMessageDialog(null,"Berhasil ditambahkan");
            try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FXMLHomeAdmin.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            System.out.println("Failed to create new Window." + e);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Gagal menambahkan");
        }
        stmt.close();
        conn.close();
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
