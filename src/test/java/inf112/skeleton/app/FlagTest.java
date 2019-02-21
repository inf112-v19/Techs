package inf112.skeleton.app;

import inf112.skeleton.app.Tiles.Flag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlagTest {

    private Flag flag;

    @Before
    public void setUp(){
        flag = new Flag();
    }

    @Test
    public void testSetSymbol() {
        flag.setSymbol("X");
        assertEquals("X", flag.getSymbol());
    }

    @Test
    public void testSetName() {
        flag.setTileType("newName");
        assertEquals("newName", flag.getTileType());
    }

    @Test
    public void testSetWeight() {
        flag.setTileWeight(10);
        assertEquals(10, flag.getTileWeight());
    }
}
