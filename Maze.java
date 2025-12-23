import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Maze {

    public static void main(String[] args) {

        int squareSize = 10;

        HashMap<String, String[]> adjacent = new HashMap<String, String[]>();
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        for (int cols = 0; cols < squareSize; cols++) {
            for (int rows = 0; rows < squareSize; rows++) {
                String key = getCord(cols, rows);
                visited.put(key, false);
                adjacent.put(key, getAdjacent(cols, rows, squareSize).clone());
            }
        }

        Random rand = new Random();
        String cursor = getCord(rand.nextInt(squareSize), rand.nextInt(squareSize));

        ArrayList<String> res = new ArrayList<String>();
        dfs(adjacent, visited, cursor, res);

        for (String i : res) {
            System.out.print(i + " " + visited.get(i));
        }

    }

    static String getCord(int xPos, int yPos) {
        return String.valueOf(xPos + ":" + yPos);
    }

    static String[] getAdjacent(int xPos, int yPos, int maxValue) {
        ArrayList<String> cordValues = new ArrayList<String>();
        if (xPos > 0)
            cordValues.add(getCord(xPos - 1, yPos));
        if (xPos < maxValue - 1)
            cordValues.add(getCord(xPos + 1, yPos));
        if (yPos > 0)
            cordValues.add(getCord(xPos, yPos - 1));
        if (yPos < maxValue - 1)
            cordValues.add(getCord(xPos, yPos + 1));

        String[] adjacent = new String[cordValues.size()];
        for (int i = 0; i < adjacent.length; i++) {
            adjacent[i] = cordValues.get(i);
        }

        return adjacent;
    }

    static void dfs(HashMap<String, String[]> adjacent, HashMap<String, Boolean> visited, String cursor,
            ArrayList<String> res) {
        res.add(cursor);
        visited.put(cursor, true);

        for (String i : adjacent.get(cursor)) {
            if (!visited.get(i)) {
                dfs(adjacent, visited, i, res);
            }
        }
    }
}