

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import  sa.*;

/**
 * Created by kimberly_93pc on 3/18/17.
 */
public class BoardController implements Initializable {
    @FXML
    private javafx.scene.control.Label player1;
    @FXML
    private javafx.scene.control.Label currentPlayer;
    @FXML
    private javafx.scene.control.Label player2;

    //Getting pit labels, to change number of pebbles
    @FXML
    private javafx.scene.control.Label pit1;  @FXML private javafx.scene.control.Label pit2;
    @FXML
    private javafx.scene.control.Label pit3;  @FXML private javafx.scene.control.Label pit4;
    @FXML
    private javafx.scene.control.Label pit5;  @FXML private javafx.scene.control.Label pit6;
    @FXML
    private javafx.scene.control.Label pit7;  @FXML private javafx.scene.control.Label pit8;
    @FXML
    private javafx.scene.control.Label pit9;  @FXML private javafx.scene.control.Label pit10;
    @FXML
    private javafx.scene.control.Label pit11; @FXML private javafx.scene.control.Label pit12;
    @FXML
    private javafx.scene.control.Label pit13; @FXML private javafx.scene.control.Label pit14;

    Player p1;
    Player p2;
    Board board;
    Label [] labels = new Label[14];
    Pit lastPit = null;

    public void gameInfo(String player1, String player2){
        //Making labels with names
        this.player1.setText(player1);
        this.currentPlayer.setText(player1);
        this.player2.setText(player2);

        labels[0] = pit1;   labels[1] = pit2;
        labels[2] = pit3;   labels[3] = pit4;
        labels[4] = pit5;   labels[5] = pit6;
        labels[6] = pit7;   labels[7] = pit8;
        labels[8] = pit9;   labels[9] = pit10;
        labels[10] = pit11; labels[11] = pit12;
        labels[12] = pit13; labels[13] = pit14;

        //Creating Players, also creates the pits with 4 pebbles each.
        p1 = new Player();
        p1.setName(this.player1.getText());
        p1.withName(this.player1.getText());

        p2 = new Player();
        p2.setName(this.player2.getText());
        p2.withName(this.player2.getText());

        //Setting both players with same board.
        board = new Board();
        board = board.withPlayers(p1,p2);//This is what actually set them together.
        p1.createPlayer(board);
        p1.withBoard(board);
        p2.createPlayer(board);
        p2.withBoard(board);

        //Adding labels to an array, makes it easier to search.
        for(int i=0; i<14; i++)
        {
            if(!this.labels[i].getId().equals("pit7") && !this.labels[i].getId().equals("pit14"))
                this.labels[i].setText("4");

            //Setting opposite pits of board.
            if(i<7)
                p1.getPitsIHave().get(i).createOppositeOf(i,p1,p2);
        }
    }
    //Cancel button
    public void onCancel()throws Exception {
        Main.primaryStage.close();
    }
    //Help button
    public void onHelp()throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Help.fxml"));
        Scene scene =  new Scene((Pane)loader.load(), 600, 600);
        Stage stage = Main.primaryStage;
        stage.setScene(scene);

        HelpControl hc = loader.<HelpControl>getController();
        hc.init();
    }
    //Back button
    public void onBack()throws Exception {

        Parent window = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        Scene scene = new Scene(window, 600, 600);
        Stage stage = Main.primaryStage;
        stage.setScene(scene);
    }
    //Called when a pit is clicked.
    @FXML
    public void handleButtonClick(Event event){

        int pits = 0;
        for(int i=0; i<14; i++)
        {
            if(pits==7)pits=0;
            if(event.getSource().equals(this.labels[i]))
            {   //First player turns
                if(i<6 && this.currentPlayer.getText().equals(this.player1.getText()))
                {
                    if(p1.getPitsIHave().get(pits).getPebblesIn()==0)
                    {//Trying to get pebbles from empty pit.
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Mancala");
                        a.setHeaderText("Try again");
                        String version = System.getProperty("java.version");
                        String content = String.format("Can't take pebbles from empty pit");
                        a.setContentText(content);
                        a.showAndWait();
                        a.close();
                        return;
                    }

                    lastPit = board.ReDistributeCounterclockwise(p1.getPitsIHave().get(pits), p1, p2);
                    this.currentPlayer.setText(player2.getText());
                }//Second player turns
                else if(i>=6 && this.currentPlayer.getText().equals(this.player2.getText())){
                    if(p2.getPitsIHave().get(pits).getPebblesIn()==0)
                    {//Trying to get pebbles from empty pit.
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Mancala");
                        a.setHeaderText("Try again");
                        String version = System.getProperty("java.version");
                        String content = String.format("Can't take pebbles from empty pit");
                        a.setContentText(content);
                        a.showAndWait();
                        a.close();
                        return;
                    }

                    lastPit = board.ReDistributeCounterclockwise(p2.getPitsIHave().get(pits), p2, p1);
                    this.currentPlayer.setText(player1.getText());
                }
                else{//When player takes pebble from wrong side.
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Mancala");
                    a.setHeaderText("Try again");
                    String version = System.getProperty("java.version");
                    String content = String.format("Can't take pebbles from pit that are not yours");
                    a.setContentText(content);
                    a.showAndWait();
                    a.close();
                    return;
                }
                break;
            }
            pits++;
        }

        //Player gains additional move.
        if(lastPit.isIsKalah())
        {
            if(!player1.getText().equals(this.currentPlayer.getText()))//Opposite because player already changed.
                 this.currentPlayer.setText(player1.getText());
            else
                this.currentPlayer.setText(player2.getText());
        }

        //Checks if last one is empty and if opposite is not & collects all pebbles to kalah.
        pebblesFromOppositePit();

        //Adding new labels to pit after move
        int labelspit = 7;
        int p1emptyPitCounter = 0;
        int p2emptyPitCounter = 0;
        for(int i=0; i<7; i++)
        {
            this.labels[i].setText(p1.getPitsIHave().get(i).getPebblesIn()+"");
            if(p1.getPitsIHave().get(i).getPebblesIn()==0)p1emptyPitCounter++;//Counting empty pit

            this.labels[labelspit].setText(p2.getPitsIHave().get(i).getPebblesIn()+"");
            if(p2.getPitsIHave().get(i).getPebblesIn()==0)p2emptyPitCounter++;//Counting empty pit
            labelspit++;
        }
        try {
            if (p1emptyPitCounter == 6 || p2emptyPitCounter == 6) gameEnd();
        }catch (Exception e){System.out.println(e);}
    }


    public void pebblesFromOppositePit(){
        //Taking pebbles from opposite pit.
        if(!lastPit.isIsKalah() && lastPit.getPebblesIn()==1 && lastPit.getOppositePit().getPebblesIn()!=0) {
            lastPit.setPebblesIn(0);
            if(!player1.getText().equals(this.currentPlayer.getText()))
                board.takeOppositePebbles(p1, p2, lastPit.getLocation());
            else
                board.takeOppositePebbles(p2, p1, lastPit.getLocation());
        }
    }
    //Called when game is over.
    public void gameEnd() throws Exception{
        onBack();
        String winner;
        if(p1.getPitsIHave().get(6).getPebblesIn()>p2.getPitsIHave().get(6).getPebblesIn())
            winner = p1.getName();
            // this.winner.setText(p1.getName());
        else
            winner = p2.getName();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Mancala");
        a.setHeaderText("Winner");
        String version = System.getProperty("java.version");
        String content = String.format(winner);
        a.setContentText(content);
        a.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
