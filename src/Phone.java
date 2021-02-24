import pixels.BluePixel;
import pixels.GreenPixel;
import pixels.Pixel;
import pixels.RedPixel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Phone extends JFrame {

    public Pixel[][] pixels;
    private Random random;
    private int randomRow;
    private int randomCol;
    private String serialNum;

    public Phone(String serialNum){
        this.pixels = new Pixel[Pixel.PIXELS_SIDE_COUNT][Pixel.PIXELS_SIDE_COUNT];
        this.serialNum = serialNum;
        random = new Random();

        setScreenPixelsOnRandomPositions();

        this.setTitle(getSerialNumber());
        this.setSize(1920,1920);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public String getSerialNumber() {
        return serialNum;
    }

    /**
     * Метод за визуализиране на всички пиксели върху дисплея.
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        for(int row = 0; row < pixels.length; row++){
            for(int col = 0; col < pixels.length; col++){
                Pixel pixel = pixels[row][col];
                pixel.render(g);
            }
        }
    }

    /**
     * Метод, при извикването на който се инициализират всички пиксели върху дисплея на случайни позиции.
     */
    public void setScreenPixelsOnRandomPositions(){
        setRedPixels();
        setGreenPixels();
        setBluePixels();
    }

    /**
     * Метод за инициализиране на червените пиксели.
     */
    private void setRedPixels(){

        for(int i = 0; i < 1000; i++){

            randomRow = random.nextInt(64);
            randomCol = random.nextInt(64);

            if(pixels[randomRow][randomCol] == null){
                pixels[randomRow][randomCol] = new RedPixel(randomRow, randomCol);
            }else{
                i--;
            }
        }
    }

    /**
     * Метод за инициализиране на зелените пиксели.
     */
    private void setGreenPixels(){

        for(int i = 0; i < 2000; i++){

            randomRow = random.nextInt(64);
            randomCol = random.nextInt(64);

            if(pixels[randomRow][randomCol] == null){
                pixels[randomRow][randomCol] = new GreenPixel(randomRow, randomCol);
            }else{
                i--;
            }
        }
    }

    /**
     * Метод за инициализиране на сините пиксели.
     */
    private void setBluePixels(){

        for(int i = 0; i < 1096; i++){

            randomRow = random.nextInt(64);
            randomCol = random.nextInt(64);

            if(pixels[randomRow][randomCol] == null){
                pixels[randomRow][randomCol] = new BluePixel(randomRow, randomCol);
            }else{
                i--;
            }
        }
    }
}