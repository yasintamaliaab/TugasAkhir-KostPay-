package kostpay;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLHomeController implements Initializable {

    @FXML
    private JFXTextField nama;
    @FXML
    private JFXTextField bln;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXButton btverifikasi;
    @FXML
    private JFXTextField bayar;
    @FXML
    private JFXDatePicker tgl;
    public String namaH, bulanH, tglH, codeH, bayarH;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Koneksi.kon.Konek();
    }    

    @FXML
    private void verifikasi(ActionEvent event) throws SQLException {
        conn = Koneksi.kon.Konek();
        namaH = nama.getText();
        bulanH = bln.getText();
        tglH= tgl.getValue().toString();
        codeH = code.getText();
        bayarH = bayar.getText();
    String sql =" INSERT INTO `user`( `nama`, `bulan`, `tgl_pembayaran`, `code` ,`bayar`) VALUES (?,?,?,?,?)";
        
        try {
            String sql2 = "SELECT * FROM admin_access WHERE codeA='"+codeH+"'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql2);
            int a = 0;
            while(rs.next()){
                a++;
            }
            
            if(a > 0) {
                //berhasil
                JOptionPane.showMessageDialog(null,"Berhasil verifikasi");
                try {
                    Statement stm1 = conn.createStatement();
                
    stm1.executeUpdate("INSERT INTO `pembayaran` (`nama`, `bulan`, `tgl_pembayaran`, `code`, `bayar`) VALUES "
            + "('"+namaH+"', '"+bulanH+"', '"+tglH+"', '"+codeH+"', '"+bayarH+"');");
                try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FXMLverifikasi.fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load());
            
            FXMLverifikasiController kirim= new FXMLverifikasiController();
            kirim = fxmlLoader.getController();
            kirim.dataterima(namaH, bulanH, tglH, codeH, bayarH);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();} catch (IOException e) {
            System.out.println("Failed to create new Window." + e);}
                }catch(Exception e) {}
            }else{
                //gagal
                JOptionPane.showMessageDialog(null,"tidak berhasil verifikasi");
            }}catch(Exception e) {}}
}
