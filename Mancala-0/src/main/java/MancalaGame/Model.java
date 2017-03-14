package MancalaGame;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import org.sdmlib.models.classes.ClassModel;

/**
 * Created by kimberly_93pc on 3/14/17.
 */
public class Model {
    public static void main(String args[]) {

        ClassModel model = new ClassModel("MancalaGame");
        Clazz pit = model.createClazz("Pit").withAttribute("pebbles", DataType.INT)
                                                    .withAttribute("isKalah", DataType.BOOLEAN)
                                                    .withAttribute("isKalah", DataType.BOOLEAN);
//                                                    .withAttribute("oppositeOf", pit)
//                                                    .withAttribute("successor", pit)
//                                                    .withAttribute("successor", player);

        Clazz player = model.createClazz("Player").withAttribute("name", DataType.STRING)
                                                            .withAttribute("pebbles_holding", DataType.INT);
//                                                            .withAttribute("pitsIHave", DataType.pit);

        pit.withBidirectional(player, "player", Cardinality.ONE, "piot", Cardinality.MANY);

        model.generate();
    }
}
