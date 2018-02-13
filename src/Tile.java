/**
 * For Unification of Tiles over multiple classes
 */
public class Tile {
    private static final char TILE_BLANK = ' ';
    private static final char TILE_FOG = '~';
    private static final char TILE_HIT = 'X';
    private static final char TILE_TANK = 'A';

    public static char getTileBlank() {
        return TILE_BLANK;
    }

    public static char getTileFog() {
        return TILE_FOG;
    }

    public static char getTileHit() {
        return TILE_HIT;
    }

    public static char getTileTank() {
        return TILE_TANK;
    }
}

