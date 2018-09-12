/*
문제 링크 
https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
팁 : 각좌표마다 수익 저장하고 
안겹치는걸로 2개 뽑기  
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class ComboInfo {
	int value, index;
	ComboInfo (int value, int index) {
		this.value = value;
		this.index = index;
	}
}
public class Solution_2115_벌꿀채취 {
	static int N;
	static int M;
	static int C;
	static int[] a;
	static ArrayList<Integer> hlist = new ArrayList<>();
	static ArrayList<ComboInfo> flist = new ArrayList<ComboInfo>();
	static int retMax;
	static int[][] dist;
	static int finalMax;
	public static void go(int index, int cnt, int K) {
		int result = 0;
		int arrSum = 0;
		if (cnt == K) {
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
	public static void chooseMax (int index, int cnt, int K) {
		if (cnt == K) {
			ComboInfo first = flist.get(0);
			ComboInfo second = flist.get(1);
			if (Math.abs(first.index - second.index) >= M) {
				if (finalMax < first.value + second.value) {
					finalMax = first.value + second.value;
				}
			}
			return;
		}
		if (N*N+(M-1)*N == index) return;
		if (a[index] != 0) {
			flist.add(new ComboInfo(a[index], index));
			chooseMax(index + 1, cnt + 1, K);
			flist.remove(flist.size() - 1);
		}
		chooseMax(index + 1 , cnt, K);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= T; t++) {
			String[] token = bf.readLine().split(" ");
			N = Integer.parseInt(token[0]);
			M = Integer.parseInt(token[1]);
			C = Integer.parseInt(token[2]);
			int[][] map = new int[N][N];
			dist = new int[N][N+M-1];
			a = new int[M];
			for (int i = 0; i < N; i++) {
				String[] line = bf.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
				}
			}
			// 1) dist[][] : 각 좌표에서 최대 채취할 수 있는 거 정리 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (M+j-1 < N) {
						int idx = 0;
						for (int p = j; p <= M+j-1; p++) {
							a[idx++] = map[i][p];
						}
						retMax = 0;
						for (int c = 1; c <= M; c++) {
							go(0, 0, c);
							dist[i][j] = retMax;
						}
					}
				}
			}
			int result = 0;
			// 2) 겹치지 않는 2개 pick 
			int idx = 0;
			a = new int[N*N+(M-1)*N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N+M-1; j++) {
					a[idx++] = dist[i][j];
				}
			}
			finalMax = 0;
			chooseMax(0, 0, 2);
			result = finalMax;
			System.out.println("#"+t+" "+result);
		}
	}
}
