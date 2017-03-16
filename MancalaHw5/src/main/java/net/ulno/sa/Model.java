package net.ulno.sa;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

/**
 * Created by Salonika on 3/14/17.
 */
public class Model {
    /**
     *
     * @see <a href='../../../../../../doc/Model.html'>Model.html</a>
     */
    public static void main(String[] args) {
        ClassModel model = new ClassModel("net.ulno.sa");

        Clazz playerClass = model.createClazz("Player")
                .withAttribute("name", DataType.STRING)
                .withAttribute("pebblesHolding", DataType.INT);

        Clazz pitClass = model.createClazz("Pit")
                .withAttribute("pebblesIn", DataType.INT)
                .withAttribute("isKalah", DataType.BOOLEAN);

        // pitClass.withAttribute("oppositeOf", DataType.create(pitClass));

        Clazz boardClass = model.createClazz("Board");

        boardClass.withMethod("takeOppositePebbles", DataType.VOID, new Parameter(playerClass).with("movingPlayer"),
                new Parameter(playerClass).with("otherPlayer"),
                new Parameter(DataType.INT).with("curLocation"));

        //university.withBidirectional(student, "student", Cardinality.MANY,"almaMater",Cardinality.ONE);
        playerClass.withBidirectional(pitClass,"playerIBelongTo",Cardinality.MANY,"pitsIHave",Cardinality.MANY);
//        playerClass.withBidirectional(boardClass,"player",Cardinality.MANY,"boardIBelongTo",Cardinality.ONE);
        boardClass.withBidirectional(playerClass,"boardIBelongTo",Cardinality.ONE,"player",Cardinality.MANY);

        pitClass.withBidirectional(pitClass,"oppositeOf",Cardinality.MANY,"oppositeOf",Cardinality.MANY);
        pitClass.withBidirectional(pitClass,"successor",Cardinality.ONE,"successor",Cardinality.ONE);



        model.generate();

        Storyboard storyboard = new Storyboard();
        storyboard.add("This is just showing us our class diagram");
        storyboard.addClassDiagram(model);
        storyboard.add("No methods are actually specified");
        storyboard.dumpHTML();
    }
}
