import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class CCTV {
	int x, y, type, dir;
	CCTV(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.dir = 0;
	}
}
public class Main {
	static int N; // 세로 
	static int M; // 가 
	static int[][] map;
	static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<CCTV> cctv; 
	/* 
	check() : 탐색하면서 자기영역 표시 (0을 지워나감 )
	*/
	static void check(int[][] temp, int x, int y, int dir) {
		int ni = x; int nj = y;
		while (ni >= 0 && ni < N && nj >= 0 && nj < M) {
			if (map[ni][nj] == 6) break;
			temp[ni][nj] = map[x][y]; // 영역 표시 
			ni += dx[dir];
			nj += dy[dir];
		}
	}
	/*
	go(idx):  탐색 하고 0 개수 return 
	*/
	static int go(int idx) {
		// 1) terminate condition 
		if (idx == cctv.size()) {
			// map복사 
			int[][] temp = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					temp[i][j] = map[i][j];
				}
			}
			// 탐색하러 간다 
			for (CCTV c : cctv) {
				int what = c.type;
				int x = c.x; int y = c.y;
				int dir = c.dir;
				if (what == 1) {
					check(temp, x, y, dir);
				} else if (what == 2) {
					check(temp, x, y, dir);
					check(temp, x, y, (dir+2)%4); // 180 
				} else if (what == 3) {
					check(temp, x, y, dir);
					check(temp, x, y, (dir+1)%4); // 90
				} else if (what == 4) {
					check(temp, x, y, dir);
					check(temp, x, y, (dir+1)%4); // 90
					check(temp, x, y, (dir+2)%4); // 180 
				} else if (what == 5) {
					check(temp, x, y, dir);
					check(temp, x, y, (dir+1)%4); // 90
					check(temp, x, y, (dir+2)%4); // 180 
					check(temp, x, y, (dir+3)%4); // 270 
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
		// 2) go
		int ans = 100;
		// i-> 0: 우, 1: 하, 2: 좌, 3: 상 
		for (int i = 0; i < 4; i++) {
			cctv.get(idx).dir = i;
			int temp = go(idx + 1);
			if (ans > temp)
				ans = temp;
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
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
