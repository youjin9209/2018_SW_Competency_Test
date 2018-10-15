import java.util.ArrayList;
import java.util.Scanner;

class CCTV {
	public int x, y, type, dir;
	CCTV (int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.dir = 0; // 0: 0, 1: 90, 2: 180, 3: 270 
	}
}
public class bf_5_4_감시_15683 {
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상  
	static int[] dy = {1, 0, -1, 0};
	static int[][] map;
	static ArrayList<CCTV> cctv;
	static int N;
	static int M;
	static void check(int[][] temp, int x, int y, int dir) {
		int ni = x; int nj = y;
		while (ni >= 0 && ni < N && nj >= 0 && nj < M) {
			if (map[ni][nj] == 6) break;
			temp[ni][nj] = map[x][y];
			ni += dx[dir];
			nj += dy[dir];
		}
	}
	static int go(int index) {
		if (index == cctv.size()) {
			// 1) map 복사 
			int[][] temp = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					temp[i][j] = map[i][j];
				}
			}
			for (CCTV c : cctv) {
				int what = c.type;
				int x = c.x; int y = c.y;
				int dir = c.dir;
				if (what == 1) {
					check(temp, x, y, dir);
				} else if (what == 2) {
					check(temp, x, y, dir);
					check(temp, x, y, (dir+2)%4);
				} else if (what == 3) {
					check(temp, x, y, dir);
					check(temp, x, y, (dir+1)%4);
				} else if (what == 4) {
					check(temp, x, y, dir);
					check(temp, x, y, (dir+1)%4);
					check(temp, x, y, (dir+2)%4);
				} else if (what == 5) {
					check(temp, x, y, dir);
					check(temp, x, y, (dir+1)%4);
					check(temp, x, y, (dir+2)%4);
					check(temp, x, y, (dir+3)%4);
				}
			}
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (temp[i][j] == 0)
						cnt++;
				}
			}
			return cnt;
		}
		int ans = 100;
		for (int i = 0; i < 4; i++) {
			cctv.get(index).dir = i;
			int temp = go(index + 1);
			if (ans > temp)
				ans = temp;
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 세로 
		M = sc.nextInt(); // 가로 
		map = new int[N][M];
		cctv = new ArrayList<CCTV>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					cctv.add(new CCTV(i, j, map[i][j]));
				}
			}
		}
		int ans = go(0);
		System.out.println(ans);
	}
}
