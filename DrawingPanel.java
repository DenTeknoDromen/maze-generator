import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.sql.Time;
import java.util.ArrayList;
// import java.util.Timer;
import java.util.Arrays;

public class DrawingPanel extends JPanel {
    private ArrayList<int[]> drawCells;
    private int squareSize;
    private int offset = 7;
    private int index = 0;
    // private Timer timer = new Timer(500, this);

    public DrawingPanel(ArrayList<int[]> initDrawCells, int squareSize, int offset) {//, ArrayList<String> newRes) {
        this.drawCells = initDrawCells;
        this.squareSize = squareSize;
        this.offset = offset;
        
        // this.squareSize = newSquareSize;
        //this.res = newRes;
        //timer = new Timer(500, this);
    }
    
    
    private void drawBackground(Graphics g) {
        // super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
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

    private void drawCells(Graphics g) {
        g.setColor(Color.BLACK);
        for (int[] cells : drawCells) {
            // if (!checkBoundaries(cells)) {
            //     System.out.println(Arrays.toString(cells));
            // }
            g.fillRect(cells[0], cells[1], squareSize - (offset * 2), squareSize - (offset * 2));
        }
    }

    Boolean checkBoundaries(int[] pos) {
        if (pos[0] < 0 || pos[0] > getHeight()) {
            return false;
        }
        if (pos[1] < 0 || pos[1] > getHeight()) {
            return false;
        }
        return true;
    }

    // void addCells(String currPos, String nextPos, Graphics g) {
    //     String[] currPosSplit = currPos.split(":");
    //     int currXPos = Integer.parseInt(currPosSplit[0]);
    //     int currYPos = Integer.parseInt(currPosSplit[1]);

    //     g.fillRect((currXPos * squareSize) + (offset),
    //             (currYPos * squareSize) + offset,
    //             squareSize - (offset * 2),
    //             squareSize - (offset * 2));        

    //     String[] nextPosSplit = currPos.split(":");
    //     int nextXPos = Integer.parseInt(nextPosSplit[0]);
    //     int nextYPos = Integer.parseInt(nextPosSplit[1]);

    //     int directionX = getDirection(currXPos - nextXPos);
    //     int directionY = getDirection(currYPos - nextYPos);        

    //     g.fillRect((currXPos * squareSize) + (offset + directionX),
    //             (currYPos * squareSize) + (offset + directionY),
    //             squareSize - (offset * 2),
    //             squareSize - (offset * 2));

    //     repaint();
    // }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        // drawGrid(g);
        drawCells(g);
    }
    // @Override
    // protected void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     drawGrid(g);
    //     g.setColor(Color.WHITE);
    //     String[] initPos = res.get(0).split(":");
    //     int preXPos = Integer.parseInt(initPos[0]);
    //     int preYPos = Integer.parseInt(initPos[1]);
    //     for (String cord : res) {
    //         String[] resSplit = cord.split(":");
    //         int x = Integer.parseInt(resSplit[0]);
    //         int y = Integer.parseInt(resSplit[1]);

    //         int directionX = getDirection(x - preXPos);
    //         int directionY = getDirection(y - preYPos);
    //         System.out.println("prePos: " + preXPos + ":" + preYPos);
    //         System.out.println("pos: " + x + ":" + y);
    //         System.out.println(directionX + ":" + directionY);
    //         System.out.println("----------------------------------------");
    //         g.fillRect((preXPos * squareSize) + (offset),
    //                 (preYPos * squareSize) + offset,
    //                 squareSize - (offset * 2),
    //                 squareSize - (offset * 2));

    //         g.fillRect((preXPos * squareSize) + (offset + directionX),
    //                 (preYPos * (squareSize)) + (offset + directionY),
    //                 squareSize - (offset * 2),
    //                 squareSize - (offset * 2));

    //         // g.fillRect((preXPos * squareSize) + (offset),
    //         //         (preYPos * (squareSize)) + (offset + directionY),
    //         //         squareSize - (offset * 2),
    //         //         squareSize - (offset * 2));                    

    //         preXPos = x;
    //         preYPos = y;
            
    //     }
    // }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     index += 1;
    //     if (index < res.size() - 1) {
    //         repaint();
    //     } else {
    //         timer.stop();
    //     }
    // }    

//     private int getDirection(int posDiff) {
//         if (posDiff == 1 || posDiff == -1) {
//             return (int) (squareSize / 2) * posDiff;
//         }
//         return 0;
//     }
// }
}