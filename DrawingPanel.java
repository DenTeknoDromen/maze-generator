import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private int squareSize;
    private ArrayList<String> res;
    private int offset = 4;

    public DrawingPanel(int newSquareSize, ArrayList<String> newRes) {
        this.squareSize = newSquareSize;
        this.res = newRes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        String[] initPos = res.get(0).split(":");
        int preXPos = Integer.parseInt(initPos[0]);
        int preYPos = Integer.parseInt(initPos[1]);
        for (String cord : res) {
            String[] resSplit = cord.split(":");
            int x = Integer.parseInt(resSplit[0]);
            int y = Integer.parseInt(resSplit[1]);
            
            int writeXPos = x - preXPos;
            int writeYPos = y - preYPos;
            int direction = (int)(squareSize / (writeXPos * 2));
            System.out.println(direction);
            // System.out.println("prePos: " + preXPos + ":" + preYPos);
            // System.out.println("pos: " + x + ":" + y);
            // System.out.println("writePos: " + writeXPos + ":" + writeYPos);
            // System.out.println("----------------------------------------");
            // g.fillRect((x * squareSize) + 4, (y * squareSize)+ 4, squareSize - 8, squareSize - 8);
            while (preXPos != x && preYPos != y) {
                preXPos += writeXPos / 2;
                preYPos += writeYPos / 2;
                System.out.println("prePos: " + preXPos + ":" + preYPos);
                System.out.println("pos: " + x + ":" + y);
                System.out.println("writePos: " + writeXPos + ":" + writeYPos);
                System.out.println("----------------------------------------");
                g.fillRect((preXPos * squareSize) + offset,
                (preYPos * squareSize)+ offset, 
                squareSize - (offset * 2), 
                squareSize - (offset * 2));
            }
            preXPos = x;
            preYPos = y;
        }
    }
}