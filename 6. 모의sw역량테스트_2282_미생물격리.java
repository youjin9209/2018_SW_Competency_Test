import java.util.ArrayList;
import java.util.Scanner;
class MicroInfo {
	int x, y, cnt, dir;
	boolean dead;
	MicroInfo (int x, int y, int cnt, int dir, boolean dead) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.dir = dir;
		this.dead = dead;
	}
}
public class Solution_2282_미생물격리 {
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			ArrayList<MicroInfo> a = new ArrayList<MicroInfo>();
			for (int k = 1; k <= K; k++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int cnt = sc.nextInt();
				int dir = sc.nextInt();
				a.add(new MicroInfo(x, y, cnt, dir, false));
			}
            int len = a.size();
			for (int m = 1; m <= M; m++) {
				for (int i = 0; i < len; i++) {
					MicroInfo mi = a.get(i);
					if (!mi.dead) {
						mi.x += dx[mi.dir];
						mi.y += dy[mi.dir];
						if (mi.x == 0 || mi.y == 0 || mi.x == N-1 || mi.y == N-1) {
							mi.cnt /= 2;
							if (mi.cnt == 0) {
								mi.dead = true;
							} else {
								if (mi.dir == 1) mi.dir = 2;
								else if (mi.dir == 2) mi.dir = 1;
								else if (mi.dir == 3) mi.dir = 4;
								else if (mi.dir == 4) mi.dir = 3;
							}
						}
					}
				}
				for (int i = 0; i < len; i++) {
					ArrayList<Integer> mf = new ArrayList<Integer>();
					mf.add(i);
					MicroInfo mi = a.get(i);
					if (!mi.dead) {
						if (mi.x == 0 || mi.x == N-1 || mi.y == 0 || mi.y == N-1) continue;
						int sum = mi.cnt;
						int max = mi.cnt;
						int maxIdx = i;
						for (int j = 0; j < len; j++) {
							if (i != j) {
								MicroInfo mj = a.get(j);
								if (!mj.dead) {
									if (mj.x == 0 || mj.x == N-1 || mj.y == 0 || mj.y == N-1) continue;
									if (mi.x == mj.x && mi.y == mj.y) {
										mf.add(j);
										sum += mj.cnt;
										if (max < mj.cnt) {
											max = mj.cnt;
											maxIdx = j;
										} 
									}
								}
							}
						}
						for (int op : mf) {
							if (!a.get(op).dead) {
								if (op == maxIdx) {
									a.get(op).cnt = sum;
								} else {
									a.get(op).dead = true;
								}
							}
						}
					}
				}
			}
			int ans = 0;
			for (int i = 0; i < len; i++) {
				if (!a.get(i).dead)
					ans += a.get(i).cnt;
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
