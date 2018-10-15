import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Pair {
	int x, y;
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution {
	static int N;
	static int K;
	static int[][] map;
	static int maxVal;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static boolean findMaxVal;
	public static int bfs1(int startX, int startY) {
		Queue<Pair> queue = new LinkedList<Pair>();
		int[][] dist = new int[N][N];
		queue.add(new Pair(startX, startY));
		dist[startX][startY] = 1;
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x; int y = p.y;
			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (map[nx][ny] < map[x][y]) {
						queue.add(new Pair(nx, ny));
						dist[nx][ny] = dist[x][y] + 1;
					}
				}
			}
		}
		int len = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (len < dist[i][j])
					len = dist[i][j];
			}
		}
		return len;
	}
	public static int bfs2(int startX, int startY) {
		int len = 0;
		Queue<Pair> queue = new LinkedList<Pair>();
		int[][] dist = new int[N][N];
		queue.add(new Pair(startX, startY));
		dist[startX][startY] = 0;
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x; int y = p.y;
			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (map[nx][ny] > map[x][y]) {
						queue.add(new Pair(nx, ny));
						dist[nx][ny] = dist[x][y] + 1;
						if (map[nx][ny] == maxVal) {
							if (len < dist[nx][ny]) {
								len = dist[nx][ny];
								findMaxVal = true;
							}
						}
					}
				}
			}
		}
		return len;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 1) intialize 
			N = sc.nextInt();
			K = sc.nextInt();
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
						// 2-1) 깎는다 
						map[i][j] -= k;
						// 2-2) bfs1() : 깎은 지점에서 자신보다 낮은 봉우리로 탐색 
						int len = 0;
						len = bfs1(i, j);
						// 2-3) bfs2() : 깎은 지점에서 최대 봉우리로 간다 
						int cnt = 0;
						cnt = bfs2(i, j); 
						// 최대 봉우리를 찾았을 때만 갱신 (최대봉우리를 무조건 가야하니까)
						if (findMaxVal && result < len + cnt)
							result = len + cnt;
						// 2-4) 복원 (깎은거랑 최대 봉우리 찾은 플래그) 
						map[i][j] += k;
						findMaxVal = false;
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
			maxVal = 0;
		}
	}
}
