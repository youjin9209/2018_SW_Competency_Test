import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Point {
	int x, y;
	Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution {
	static int N;
	static int K;
	static int[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static int bfs1(int i, int j) {
		int result = 0;
		int[][] dist = new int[N][N];
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j));
		dist[i][j] = 1;
		while (!queue.isEmpty()) {
			Point p = queue.remove();
			int x = p.x; int y = p.y;
			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (map[nx][ny] >= map[x][y]) continue;
					queue.add(new Point(nx, ny));
					dist[nx][ny] = dist[x][y] + 1;
					if (result < dist[nx][ny])
						result = dist[nx][ny];
				}
			}
		}
		return result;
	}
	public static int bfs2(int i, int j, int maxVal) {
		int result = Integer.MIN_VALUE;
		int[][] dist = new int[N][N];
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j));
		dist[i][j] = 0;
		while (!queue.isEmpty()) {
			Point p = queue.remove();
			int x = p.x; int y = p.y;
			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (map[nx][ny] <= map[x][y]) continue;
					queue.add(new Point(nx, ny));
					dist[nx][ny] = dist[x][y] + 1;
					if (map[nx][ny] == maxVal) {
						if (result < dist[nx][ny])
							result = dist[nx][ny];
					}
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			int maxVal = Integer.MIN_VALUE;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (maxVal < map[i][j])
						maxVal = map[i][j];
				}
			}
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k <= K; k++) {
						map[i][j] -= k; // 깎고 
						// 1) 깍은 지점에서 시작하여 최소 높이지점 까지 탐색 - bfs1
						int len1 = bfs1(i, j);
						if (len1 == 0)  // 깎은 지점이 높이가 최소지점일 때 (탐색 못했을 때 )
							len1++;
						// 2) 깍은 지점에서 시작해 최대 높이 지점까지 탐색 (maxVal까지) - bfs2
						int len2 = bfs2(i, j, maxVal);
						if (result < len1 + len2)
							result = len1 + len2;
						map[i][j] += k;// 복구 
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}
	}
}
