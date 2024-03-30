import java.util.ArrayDeque;
import java.util.Queue;

public class LeastTimeToReachDestination {

    public static int leastTimeToReachDestination(int[][] grid) {
        int n = grid.length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Perform BFS
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                if (x == n - 1 && y == n - 1) {
                    return time;
                }
                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n && !visited[newX][newY] && grid[newX][newY] <= time) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            time++;
        }
        return -1; // If destination cannot be reached
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}
        };
        System.out.println(leastTimeToReachDestination(grid)); // Output should be 16
    }
}
