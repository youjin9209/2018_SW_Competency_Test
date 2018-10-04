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
	static int[][] map;
	static boolean[] biteCheck; // 해당 디저트 종류를 먹었는 지 check 
	static boolean[][] check; // 해당 좌표를 건넜는지 check 
	static int[][] dist; // 먹은 디저트 개수 저장 
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static int dessertSum;
	static int startX;
	static int startY;
	public static void dfs(int x, int y, int idx, int sum) {
		// 1) 종결 조건 : 다시 시작포인트로 돌아왔을 때 
		if (idx == 3 && startX == x && startY == y) {
			if (dessertSum < sum)
				dessertSum = sum;
			return;
		}
		// 2) return 조건 
		if (x < 0 || x >= N || y < 0 || y >= N) return;
		if (biteCheck[map[x][y]]) return;
		if (check[x][y]) return;
		if (idx > 3) return;
		// 3) go 
		biteCheck[map[x][y]] = true;
		if (!(startX == x && startY == y))
			check[x][y] = true;
		int nx = x + dx[idx];
		int ny = y + dy[idx];
		dfs(nx, ny, idx, sum + 1); // 가던 방향으로 쭉 간다 -> 이게 최대가 될 확률이 높으니까 먼저 탐색 
		dfs(nx, ny, idx + 1, sum + 1); // 가던 방향으로 안되면 방향 튼다 
		// 4) return 했을 경우 부모노드 초기화 
		biteCheck[map[x][y]] = false;
		check[x][y] = false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 1. initialize 
			N = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 2. process 
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					check = new boolean[N][N];	
					biteCheck = new boolean[101];
					startX = i; startY = j;
					dfs(i, j, 0, 0);
					dist[i][j] = dessertSum;
					dessertSum = 0;
				}
			}
			int result = 0;
			// 3. get summation 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (result < dist[i][j])
						result = dist[i][j];
				}
			}
			if (result == 0)
				result = -1;
			System.out.println("#"+test_case+" "+result);
		}
	}
}
