import javax.swing.*;

public class Main {    
    public static void main(String[] args) {
        int SIZE = 672;
        int SQUARESIZE = 28;
        int OFFSET = 7;
        
        JFrame frame = new JFrame("Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE, SIZE + SQUARESIZE);

        Maze newMaze = new Maze(SIZE, SQUARESIZE, OFFSET);
 
        DrawingPanel panel = new DrawingPanel(newMaze.getDrawCells(), SQUARESIZE, OFFSET);

        frame.add(panel);
        frame.setVisible(true);
    }
}