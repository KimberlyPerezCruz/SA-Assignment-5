import net.ulno.sa.Board;
import net.ulno.sa.Pit;
import net.ulno.sa.Player;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

public class test1 {
    /*
     *  You can run the test by clicking on the button to the right of the test function that looks like a play button.
     *  Scenario:
     *  First player Sal's make his first move, selecting the pebbles from pit 5 to distribute.
     *  Now, Sal's fifth pit will have no pebbles and four pits that follow the fifth pit, including
     *  Sal's Kalah, has one extra pebble.
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
        board = board.withPlayers(p1,p2);//This is what actually set them together.
        p1.createPlayer(board);
        p1.withBoard(board);
        p2.createPlayer(board);
        p2.withBoard(board);


        board.ReDistributeCounterclockwise(p1.getPitsIHave().get(5), p1, p2);

        assert(p1.getPitsIHave().get(5).getPebblesIn() == 0);// Position 5 of Sal's Pit has no pebbles now.

        // All pits counterclockwise after this one up to 4 have one extra pebbles (5 pebbles because this is first move).
        for(int i=3; i<=0; i++)// From Ash's last pit to pit 3, they have one more pebble.
            assert(p2.getPitsIHave().get(5).getPebblesIn() == 5);
        assert(p1.getPitsIHave().get(0).getPebblesIn() == 5);// Making sure Sal's pebbles also has one extra.

        storyboard.add("Test one Scenario:\n" +
                "     \nPre-condition: Game initial state, this is first move.\n" +
                "     \nPost-condition: Sal has 5 pebbles in pit 5 and 5 in his Kalah.\n" +
                "     Scenario:"+
                "     First player Sal's make his first move, selecting the pebbles from pit 5 to distribute."+
                "     Now, Sal's fifth pit will have no pebbles and four pits that follow the fifth pit, including"+
                "     Sal's Kalah, has one extra pebble.");
        storyboard.addObjectDiagram("Sal", p1, "Ash", p2, "board", board);
        storyboard.dumpHTML();
    }
}
