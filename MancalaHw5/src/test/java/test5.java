import net.ulno.sa.Board;
import net.ulno.sa.Player;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

/**
 * Created by kimberly_93pc on 3/15/17.
 */
public class test5 {
        /*
         *  You can run the test by clicking on the button to the right of the test function that looks like a play button.
         *  Scenario:
         *  Pre-condition: Sal make a moves and leave an empty pit.
         *  Post-condition: Ash would have distributed hit pebbles and finish his move.
         *  Player Ash will make a move his last pebble lands on an none empty pit,
         *  but opposite pit does not have any pebbles. This will end Ash's move.
 * @see <a href='../../../doc/ThirdReDistributeCounterclockwise.html'>ThirdReDistributeCounterclockwise.html</a>
 */

    @Test
    public void ThirdReDistributeCounterclockwise() throws Exception {
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

        //Pre-condition: Sal made a moves and leaved an empty pit.
        board.ReDistributeCounterclockwise(p1.getPitsIHave().get(1), p1, p2);


        //Ash makes a move, last pebbles lands on an empty pit.
        board.ReDistributeCounterclockwise(p2.getPitsIHave().get(0), p2, p1);


        //Asserting post-conditions: Ash's pebbles are distributed, last one lands on empty pit in both sides of board.
        assert(p1.getPitsIHave().get(1).getPebblesIn()==0);//Empty pit on Sal's side.
        assert(p2.getPitsIHave().get(0).getPebblesIn()==0);//Empty pit after he made the move.
        assert(p2.getPitsIHave().get(4).getPebblesIn()==5);//5 pebbles on Ash's ending pit.


        //Creating Storyboard
        storyboard.add("Test five Scenario:\n" +
                "     \nPre-condition: Sal make a moves and leave an empty pit.\n" +
                "     \nPost-condition: Ash would have distributed hit pebbles and finish his move, " +
                "     \n5 in his last landing pit and 0 in his starting pit.\n" +
                "     \nPlayer Ash will make a move his last pebble lands on an none empty pit,\n" +
                "     \nbut opposite pit does not have any pebbles. This will end Ash's move.\n");
        storyboard.addObjectDiagram("Sal", p1, "Ash", p2, "board", board);
        storyboard.dumpHTML();
    }
}
