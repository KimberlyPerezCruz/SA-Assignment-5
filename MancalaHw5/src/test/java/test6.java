import net.ulno.sa.Board;
import net.ulno.sa.Player;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

/**
 * Created by kimberly_93pc on 3/15/17.
 */
public class test6 {
    /*
     *  You can run the test by clicking on the button to the right of the test function that looks like a play button.
     *  Scenario:
     *  Pre-condition: Sal's last pebble landed on his Kalah.
     *  Post-condition: His Kalah has one extra pebble and Sal has one extra move.
     *  Player Sal will make a move his last pebble lands on his Kalah, this will add one more pebble to his
     *  Kalah and one more extra move to select any pit he wants to redistribute.
     *  He selects his pit 4 and another pebbles lands on his Kalah, but it is not the last one so he
     *  does not get another extra move.
 * @see <a href='../../../doc/FourthReDistributeCounterclockwise.html'>FourthReDistributeCounterclockwise.html</a>
 * @see <a href='../../../doc/FifthReDistributeCounterclockwise.html'>FifthReDistributeCounterclockwise.html</a>
 */
    @Test
    public void FifthReDistributeCounterclockwise() throws Exception {
        Storyboard storyboard = new Storyboard();

        //Creating Players, also creates the pits with 4 pebbles each.
        Player p1 = new Player();
        p1.setName("Sal");
        p1.withName("Sal");

        Player p2 = new Player();
        p2.setName("Ash");
        p2.withName("Ash");

        //Setting both players with same board.
        Board board = new Board();
        board = board.withPlayers(p1,p2);//This is what actually set them together.
        p1.createPlayer(board);
        p1.withBoard(board);
        p2.createPlayer(board);
        p2.withBoard(board);

        //Pre-condition: Sal makes a move, last pebbles lands on an empty pit.
        board.ReDistributeCounterclockwise(p1.getPitsIHave().get(2), p1, p2);

        //Sal gets an extra move.
        board.ReDistributeCounterclockwise(p1.getPitsIHave().get(3), p1, p2);


        //Asserting post-conditions: Sal's pebbles are distributed, Kalah has an extra pebble.
        assert(p1.getPitsIHave().get(2).getPebblesIn()==0);//Empty pit from Sal's first move.
        assert(p1.getPitsIHave().get(3).getPebblesIn()==0);//Empty pit from Sal's second move.
        assert(p1.getPitsIHave().get(6).getPebblesIn()==2);//Tow pebble on Sal's Kala because
                                                                // both move added a pebble .

        //Creating Storyboard
        storyboard.add("Test six Scenario:\n" +
                "     \nPre-condition: Sal's last pebble landed on his Kalah.\n" +
                "     \nPost-condition: His Kalah has one extra pebble and Sal has one extra move. " +
                "     \nPlayer Sal will make a move his last pebble lands on his Kalah, this will\n" +
                "     \nadd one more pebble to his Kalah and one more extra move to select any pit\n" +
                "     \nhe wants to redistribute. He selects his pit 4 and another pebbles lands on his Kalah,\n" +
                "     \nbut it is not the last one so he does not get another extra move.\n");
        storyboard.addObjectDiagram("Sal", p1, "Ash", p2, "board", board);
        storyboard.dumpHTML();
    }
}
