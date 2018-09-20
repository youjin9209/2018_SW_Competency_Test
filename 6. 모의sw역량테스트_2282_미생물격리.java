import java.util.Scanner;
class Point {
	int cnt, dir;
	int maxCnt;
	public void setPoint() {
		cnt = 0; dir = 0; maxCnt = 0;
	}
	public void setPoint(int cnt, int dir, int maxCnt) {
		this.cnt = cnt;
		this.dir = dir;
		this.maxCnt = maxCnt;
	}
	public void setPoint(Point m) {
		this.cnt = m.cnt;
		this.dir = m.dir;
		this.maxCnt = m.maxCnt;
	}
}
public class Solution_2282_미생물격리 {
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	static int N;
	static int M;
	static int K;
	static Point[][] map; // current state 
	static Point[][] next_map; // 1 hours later state 
	public static void move() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].cnt > 0) {
					int dir = map[i][j].dir;
					int nx = i + dx[dir];
					int ny = j + dy[dir];
					// area1 : 약물 발라진곳 
					if (nx == 0 || nx == N-1 || ny == 0 || ny == N-1) {
						next_map[nx][ny].cnt = map[i][j].cnt / 2;
						if (dir == 1) {
							next_map[nx][ny].dir = 2;
						} else if (dir == 2) {
							next_map[nx][ny].dir = 1;
						} else if (dir == 3) {
							next_map[nx][ny].dir = 4;
						} else if (dir == 4) {
							next_map[nx][ny].dir = 3;
						}
					}
					// area2 : 약물 아닌 곳 
					else {
						if (next_map[nx][ny].cnt > 0) { // 다른놈이 미리 와있는 경우 
							next_map[nx][ny].cnt += map[i][j].cnt;
							if (next_map[nx][ny].maxCnt < map[i][j].cnt) {
								next_map[nx][ny].maxCnt = map[i][j].cnt;
								next_map[nx][ny].dir = map[i][j].dir;
							}
						} else {
							next_map[nx][ny].cnt = map[i][j].cnt;
							next_map[nx][ny].maxCnt = map[i][j].cnt;
							next_map[nx][ny].dir = map[i][j].dir;
						}
					}
				}
			}
		}
		// 엎어 치기 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j].setPoint(next_map[i][j]);
				next_map[i][j].setPoint();
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			map = new Point[N][N];
			next_map = new Point[N][N];
			// 1) initialize 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					next_map[i][j] = new Point();
					map[i][j] = new Point();
					map[i][j].setPoint();
				}
			}
			int x, y, cnt, dir;
			for (int k = 1; k <= K; k++) {
				x = sc.nextInt();
				y = sc.nextInt();
				cnt = sc.nextInt();
				dir = sc.nextInt();
				map[x][y].setPoint(cnt, dir, cnt);
			}
			// 2) process 
			for (int i = 0; i < M; i++) {
				move();
			}
			// 3) answer 
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans += map[i][j].cnt;
				}
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
