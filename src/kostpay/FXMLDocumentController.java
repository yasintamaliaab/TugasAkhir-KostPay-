package kostpay;

import java.sql.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
    public String nama1, asal1, gender1, usernameL, passwordL;
   @FXML
    private JFXTextField InUser;
    @FXML
    private JFXPasswordField InPass;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btdaftar;
    public String user="admin";
    public String pass="admin";
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Koneksi.kon.Konek();}
    @FXML
    private void handleButtonAction(ActionEvent event) {
        usernameL = InUser.getText();
        passwordL =InPass.getText();
        if(InUser.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Harap inputkan username Anda");}
        else if (InPass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Harap masukkan password Anda");}
             String sql = "Select * from user where username = ? && password = ?";
            try{
               stmt = conn.prepareStatement(sql);
               stmt.setString(1, usernameL);
               stmt.setString(2, passwordL);
               rs = stmt.executeQuery();
               if(rs.next()){
                   if(usernameL.equalsIgnoreCase(user) && passwordL.equalsIgnoreCase(pass)){
                       try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FXMLHomeAdmin.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();} catch (IOException e) {
            System.out.println("Failed to create new Window." + e);}
                   }else{
                   try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FXMLHome.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();} 
                   catch (IOException e) {
            System.out.println("Failed to create new Window." + e);}
                   }
               }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);}}

    @FXML
    private void daftar(ActionEvent event) {
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FXMLDaftar.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();} catch (IOException e) {
            System.out.println("Failed to create new Window." + e);}}}
