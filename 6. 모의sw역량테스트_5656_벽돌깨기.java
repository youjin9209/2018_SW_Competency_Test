import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_5656_벽돌깨기 {
	static int N;
	static int W;
	static int H;
	static int[][][] map; 
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	// dfs : run() 에서 정한 위치에서 쐈을 때 얼마나 깨지는지 시뮬레이션 하고 깨지는 벽돌의 수 return ;
	public static int dfs(int x, int y, int depth) {
		int siz = map[depth][x][y];
		int ans = 1;
		map[depth][x][y] = 0;
		for (int i = 0; i < siz; i++) {
			for (int j = 0; j < 4; j++) {
				int nx = x + i*dx[j];
				int ny = y + i*dy[j];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[depth][nx][ny] == 0) continue;
				ans += dfs(nx, ny, depth);
			}
		}
		return ans;
	}
	// cpy : 현재 depth 의 시뮬레이션을 위해 이전 depth 의 맵을 배껴온다  
	public static void cpy (int depth) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[depth][i][j] = map[depth-1][i][j];
			}
		}
	}
	// drop : dfs() 한 후, 벽돌 정렬 
	public static void drop(int depth) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int j = 0; j < W; j++) {
			for (int i = H-1; i >= 0; i--) {
				if (map[depth][i][j] > 0) {
					queue.add(map[depth][i][j]);
					map[depth][i][j] = 0;
				}
			}
			for (int i = H - 1; !queue.isEmpty(); i--) {
				map[depth][i][j] = queue.peek();
				queue.remove();
			}
		}
	}
	// run : 0 ~ W-1 사이에서 어디에 쏠지 정한다. 최대한 많이 깨졌을 때의 수 return 
	public static int run (int depth) {
		if (depth > N) return 0;
		int ans = 0;
		for (int j = 0; j < W; j++) {
			cpy(depth);
			int temp = 0;
			boolean f = true;
			for (int i = 0; i < H; i++) {
				if (map[depth][i][j] > 0) {
					f = false;
					temp = dfs(i, j, depth);
					break;
				}
			}
			if (f) continue;
			drop(depth);
			ans = Math.max(ans,  run(depth+1) + temp);
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		map = new int[5][15][12];
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			int sum = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					map[0][i][j] = sc.nextInt();
					sum += map[0][i][j] > 0 ? 1 : 0;
				}
			}
			int ans = sum - run(1);
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
