import pixels.BluePixel;
import pixels.GreenPixel;
import pixels.Pixel;
import pixels.RedPixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Phone extends JFrame implements MouseListener {

    public Pixel[][] pixels;
    public Pixel clickedPixel;
    private int clickCounter = 0;
    private Random random;
    private int randomRow;
    private int randomCol;
    private int burnedPixelsCounter;
    private String serialNum, phoneCondition;
    CustomCollection<Phone> workingPhonesCollection = new CustomCollection<>();
    CustomCollection<Phone> nonWorkingPhonesCollection = new CustomCollection<>();

    public Phone(String serialNum){
        this.pixels = new Pixel[Pixel.PIXELS_SIDE_COUNT][Pixel.PIXELS_SIDE_COUNT];
        this.serialNum = serialNum;
        random = new Random();

        setScreenPixelsOnRandomPositions();

        this.setTitle(getSerialNumber());
        this.setSize(1920,1920);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    public String getSerialNumber() {
        return serialNum;
    }

    public void setPhoneCondition(String phoneCondition) {
        this.phoneCondition = phoneCondition;
    }

    public String getPhoneCondition() {
        return phoneCondition;
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getScreenDimensionBasedOnCoordinates(e.getY());
        int col = this.getScreenDimensionBasedOnCoordinates(e.getX());

        this.clickedPixel = this.getPixel(row, col);

        if (this.clickedPixel != null && e.getClickCount() == 3) {

            int randomNum = random.nextInt(10);

            if(randomNum <= 2){
                this.clickedPixel.setColor(Color.BLACK);
                this.burnedPixelsCounter++;
            }else if(randomNum > 7){
                this.clickedPixel.setColor(Color.BLACK);
                repaint();
            }
            this.clickedPixel = null;
        }
        this.clickCounter++;
        repaint();

        if(this.clickCounter == Pixel.PIXELS_COUNT*3){
            this.showPhoneCondition();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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

    /**
     * Метод, при извикването на който се визуализира модален диалог за състоянието на продукта,
     * а на конзолата се извежда съответният модел телефон и неговото състояние.
     */
    private void showPhoneCondition(){
        if(this.burnedPixelsCounter > 2048){
            this.setPhoneCondition("not working");
            this.nonWorkingPhonesCollection.add(this);
            Modal.show(this, "Внимание!", "Телефонът е дефектен! Има повече от 50% изгорели пиксели!");
        }else{
            this.setPhoneCondition("good");
            this.workingPhonesCollection.add(this);
            Modal.show(this, "Внимание!", "Телефонът е в добро състояние!");
        }
        System.out.println(this.getSerialNumber() + " -> " + this.getPhoneCondition());
        dispose();
    }

    /**
     * Метод, който връща пиксел, намиращ се на съответния ред и колона.
     * @param row - редът, на който се намира пиксела
     * @param col - колоната, на която се намира пиксела
     * @return object
     */
    private Pixel getPixel(int row, int col) {
        return this.pixels[row][col];
    }

    private int getScreenDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / Pixel.PIXEL_SIZE;
    }
}