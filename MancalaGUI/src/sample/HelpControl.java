import javafx.fxml.FXML;

/**
 * Created by kimberly_93pc on 3/18/17.
 */
public class HelpControl {
    @FXML
    private javafx.scene.control.TextArea helpInfo;
    public void init(){
        this.helpInfo.setEditable(false);
    }
}
