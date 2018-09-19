import java.util.ArrayList;
import java.util.Scanner;

class MicroInfo {
	int x, y, cnt, dir;
	MicroInfo (int x, int y, int cnt, int dir) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.dir = dir;
	}
}
class Point {
	int x, y;
	Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution_2282_미생물격리 {
	static int N;
	static int M;
	static int K;
	static ArrayList<MicroInfo> a;
	static int[] dx = {0, -1, 1, 0, 0}; // 1: 상 2 : 하  
	static int[] dy = {0, 0, 0, -1, 1}; // 3: 좌 4 : 우
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			a = new ArrayList<MicroInfo>();
			// 1) input & initialize 
			for (int k = 1; k <= K; k++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int cnt = sc.nextInt();
				int dir = sc.nextInt();
				a.add(new MicroInfo(x, y, cnt, dir));
			}
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == 0 || j == 0 || i == N-1 || j == N-1)
						map[i][j] = 1; // 약품칠하기 
				}
			}
			// 2) process 
			int[][] dist = new int[N][N];
			for (int m = 1; m <= M; m++) {
				for (int k = 0; k <= K-1; k++) {
					MicroInfo mi = a.get(k);
					int nx = mi.x + dx[mi.dir];
					int ny = mi.y + dy[mi.dir];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N) { 
						dist[nx][ny]++;
						mi.x = nx;
						mi.y = ny;
						// 3) 약품 셀에 왔을 경우 - 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀜 
						if (map[nx][ny] == 1) {
							int ncnt = (mi.cnt)/2;
							mi.cnt = ncnt;
							if (mi.cnt == 0) {
								mi.dir = 0;
							} else {
								if (mi.dir == 1) mi.dir = 2;
								else if (mi.dir == 2) mi.dir = 1;
								else if (mi.dir == 3) mi.dir = 3;
								else if (mi.dir == 4) mi.dir = 4;
							}
						}
					}
				}
				// 4) 두개 이상의 군집이 한 셀에 모일 경우 
				// 미생물 수 : sum  / 이동 방향 : max 군집의 이동방향 
				ArrayList<Point> p = new ArrayList<Point>();
				for (int i = 1; i <= N-2; i++) {
					for (int j = 1; j <= N-2; j++) {
						if (dist[i][j] > 1) {
							p.add(new Point(i, j));
						}
					}
				}
				int len = a.size();
				for (Point pi : p) {
					int sum = 0, max = 0, maxIdx = 0;
					for (int i = 0; i < len; i++) {
						if (a.get(i).x == pi.x && a.get(i).y == pi.y) {
							sum += a.get(i).cnt;
							if (max < a.get(i).cnt) {
								max = a.get(i).cnt;
								maxIdx = i;
							}
						}
					}
					for (int i = 0; i < len; i++) {
						if (a.get(i).x == pi.x && a.get(i).y == pi.y) {
							if (i == maxIdx) {
								a.get(i).cnt = sum;
							} else {
								a.get(i).cnt = 0;
								a.get(i).dir = 0;
							}
						}
					}
				}
			}
			int ans = 0;
			int len = a.size();
			for (int i = 0; i < len; i++) {
				ans += a.get(i).cnt;
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
