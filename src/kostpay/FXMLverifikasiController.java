package kostpay;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class FXMLverifikasiController implements Initializable {

    @FXML
    private TextArea output;
    @FXML
    private JFXButton btexit;
    private Connection conn = null;
    private PreparedStatement stmt = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    public void dataterima(String namav, String bulanv, String tglv,String codev,String bayarv){
        output.setText("nama : "+namav+"\nbulan pembayaran : "+bulanv+"\ntanggal pembayaran : "
                +tglv+"\nkode verifikasi : "+codev+"\nbayar : "+bayarv);
    }
}
