import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBlock extends Block {

    private boolean[][] randomCells;
    private int maxRows;
    private int maxColumns;

    public RandomBlock(int maxRows, int maxColumns) {
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
        super(rows, columns, cells, rotateCenterRow, rotateCenterColumn);
    }

    private void initialRandomCells() {
        Random random = new Random();
        while (true) {
            randomCells = new boolean[maxRows][maxColumns];
            for (int i = 0; i < maxRows; i++) {
                for (int j = 0; j < maxColumns; j++) {
                    randomCells[i][j] = random.nextBoolean();
                }
            }
            if (connected()) {
                return;
            }
        }
    }



    private boolean connected() {
        boolean[][] searched = new boolean[maxRows][maxColumns];

        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxColumns; j++) {
                searched[i][j] = false;
            }
        }

        int blocks = 0;
        List<int[]> queue = new ArrayList<>();
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxColumns; j++) {
                if (!searched[i][j] && randomCells[i][j]) {

                    queue.add(new int[]{i, j});
                    while(!queue.isEmpty()) {
                        int[] pos = queue.remove(0);
                        searched[pos[0]][pos[1]] = true;
                        if (pos[0] > 0 && !searched[pos[0] - 1][pos[1]]) {
                            queue.add(new int[]{pos[0] - 1, pos[1]});
                        }
                        if (pos[0] < maxRows - 1 && !searched[pos[0] + 1][pos[1]]) {
                            queue.add(new int[][])
                        }
                    }
                }
            }
        }

    }
}
