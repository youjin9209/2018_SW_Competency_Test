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
			for (int m = 1; m <= M; m++) {
				for (int k = 0; k <= K-1; k++) {
					MicroInfo mi = a.get(k);
					int nx = mi.x + dx[mi.dir];
					int ny = mi.y + dy[mi.dir];
					mi.x = nx;
					mi.y = ny;
					// 3) 약품 셀에 왔을 경우 - 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀜 
					if (map[nx][ny] == 1) {
						int ncnt = (mi.cnt)/2;
						mi.cnt = ncnt;
						if (ncnt == 0) {
							mi.dir = 0;
						} else {
							if (mi.dir == 1) mi.dir = 2;
							else if (mi.dir == 2) mi.dir = 1;
							else if (mi.dir == 3) mi.dir = 3;
							else if (mi.dir == 4) mi.dir = 4;
						}
					} 
				}
				// 4) 두개 이상의 군집이 한 셀에 모일 경우 
				// 미생물 수 : sum  / 이동 방향 : max 군집의 이동방향 
				for (MicroInfo mi : a) {
					System.out.println(mi.x + " " + mi.y);
				}
			}
		}
	}
}
