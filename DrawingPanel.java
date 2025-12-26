import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private int squareSize;
    private ArrayList<String> res;
    private int offset = 7;

    public DrawingPanel(int newSquareSize, ArrayList<String> newRes) {
        this.squareSize = newSquareSize;
        this.res = newRes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawGrid(g);
        g.setColor(Color.WHITE);
        String[] initPos = res.get(0).split(":");
        int preXPos = Integer.parseInt(initPos[0]);
        int preYPos = Integer.parseInt(initPos[1]);
        for (String cord : res) {
            String[] resSplit = cord.split(":");
            int x = Integer.parseInt(resSplit[0]);
            int y = Integer.parseInt(resSplit[1]);

            int directionX = getDirection(x - preXPos);
            int directionY = getDirection(y - preYPos);
            System.out.println("prePos: " + preXPos + ":" + preYPos);
            System.out.println("pos: " + x + ":" + y);
            System.out.println(directionX + ":" + directionY);
            System.out.println("----------------------------------------");
            g.fillRect((preXPos * squareSize) + (offset),
                    (preYPos * squareSize) + offset,
                    squareSize - (offset * 2),
                    squareSize - (offset * 2));

            g.fillRect((preXPos * squareSize) + (offset + directionX),
                    (preYPos * (squareSize)) + (offset + directionY),
                    squareSize - (offset * 2),
                    squareSize - (offset * 2));

            preXPos = x;
            preYPos = y;
            
        }
    }

    private int getDirection(int posDiff) {
        if (posDiff == 1 || posDiff == -1) {
            return (int) (squareSize / 2) * posDiff;
        }
        return 0;
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.red);
        for (int x = 0; x < getWidth(); x += squareSize) {
            g.drawLine(x, 0, x, getHeight());
        }
        for (int y = 0; y < getHeight(); y += squareSize) {
            g.drawLine(0, y, getWidth(), y);
        }
    }
}