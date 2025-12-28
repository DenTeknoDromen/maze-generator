import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawMaze extends JPanel {
    private ArrayList<int[]> drawCells;
    private int squareSize;
    private int offset;

    public DrawMaze(ArrayList<int[]> drawCells, int squareSize, int offset) {
        this.drawCells = drawCells;
        this.squareSize = squareSize;
        this.offset = offset;
    }
    
    private void drawBackground(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawCells(Graphics g) {
        g.setColor(Color.BLACK);
        for (int[] cells : drawCells) {
            g.fillRect(cells[0], cells[1], squareSize - (offset * 2), squareSize - (offset * 2));
        }
    }

    // Draws red lines showing every cell,
    // Used for debugging
    private void drawGrid(Graphics g) {
        g.setColor(Color.red);
        for (int x = 0; x < getWidth(); x += squareSize) {
            g.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += squareSize) {
            g.drawLine(0, y, getWidth(), y);
        }
    }    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        // drawGrid(g);
        drawCells(g);
    }
}