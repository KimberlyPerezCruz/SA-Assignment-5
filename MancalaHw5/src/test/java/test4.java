import net.ulno.sa.Board;
import net.ulno.sa.Player;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;


public class test4 {
    /*
     *  You can run the test by clicking on the button to the right of the test function that looks like a play button.
     *  Scenario:
     *  Pre-condition: Sal & Ash both make a moves and leave an empty pit opposite to each other.
     *  Post-condition: Ash's pebbles are distributed, last one lands on empty pit in both sides of board.
     *  Player Ash will make a move, but his last pebble lands on an empty pit. This will end Ash's move.
 * @see <a href='../../../doc/SecondReDistributeCounterclockwise.html'>SecondReDistributeCounterclockwise.html</a>
 * @see <a href='../../../doc/ThirdDistributeCounterclockwise.html'>ThirdDistributeCounterclockwise.html</a>
 */

    @Test
    public void ThirdDistributeCounterclockwise() throws Exception {
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

        //Pre-condition: Sal & Ash both make a moves and leave an empty pit opposite to each other.
        board.ReDistributeCounterclockwise(p2.getPitsIHave().get(5), p2, p1);
        board.ReDistributeCounterclockwise(p1.getPitsIHave().get(0), p1, p2);


        //Ash makes a move, last pebbles lands on an empty pit on both side.
        board.ReDistributeCounterclockwise(p2.getPitsIHave().get(1), p2, p1);


        //Asserting post-conditions: Ash's pebbles are distributed, last one lands on empty pit in both sides of board.
        assert(p1.getPitsIHave().get(0).getPebblesIn()==0);//Empty pit on Sal's side.
        assert(p2.getPitsIHave().get(5).getPebblesIn()==1);//Only one pebble on Ash's side, landing pit.



        //Creating Storyboard
        storyboard.add("Test four Scenario:\n" +
                "     \nPre-condition: Sal & Ash both make a moves and leave an empty pit opposite to each other.\n" +
                "     \nPost-condition: Ash's pebbles are distributed, last one lands on empty pit in both sides of board.\n" +
                "     \nPlayer Ash will make a move, but his last pebble lands on an empty pit on both side of board.\n" +
                "     \nThis will end Ash's move.\n");
        storyboard.addObjectDiagram("Sal", p1, "Ash", p2, "board", board);
        storyboard.dumpHTML();
    }
}
