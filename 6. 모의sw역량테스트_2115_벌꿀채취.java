/*
문제 링크 
https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution_2115_벌꿀채취 {
	static int[] a;
	static int M;
	static int C;
	//static int K;
	static ArrayList<Integer> hlist = new ArrayList<>();
	static int retMax;
	public static void go(int index, int cnt, int K) {
		int result = 0;
		int arrSum = 0;
		if (cnt == K) {
			// 제곱의 합 최대값 갱신 
			for (int num : hlist) {
				arrSum += num;
			}
			if (arrSum <= C) {
				for (int num : hlist) {
					result += Math.pow(num, 2);
				}
			}
			if (retMax < result)
				retMax = result;
		}
		if (M == index) return;
		hlist.add(a[index]);
		go(index + 1, cnt + 1, K);
		hlist.remove(hlist.size() - 1);
		go(index + 1 , cnt, K);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= T; t++) {
			String[] token = bf.readLine().split(" ");
			int N = Integer.parseInt(token[0]);
			M = Integer.parseInt(token[1]);
			C = Integer.parseInt(token[2]);
			int[][] map = new int[N][N];
			a = new int[M];
			for (int i = 0; i < N; i++) {
				String[] line = bf.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
				}
			}
			int startAx = 0, startAy = 0;
			int startBx = 0, startBy = 0;
			int maxA = 0, maxB = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// A
					if (M+j-1 < N) {
						startAx = i; startAy = j;
						int sum = 0;
						int idx = 0;
						for (int p = j; p <= M+j-1; p++) {
							a[idx++] = map[i][p];
						}
						for (int c = 1; c <= M; c++) {
							go(0, 0, c);
							if (maxA < retMax)
								maxA = retMax;
						}
					}
					// B
//					for (int k = i; k < N; k++) {
//						for (int l = 0; l < N; l++) {
//							if (l < N && l > M+j-1) {
//								startBx = k; startBy = l;
//							}
//							
//						}
//					}
				}
			}
		}
	}
}
