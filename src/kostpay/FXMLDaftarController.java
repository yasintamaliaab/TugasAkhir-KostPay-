package kostpay;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLDaftarController implements Initializable {

    @FXML
    private JFXTextField nama;
    @FXML
    private JFXTextField asal;
    @FXML
    private JFXTextField InUserS;
    @FXML
    private JFXPasswordField InPassS;
    @FXML
    private RadioButton rdlaki;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rdperempuan;

    @FXML
    private JFXButton btdaftar;
    
    public String namaS, asalS, genderS, usernameS, passwordS;
    
    private Connection conn = null;
    private PreparedStatement stmt = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Koneksi.kon.Konek();
    }
    @FXML
    private void daftar(ActionEvent event) {
        conn = Koneksi.kon.Konek();
        usernameS = InUserS.getText();
        passwordS = InPassS.getText();
        namaS = nama.getText();
        asalS = asal.getText();
        if(rdlaki.isSelected()){
        genderS = "L";
        }else if(rdperempuan.isSelected()){
        genderS="P";
        }
        if(usernameS.equals("")){
            JOptionPane.showMessageDialog(null, "Harap inputkan username Anda");}
        else if (passwordS.equals("")){
            JOptionPane.showMessageDialog(null, "Harap masukkan password Anda");
        }
        else if (namaS.equals("")){
            JOptionPane.showMessageDialog(null, "Harap masukkan nama Anda");
        }
        else if (asalS.equals("")){
            JOptionPane.showMessageDialog(null, "Harap masukkan asal Anda");
        }String sql =" INSERT INTO `user`( `nama_user`, `username`, `asal`, `gender` ,`password`) VALUES (?,?,?,?,?)";
        try{
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, namaS);
        stmt.setString(2, usernameS);
        stmt.setString(3, asalS);
        stmt.setString(4, genderS);
        stmt.setString(5, passwordS);
        int i = stmt.executeUpdate();
        if (i == 1){
            JOptionPane.showMessageDialog(null,"Daftar Berhasil");
            try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FXMLLogin.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();} catch (IOException e) {
            System.out.println("Failed to create new Window." + e);}
        }else{
            JOptionPane.showMessageDialog(null, "Maaf anda gagal mendaftar");
        }
        stmt.close();
        conn.close();
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException se){
                se.printStackTrace();}}}}
