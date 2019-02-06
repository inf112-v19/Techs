package inf112.skeleton.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckpointTest {

    private Checkpoint checkpoint;

    @Before
    public void setUp(){
        checkpoint = new Checkpoint();
    }

    @Test
    public void testSetGetSymbol() {
        checkpoint.setSymbol("X");
        assertEquals("X", checkpoint.getSymbol());
    }

    @Test
    public void testSetGetName() {
        checkpoint.setName("newName");
        assertEquals("newName",checkpoint.getName());
    }

    @Test
    public void testSetGetWeight() {
        checkpoint.setWeight(10);
        assertEquals(10, checkpoint.getWeight());
    }
}
