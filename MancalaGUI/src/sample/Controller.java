

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

   @FXML
    private javafx.scene.control.TextField player1;

   @FXML
   private javafx.scene.control.TextField player2;

   public void onPlay()throws Exception {
       String  name = player1.getText();
       String  name2 = player2.getText();

       if(name.equals("") || name2.equals("") || name.equals(name2)){
           Alert a = new Alert(Alert.AlertType.INFORMATION);
           a.setTitle("Mancala");
           a.setHeaderText("Try again");
           String version = System.getProperty("java.version");
           String content = String.format("Please type a name in both fields before beginning, they must be different");
           a.setContentText(content);
           a.showAndWait();
           a.close();
           return;
       }
       FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Board.fxml"));
       Scene scene =  new Scene((Pane)loader.load(), 600, 600);
       Stage stage = Main.primaryStage;
       stage.setScene(scene);

       BoardController bc = loader.<BoardController>getController();
       bc.gameInfo(name, name2);



   }
    public void onCancel()throws Exception {
       Main.primaryStage.close();
    }
    public void onHelp()throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Help.fxml"));
        Scene scene =  new Scene((Pane)loader.load(), 600, 600);
        Stage stage = Main.primaryStage;
        stage.setScene(scene);

        HelpControl hc = loader.<HelpControl>getController();
        hc.init();
    }
    public void onBack()throws Exception {

        Parent window = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        Scene scene = new Scene(window, 600, 600);
        Stage stage = Main.primaryStage;
        stage.setScene(scene);
    }
}
