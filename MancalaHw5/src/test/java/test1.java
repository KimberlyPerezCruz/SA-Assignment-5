import net.ulno.sa.Board;
import net.ulno.sa.Pit;
import net.ulno.sa.Player;
import org.junit.Test;

public class test1 {
    /*
     *  You can run the test by clicking on the button to the right of the test function that looks like a play button.
     *  Scenario:
     *  Player Sal's last pebble fell in an empty pit, so Sal takes pebbles from opposite pit, which is Ash's pit.
     *  Now Sal's fifth pit has zero pebbles and his Kalah has all of Ash's fifth pit pebbles and the last one he
     *  dropped on empty pit.
     * */
    @Test
    public void takeOppositePebbles() throws Exception {
        //Creating Players, also creates the pits with 4 pebbles each.
        Player p1 = new Player();
        p1.setName("Sal");
        Player p2 = new Player();
        p2.setName("Ash");

        //Setting both players with same board.
        Board board = new Board();
        p1.createPlayer(board);
        p2.createPlayer(board);

        int p1PebblesinKalah = p1.getPitsIHave().get(6).getPebblesIn();
        int p2Pebblesinfifth = p1.getPitsIHave().get(5).getPebblesIn();
        board.takeOppositePebbles(p1, p2, 5);
        assert(p1.getPitsIHave().get(5).getPebblesIn() == 0);// Position 5 of Sal's Pit has no pebbles now.
        // Sal's Kalah has more now.
        assert(p1.getPitsIHave().get(6).getPebblesIn() == p1PebblesinKalah+p2Pebblesinfifth+1);
        assert(p2.getPitsIHave().get(6).getPebblesIn() == 0);// Position 5 of Ash's pit has not pebbles.
    }
}
