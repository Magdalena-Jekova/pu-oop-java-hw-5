package pixels;

import java.awt.*;

public class Pixel {

    public static final int PIXEL_SIZE = 30;
    public static final int PIXELS_SIDE_COUNT = 64;
    public static final int PIXELS_COUNT = 4096;
    protected int row;
    protected int col;
    protected Color color;

    public Pixel(int row, int col, Color color){
        this.row   = row;
        this.col   = col;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Метод за визуализиране на пикселите.
     * @param g Graphics object
     */
    public void render(Graphics g){
        int pixelX = this.col * PIXEL_SIZE;
        int pixelY = this.row * PIXEL_SIZE;

        g.setColor(Color.GRAY);
        g.fillRect(pixelX, pixelY, PIXEL_SIZE, PIXEL_SIZE);

        g.setColor(getColor());
        g.fillRect(pixelX + 1, pixelY + 1, PIXEL_SIZE - 3, PIXEL_SIZE - 3);
    }
}