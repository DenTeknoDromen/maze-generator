import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawMaze extends JPanel implements ActionListener {
    private ArrayList<int[]> drawCells;
    private Timer timer;
    private int squareSize;
    private int offset;
    private int maxIndex;

    public DrawMaze(ArrayList<int[]> drawCells, int squareSize, int offset) {
        this.drawCells = drawCells;
        this.squareSize = squareSize;
        this.offset = offset;
        timer = new Timer((int) (1000 / 60), this);
        timer.start();
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawCells(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < maxIndex; i++) {
            g.fillRect(drawCells.get(i)[0], drawCells.get(i)[1], squareSize - (offset * 2), squareSize - (offset * 2));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        maxIndex = (maxIndex < drawCells.size() - 1) ? maxIndex + 1 : maxIndex + 0;
        repaint();
    }
}