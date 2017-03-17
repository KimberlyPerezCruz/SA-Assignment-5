import net.ulno.sa.Board;
import net.ulno.sa.Pit;
import net.ulno.sa.Player;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

public class test2 {
    /*
     *  You can run the test by clicking on the button to the right of the test function that looks like a play button.
     *  Scenario:
     *  Pre-condition: Players already made a move, empty pit on Sal's side.
     *  Post-condition: First player's Kalah has more pebbles.
     *  Player Sal's last pebble fell in an empty pit, so Sal takes pebbles from opposite pit, which is Ash's pit.
     *  Now Sal's fifth pit has zero pebbles and his Kalah has all of Ash's fifth pit pebbles and the last one he
     *  dropped on empty pit.
 * @see <a href='../../../doc/takeOppositePebbles.html'>takeOppositePebbles.html</a>
 */

    @Test
    public void takeOppositePebbles() throws Exception {
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


        //Pre-condition: Players already made a move, empty pit on Sal's side.
        board.ReDistributeCounterclockwise(p1.getPitsIHave().get(5), p1,p2);

        // Making the move
        int p1PebblesinKalah = p1.getPitsIHave().get(6).getPebblesIn();
        int p2Pebblesinfifth = p2.getPitsIHave().get(5).getPebblesIn();
        board.takeOppositePebbles(p1, p2, 5);

        //Asserting Post-condition: First player's Kalah has more pebbles.
        assert(p1.getPitsIHave().get(5).getPebblesIn() == 0);// Position 5 of Sal's Pit has no pebbles now.
        // Sal's Kalah has more now.
        assert(p1.getPitsIHave().get(6).getPebblesIn() == p1PebblesinKalah+p2Pebblesinfifth+1);
        assert(p2.getPitsIHave().get(6).getPebblesIn() == 0);// Position 5 of Ash's pit has not pebbles.


        //Creating Storyboard
        storyboard.add("Test Two Scenario:\n" +
                "     \nPre-condition: Players already made a move, empty pit on Sal's side.\n" +
                "     \nPost-condition: First player's Kalah has more pebbles.\n" +
                "     \nPlayer Sal's last pebble fell in an empty pit, so Sal takes pebbles from opposite pit, which is Ash's pit.\n" +
                "     \nNow Sal's fifth pit has zero pebbles and his Kalah has all of Ash's fifth pit pebbles and the last one he\n" +
                "     \ndropped on empty pit.\n");
        storyboard.addObjectDiagram("Sal", p1, "Ash", p2, "board", board);
        storyboard.dumpHTML();
    }
}
