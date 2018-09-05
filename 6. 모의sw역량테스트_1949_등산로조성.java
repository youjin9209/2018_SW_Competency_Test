import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
class Pair {
	int x, y;
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class 모의sw역량테스트_1949_등산로조성 {
	static int[][] map;
	static int N;
	static int K;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static int bfs(int startX, int startY) {
		int[][] dist = new int[N][N];
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(startX, startY));
		dist[startX][startY] = 1;
		int result = 0;
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x;
			int y = p.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (map[nx][ny] >= map[x][y]) continue;
					queue.add(new Pair(nx, ny));
					dist[nx][ny] = dist[x][y] + 1;
					if (result < dist[nx][ny])
						result = dist[nx][ny];
				}
			}
		}
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= T; t++) {
			String[] line = bf.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			K = Integer.parseInt(line[1]);
			map = new int[N][N];
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				String[] token = bf.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token[j]);
					if (max <= map[i][j]) {
						max = map[i][j];
					}
				}
			}
			int result = 0;
			// 다깎아 놓고 시작 
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int h = 0; h <= K; h++) {
						map[i][j] -= h; // 깎고 
						cnt = bfs(i, j);
						// (bfs돌린 x,y : cnt) + (최고 높이 봉우리 까지 : maxAdd)
						int[][] d = new int[N][N];
						Queue<Pair> queue = new LinkedList<Pair>();
						queue.add(new Pair(i, j));
						int maxAdd = Integer.MIN_VALUE;
						while (!queue.isEmpty()) {
							Pair p = queue.remove();
							for (int idx = 0; idx < 4; idx++) {
								int nx = p.x + dx[idx];
								int ny = p.y + dy[idx];
								if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
									if (map[nx][ny] > map[p.x][p.y]) {
										d[nx][ny] = d[p.x][p.y] + 1;
										queue.add(new Pair(nx, ny));
										if (map[nx][ny] == max) {
											if (maxAdd < d[nx][ny])
												maxAdd = d[nx][ny];
										}
									}
								}
							}
						}
						if (cnt == 0)
							cnt++;
						cnt += maxAdd;
						if (result < cnt)
							result = cnt;
						map[i][j] += h; //깎은거 초기화 
					}
				}
			}
			System.out.println("#"+t+" "+result);
		}
	}
}
