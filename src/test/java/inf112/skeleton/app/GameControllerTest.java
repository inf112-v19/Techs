package inf112.skeleton.app;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.logic.BoardCards;
import inf112.skeleton.app.logic.BoardLogic;

import inf112.skeleton.app.logic.CardType;
import inf112.skeleton.app.objects.IProgramCard;
import inf112.skeleton.app.objects.ProgramCard;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameControllerTest {

    private TiledMap noWallMap;
    private BoardLogic boardLogic;
    private RoboRally roboRally;
    private BoardCards boardCards;
    private GameController gameController;


    @Before
    public void setUp(){
        noWallMap = new TmxMapLoader().load("assets/testMaps/mapNoWalls.tmx");
        boardLogic = new BoardLogic(noWallMap);
        roboRally = new RoboRally();
        boardCards = new BoardCards(roboRally, 2, 0);
        gameController = new GameController(2, 0, boardCards);
    }

    @Test
    void donePickingCards() {
        ArrayList<IProgramCard> programCards = new ArrayList<>();
        programCards.add(new ProgramCard(CardType.MOVEMENT_1, 1, 0, 1));
        programCards.add(new ProgramCard(CardType.MOVEMENT_1, 1, 0, -1));
        programCards.add(new ProgramCard(CardType.MOVEMENT_1, 1, 0, 1));
        programCards.add(new ProgramCard(CardType.MOVEMENT_1, 1, 0, -1));
        programCards.add(new ProgramCard(CardType.MOVEMENT_1, 1, 0, 1));
        //gameController.donePickingCards(programCards, boardCards);
    }

    @Test
    void movePlayers() {

    }

    @Test
    void movePlayer() {
    }

    @Test
    void getProgramCardToPlayer() {
    }

    @Test
    void getTurns() {

    }
}
