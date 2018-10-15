import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Pair {
	int x, y;
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution {
	static int K = 20;
	static int N;
	static int M;
	static int[][] map;
	static int[] price = new int[K+2]; // 운영 비용
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	// 4) 손해 안보면서 방문하는 최대 집 개수 return 
	public static int check (int startX, int startY, int k) {
		int cnt = 0;
		// 4-1) BFS로 집 개수 센다 
		Queue<Pair> queue = new LinkedList<Pair>();
		int[][] dist = new int[N][N];
		boolean[][] check = new boolean[N][N];
		queue.add(new Pair(startX, startY));
		dist[startX][startY] = 1;
		if (map[startX][startY] == 1)
			cnt = 1;
		check[startX][startY] = true;
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x; int y = p.y;
			if (dist[x][y] >= k) break;
			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx]; int ny = y + dy[idx];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (!check[nx][ny]) {
						queue.add(new Pair(nx, ny));
						dist[nx][ny] = dist[x][y] + 1;
						check[nx][ny] = true;
						if (map[nx][ny] == 1 && dist[nx][ny] <= k)
							cnt++;
					}
				}
			}
		}
		// 4-2) 손해 안보는거 여부 확인 
		if (cnt*M - price[k] >= 0) return cnt;
		return 0;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		// 1) 운영 비용 계산 
		price[1] = 1;
		for (int i = 2; i <= K+1; i++) {
			price[i] = i*i + (i-1)*(i-1);
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			// 2) input 
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 3) 좌표 탐색 
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 각 좌표마다 K를 최대한 늘릴수 있을만큼 늘리면서 탐색 : 다 덮는 케이스가 있으니까 k <= N+1 
					for (int k = 1; k <= N+1; k++) {
						ans = Math.max(ans, check(i, j, k));
					}
				}
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
