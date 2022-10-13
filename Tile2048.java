import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
* Class not used in 2048 implementation, for better OOP, could implement
* in board class
*/
public class Tile2048 extends JLabel {

    private int value;
    private Color tileColor;

    /*
     * Constructs a tile with a value of n
     */
    public Tile2048(int v) {
        super();
        setHorizontalAlignment(SwingConstants.CENTER);
        setSize(100, 100);
        setOpaque(true);
        setValue(value);
    }

    /*
     * Get tile value
     */
    public int getValue() {
        return value;
    }

    /*
     * Set tile value
     */
    public void setValue(int value) {
        this.value = value;
        this.setBackground(getColor());
        this.setText("" + this.value);
    }

    /*
     * Get string rep of tile
     */
    public String toString() {
        return "" + value;
    }

    /*
     * Sets the tile's color based on its value
     */
    public void setColor() {
        if ( this.getValue() == 2 ) {
            tileColor = new Color( 238, 228, 218 );
        } else if ( this.getValue() == 4 ) {
            tileColor = new Color( 237, 224, 200 );
        } else if ( this.getValue() == 8 ) {
            tileColor = new Color( 242, 177, 121 );
        } else if ( this.getValue() == 16 ) {
            tileColor = new Color( 245, 149, 99 );
        } else if ( this.getValue() == 32 ) {
            tileColor = new Color( 246, 124, 95 );
        } else if ( this.getValue() == 64 ) {
            tileColor = new Color( 246, 94, 59 );
        } else if ( this.getValue() == 128 ) {
            tileColor = new Color( 237, 207, 114 );
        } else if ( this.getValue() == 256 ) {
            tileColor = new Color( 237, 204, 97 );
        } else if ( this.getValue() == 512 ) {
            tileColor = new Color( 237, 200, 80 );
        } else if ( this.getValue() == 1024 ) {
            tileColor = new Color( 237, 197, 63 );
        } else if ( this.getValue() == 2048){
            tileColor = new Color( 237, 194, 46 );
        }
    }

    /*
     * Get tile color
     */
    public Color getColor() {
        this.setColor();
        return tileColor;
    }

}
