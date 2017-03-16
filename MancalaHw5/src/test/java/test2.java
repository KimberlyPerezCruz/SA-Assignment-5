import net.ulno.sa.Board;
import net.ulno.sa.Pit;
import net.ulno.sa.Player;
import org.junit.Test;


public class test2 {
    @Test
    public void takeOppositePebbles2() throws Exception {
        //Creating Players
        Player p1 = new Player();
        p1.setName("sal");
        Player p2 = new Player();
        p2.setName("ash");

        //Setting both players with same board.
        Board board = new Board();
        p1.createPlayer(board);
        p2.createPlayer(board);

        //Setting both players with same pits.
        Pit pit = p1.createPitsIHave();
        p2.createPitsIHave();
        pit.withPlayerIBelongTo(p1,p2);



    }
}
