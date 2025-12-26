import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int SIZE = 672;
        int SQUARESIZE = 28;

        Maze newMaze = new Maze((int)SIZE/SQUARESIZE);
        newMaze.createMaze();
        
        JFrame frame = new JFrame("Java Graphics Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE, SIZE);
 
        DrawingPanel panel = new DrawingPanel(SQUARESIZE, newMaze.getRes());

        frame.add(panel);
        frame.setVisible(true);
    }
}