import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawMaze extends JPanel implements ActionListener {
    private ArrayList<int[]> drawCells;
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private Timer timer;
    private int squareSize;
    private int maxIndex;
    private int wallSize;

    public DrawMaze(ArrayList<int[]> drawCells, int squareSize, int offset) {
        this.drawCells = drawCells;
        this.squareSize = squareSize;
        this.wallSize = squareSize - (offset * 2);
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
            g.fillRect(drawCells.get(i)[0], drawCells.get(i)[1], wallSize, wallSize);
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

        if (offScreenImage == null) {
            offScreenImage = createImage(getWidth(), getHeight());
            offScreenGraphics = offScreenImage.getGraphics();
        }
        offScreenGraphics.setColor(Color.BLUE);
        offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
        drawCells(offScreenGraphics);
        g.drawImage(offScreenImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        maxIndex = (maxIndex < drawCells.size() - 1) ? maxIndex + 1 : maxIndex + 0;
        repaint();
    }
}