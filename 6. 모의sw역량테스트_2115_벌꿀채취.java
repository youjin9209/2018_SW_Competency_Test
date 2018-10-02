/*
문제 링크 
https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
팁 : 각좌표마다 수익 저장하고 안겹치는걸로 2개 뽑기  
*/
import java.util.ArrayList;
import java.util.Scanner;
class ComboInfo {
	int value;
	int idx;
	ComboInfo(int value, int idx) {
		this.value = value;
		this.idx = idx;
	}
}
public class Solution {
	static int N;
	static int M;
	static int C;
	static int[][] map;
	static int[][] d;
	static int[] arr;
	static ArrayList<Integer> al;
	static int Max;
	static ArrayList<Integer> af;
	static ArrayList<ComboInfo> ag;
	static int result;
	public static void getMax(int cnt, int idx, int K) {
		if (cnt == K) {
			ComboInfo first = ag.get(0);
			ComboInfo second = ag.get(1);
			if (Math.abs(first.idx - second.idx) >= M) {
				if (result < first.value + second.value) {
					result = first.value + second.value;
				}
			}
			return;
		}
		if (idx >= N*(N + M - 1)) return;
		ag.add(new ComboInfo(af.get(idx), idx));
		getMax(cnt+1, idx+1, K);
		ag.remove(ag.size() - 1);
		getMax(cnt, idx+1, K);
	}
	/*
 	cnt: 현재 개수 (담고 있는 배열), idx: 지칭하는 배열의 index, K: 뽑아야 하는 개수 
	*/
	public static void combination(int cnt, int idx, int K) {
		if (cnt == K) {
			int sum = 0;
			int powSum = 0;
			for (int x : al) {
				sum += x;
				powSum += Math.pow(x, 2);
			}
			if (sum <= C) {
				if (Max < powSum) {
					Max = powSum;
				}
			}
			return;
		}
		if (idx >= M) return;
		al.add(arr[idx]);
		combination(cnt+1, idx+1, K);
		al.remove(al.size() - 1);
		combination(cnt, idx+1, K);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); 
			M = sc.nextInt();
			C = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			d = new int[N][N];
			// 1) 모든 좌표를 돌면서 최대수익 낼 수 있는거 저장 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j + M <= N) { // 벌통을 끼울 수 있는 영역만 탐색 
						arr = new int[M];
						al = new ArrayList<Integer>();
						for (int m = 0; m < M; m++) {
							arr[m] = map[i][j+m];
						}
						for (int k = 1; k <= M; k++) {
							combination(0, 0, k); // 최대 k개 만큼 뽑기 
							d[i][j] = Max;
						}
						Max = 0;
					}
				}
			}
			// 2) 두개씩 뽑아서 최대 수익 구하기 
			// int[][] -> ArrayList<Integer> : 2차원 -> 1차원 
			// 다른 행이라면 제약사항 없어지니까 각 행에다가 0 을 M-1 개만큼 추가해주면 된다. 
			// 각 행의 맨끝에다가 M-1개만큼 0을 추가해주게 되면 조합 2개 고를때 열의 차이가 M이상 나는 것만 제한해줘도 된다 
			af = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N + M -1; j++) {
					if (j >= 0 && j < N) {
						af.add(d[i][j]);
					} else {
						af.add(0);
					}
				}
			}
			ag = new ArrayList<ComboInfo>();
			getMax(0, 0, 2);
			System.out.println("#"+test_case+" "+result);
			result = 0;
		}
	}
}
