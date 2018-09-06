import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
class Pair {
	int x, y;
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Solution {
	static int N;
	static int M;
	static int L;
	static int[][] map = new int[50][50];
	static int[][] dist = new int[50][50];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static Queue<Pair> queue = new LinkedList<Pair>();
	public static boolean up (int x, int y) {
		if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 7)
			return true;
		else 
			return false;
	}
	public static boolean down (int x, int y) {
		if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 6)
			return true;
		else 
			return false;
	}
	public static boolean left (int x, int y) {
		if (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 7)
			return true;
		else 
			return false;
	}
	public static boolean right (int x, int y) {
		if (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5)
			return true;
		else 
			return false;
	}
	public static int bfs (int startX, int startY) {
		int result = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], 0);
		}
		Pair s = new Pair(startX, startY);
		queue.add(s);
		dist[startX][startY] = 1;
		
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x; int y = p.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				Pair n = new Pair(nx, ny);
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (map[nx][ny] == 0) continue;
					if (dist[nx][ny] != 0) continue;
					if (up(x, y)) { // 위방향이 뚫려 있으면 위에만 보면 된다 
						if (i == 0) { // 다음이 위 칸일 때 
							if (down(nx, ny)) { // 다음칸이 아래가 뚫려 있어야 함 
								queue.add(n);
								dist[nx][ny] = dist[x][y] + 1;
							}
						}
					}
					if (down(x, y)) {
						if (i == 1) {
							if (up(nx, ny)) {
								queue.add(n);
								dist[nx][ny] = dist[x][y] + 1;
							}
						}
					}
					if (right(x, y)) {
						if (i == 2) {
							if (left(nx, ny)) {
								queue.add(n);
								dist[nx][ny] = dist[x][y] + 1;
							}
						}
					}
					if (left(x, y)) {
						if (i == 3) {
							if (right(nx, ny)) {
								queue.add(n);
								dist[nx][ny] = dist[x][y] + 1;
							}
						}
					}
				}
				n = null;
			}
		}
		for (int p = 1; p <= L; p++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (dist[i][j] == p)
						result++;
				}
			}
		}
		s = null;
		return result;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= T; t++) {
			String[] token = bf.readLine().split(" "); 
			N = Integer.parseInt(token[0]); // 세로 
			M = Integer.parseInt(token[1]); // 가로 
			int x = Integer.parseInt(token[2]); // 맨홀 x 좌표 
			int y = Integer.parseInt(token[3]); // 맨홀 y 좌표 
			L = Integer.parseInt(token[4]); // 소요 시간 
			for (int i = 0; i < N; i++) {
				String[] line = bf.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(line[j]);
				}
			}
			int result = 0;
			result = bfs(x, y);
			System.out.println("#"+t+" "+result);
		}
	}
}
