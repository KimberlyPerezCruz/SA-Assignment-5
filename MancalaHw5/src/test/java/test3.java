import net.ulno.sa.Board;
import net.ulno.sa.Player;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;


public class test3 {
      /**
    * 
    * @see <a href='../../../doc/SecondReDistributeCounterclockwise.html'>SecondReDistributeCounterclockwise.html</a>
 */
   @Test
    public void SecondReDistributeCounterclockwise() throws Exception {
        Storyboard storyboard = new Storyboard();
        //Pre-condition: initial state.

        //Creating Players, also creates the pits with 4 pebbles each.
        Player p1 = new Player();
        p1.setName("Sal");
        p1.withName("Sal");

        Player p2 = new Player();
        p2.setName("Ash");
        p2.withName("Ash");

        //Setting both players with same board.
        Board board = new Board();
        board = board.withPlayers(p1, p2);//This is what actually set them together.
        p1.createPlayer(board);
        p1.withBoard(board);
        p2.createPlayer(board);
        p2.withBoard(board);

        //Player one Sal, makes first move.
       int pebs = p2.getPitsIHave().get(1).getPebblesIn();
       System.out.println(pebs);
       board.ReDistributeCounterclockwise(p2.getPitsIHave().get(1), p1, p2);

        //Asserting post-conditions.
        assert (p2.getPitsIHave().get(1).getPebblesIn() == 0);// Position 5 of Sal's Pit has no pebbles now.
        // All pits counterclockwise after this one up to 4 have one extra pebbles (5 pebbles).

       assert (p2.getPitsIHave().get(2).getPebblesIn() == 5);
       assert (p2.getPitsIHave().get(3).getPebblesIn() == 5);
       assert (p2.getPitsIHave().get(4).getPebblesIn() == 5);
       assert (p2.getPitsIHave().get(5).getPebblesIn() == 5);


        storyboard.add("Test three Scenario:\n" +
                "     \nPre-condition: Game initial state, this is first move.\n" +
                "     \nPost-condition: Ash has completed her turn. Ash's pits now have 1 extra pebble in them\n" +
                "     Scenario:" +
                "     Second player Ash makes her first move, selecting the pebbles from pit 1 to distribute." +
                "     Now, Ash's pit 1 will have no pebbles and four pits that follow the second pit, " +
                "     have one extra pebble.");
        storyboard.addObjectDiagram("Sal", p1, "Ash", p2, "board", board);
        storyboard.dumpHTML();
    }
}
