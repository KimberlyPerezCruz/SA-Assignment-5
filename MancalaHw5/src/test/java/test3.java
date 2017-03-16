import net.ulno.sa.Board;
import net.ulno.sa.Player;
import org.junit.Test;


public class test3 {
    @Test
    public void reDistributeCounterclockwise() throws Exception {
        //Creating Players
        Player p1 = new Player();
        p1.setName("sal");
        Player p2 = new Player();
        p2.setName("ash");

        //Setting both players with same board. Not sure if it is ok.
//        Board board = p1.createPlayer();
//        board.setBoardIBelongTo(p2);


//        board.ReDistributeCounterclockwise(p1.getPits().get(1), p1.getPits().get(1).getPebbles_in());
//        assertEquals(p1.getPits().get(1).getPebbles_in(), 0);
    }
}
