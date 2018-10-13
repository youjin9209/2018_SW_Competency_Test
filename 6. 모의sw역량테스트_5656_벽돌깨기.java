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
	// dfs : go() 에서 정한 위치에서 쐈을 때 깨지는 벽돌의 수 return 
	public static int dfs(int x, int y, int depth) {
		// 1. 처음 깰거 시작 
		int siz = map[depth][x][y];
		map[depth][x][y] = 0;
		int ans = 1;
		// 2. 깨러 간다 
		for (int i = 0; i < siz; i++) {
			for (int j = 0; j < 4; j++) {
				int nx = x + i*dx[j];
				int ny = y + i*dy[j];
				if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
					if (map[depth][nx][ny] == 0) continue;
					ans += dfs(nx, ny, depth);
				}
			}
		}
		return ans;
	}
	// cpy : 현재 depth 의 시뮬레이션을 위해 이전 차수의 맵을 배껴온다 
	public static void cpy (int depth) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[depth][i][j] = map[depth-1][i][j];
			}
		}
	}
	// drop : 한판 깨고 난 후 , 벽돌 정렬 
	public static void drop(int depth) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int j = 0; j < W; j++) {
			for (int i = H-1; i >= 0; i--) { // 아래에서 부터 
				if (map[depth][i][j] > 0) { // 0이 아닌걸 찾아서 큐에 넣고 0 으로 만듬 
					queue.add(map[depth][i][j]);
					map[depth][i][j] = 0;
				}
			}
			int i = H-1;
			while (!queue.isEmpty()) {
				map[depth][i][j] = queue.remove(); // 빼서 아래에서 부터 넣어준다 
				i--;
			}
		}
	}
	// go(depth) : 어느 width에서 쏠지 (0 ~ W-1 사이) 정한다. 최대한 많이 깨졌을 때의 수 return 
	public static int go (int depth) {
		int ans = 0;
		// 1) terminate condition
		if (depth > N)
			return 0;
		// 2) go 
		for (int j = 0; j < W; j++) {
			cpy(depth);
			int brokenCnt = 0;
			boolean findToBreak = false;
			for (int i = 0; i < H; i++) {
				if (map[depth][i][j] > 0) { // 깰 벽돌을 찾음 
					findToBreak = true;
					brokenCnt = dfs(i, j, depth);
					break;
				}
			}
			if (!findToBreak) continue; // 못찾음 -> 다음 열로 넘어가 
			drop(depth); // 깨트린거 정렬하러 간다 
			if (ans < go(depth+1) + brokenCnt)
				ans = go(depth+1) + brokenCnt;
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
					if (map[0][i][j] > 0)
						sum++;
				}
			}
			int ans = sum - go(1);
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
