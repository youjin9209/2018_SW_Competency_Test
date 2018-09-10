import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2105_디저트카페 {
	static int N;
	static int[][] map;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static boolean[][] check;
	static boolean[] dessert;
	static int max;
	static int startX;
	static int startY;
	public static void dfs(int x, int y, int dir, int sum) {
		// 1. 제한조건 check 
		if (x < 0 || x >= N || y < 0 || y >= N) return;
		if (dessert[map[x][y]]) {
			if (!(x == startX && y == startY))
				return;
		}
		if(check[x][y]) return;
		
		// 2. 처음지점 다시 돌아왔을 때 - 최대값 갱신 
		if (dir == 3 && startX == x && startY == y) {
			if (max < sum)
				max = sum;
			return;
		}
		// 3. check[][], dessert, sum 값 
		if (x != startX && y != startY)
			check[x][y] = true;
		dessert[map[x][y]] = true;
		sum += 1;
		// 4. 같은방향으로 go straight (얘가 먼저간다 )
		int nx, ny;
		if (dir < 4) {
			nx = x + dx[dir];
			ny = y + dy[dir];
			dfs(nx, ny, dir, sum);
		}
		// 4. 다른방향으로 go 
		if (dir + 1 < 4) {
			nx = x + dx[dir+1];
			ny = y + dy[dir+1];
			dfs(nx, ny, dir+1, sum);
		}
		// 5. backtracking 해야하니까 false 시켜 
		dessert[map[x][y]] = false;
		check[x][y] = false;
		sum -= 1;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= T; t++) {
			max = -1;
			N = Integer.parseInt(bf.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] token = bf.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token[j]);
				}
			}
			check = new boolean[N][N];
			dessert = new boolean[101];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					startX = i;
					startY = j;
					dfs(i, j, 0, 0);
				}
			}
			System.out.println("#"+t+" "+max);
		}
	}
}
