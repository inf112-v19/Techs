package inf112.skeleton.app.inWork.game;

import com.badlogic.gdx.maps.tiled.TiledMapTile;

import java.util.HashMap;

public enum TileType {

    FLOOR(5, false, false, "floor"),
    PIT(6, false, false,"pit"),
    UPGRADE(7, false, false, "upgrade"),
    D_CONVEYOR_UP(12, false, false,"double_conveyor_up"),
    D_CONVEYOR_RIGHT(13, false,false,"double_conveyor_right"),
    REPAIR(14, false, false, "repair"),
    D_CONVEYOR_DOWN(19, false, false, "double_conveyor_down"),
    D_CONVEYOR_LEFT(20, false, false,"double_conveyor_left"),
    WALL_RIGHT(21, true, false,"wall_right"),
    WALL_DOWN(26, true, false,"wall_down"),
    WALL_LEFT(27, true, false,"wall_left"),
    WALL_UP(28, true, false,"wall_up"),
    CONVEYOR_LEFTDOWN(29, false, false, "conveyor_leftdown"),
    CONVEYOR_UPLEFT(30, false, false, "conveyor_upleft"),
    CONVEYOR_UPRIGHT(31, false, false,"conveyor_upright"),
    CONVEYOR_RIGHTDOWN(32, false, false, "conveyor_rightdown"),
    LASER_UP(33, true, false, "laser_up", 1),
    LASER_RIGHT(34, true, false, "laser_right", 1),
    LASER_HORISONTAL(35, true, false, "laser_horisontal", 1),
    CONVEYOR_DOWNRIGHT(36, false, false,"conveyor_downright"),
    CONVEYOR_RIGHTUP(37, false, false,"conveyor_rightup"),
    CONVEYOR_LEFTUP(38, false, false,"conveyor_leftup"),
    CONVEYOR_DOWNLEFT(39, false, false,"conveyor_downleft"),
    LASER_DOWN(40, true, false, "laser_down", 1),
    LASER_LEFT(41, true, false, "laser_left", 1),
    LASER_VERTICAL(42, true, false, "laser_vertical", 1),
    CONVEYOR_UP(43,false, false,"conveyor_up"),
    CONVEYOR_DOWN(44,false, false,"conveyor_down"),
    CONVEYOR_LEFT(45,false, false,"conveyor_left"),
    CONVEYOR_RIGHT(46,false, false,"conveyor_right"),
    ROTATE_LEFT(47,false, false,"rotate_left"),
    ROTATE_RIGHT(48,false, false,"rotate_right"),
    CHECKPOINT_ONE(49,false, true, "checkpoint_one"),
    CHECKPOINT_TWO(56,false, true, "checkpoint_two"),
    CHECKPOINT_THREE(63,false, true, "checkpoint_three"),
    CHECKPOINT_FOUR(70,false, true, "checkpoint_four");

    private int id;
    private boolean collidable;
    private boolean checkpoint;
    private String name;
    private float damage;

    public static final int TILE_SIZE = 96;

    private TileType(int id, boolean collidable, boolean checkpoint, String name) {
        this(id, collidable, checkpoint, name, 0);

    }
    private TileType (int id, boolean collidable, boolean checkpoint, String name, float damage) {
        this.id = id;
        this.collidable = collidable;
        this.checkpoint = checkpoint;
        this.name = name;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public boolean isCheckpoint() {
        return checkpoint;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    private static HashMap<Integer, TileType> tileMap;

    static {
        tileMap = new HashMap<Integer, TileType>();
        for (TileType tileType : TileType.values()) {
            tileMap.put(tileType.getId(), tileType);
        }
    }

    public static TileType getTileTypeById(int id) {
        return tileMap.get(id);
    }
}
