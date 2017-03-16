import net.ulno.sa.Board;
import net.ulno.sa.Pit;
import net.ulno.sa.Player;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

public class test2 {
    /*
     *  You can run the test by clicking on the button to the right of the test function that looks like a play button.
     *  Scenario:
     *  Player Sal's last pebble fell in an empty pit, so Sal takes pebbles from opposite pit, which is Ash's pit.
     *  Now Sal's fifth pit has zero pebbles and his Kalah has all of Ash's fifth pit pebbles and the last one he
     *  dropped on empty pit.
     * * @see <a href='../../../doc/takeOppositePebbles.html'>takeOppositePebbles.html</a>
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
        p1.createPlayer(board);
        p1.withPlayer(board);
        p2.createPlayer(board);
        p2.withPlayer(board);

        //Pre-Condition
        p1.getPitsIHave().get(5).setPebblesIn(0);

        int p1PebblesinKalah = p1.getPitsIHave().get(6).getPebblesIn();
        int p2Pebblesinfifth = p2.getPitsIHave().get(5).getPebblesIn();
        board.takeOppositePebbles(p1, p2, 5);
        assert(p1.getPitsIHave().get(5).getPebblesIn() == 0);// Position 5 of Sal's Pit has no pebbles now.
        // Sal's Kalah has more now.
        assert(p1.getPitsIHave().get(6).getPebblesIn() == p1PebblesinKalah+p2Pebblesinfifth+1);
        assert(p2.getPitsIHave().get(6).getPebblesIn() == 0);// Position 5 of Ash's pit has not pebbles.


        storyboard.add("Test one Scenario:\n" +
                "     \nPre-condition: Players already made a move, this is 1st player first move.\n" +
                "     \nPost-condition: First player's Kalah has more pebbles.\n" +
                "     Player Sal's last pebble fell in an empty pit, so Sal takes pebbles from opposite pit, which is Ash's pit.\n" +
                "     Now Sal's fifth pit has zero pebbles and his Kalah has all of Ash's fifth pit pebbles and the last one he\n" +
                "     dropped on empty pit.");
        storyboard.addObjectDiagram("Sal", p1, "Ash", p2, "board", board);
        storyboard.dumpHTML();
    }
}
