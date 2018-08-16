/*
문제링크
https://www.acmicpc.net/problem/14500
해결 : 경우의 수가 적으므로 다해본다.
총 19 가지 !
 * */
import java.util.Scanner;

public class bf_3_4_테트로미노_14500 {
	static int N;
	static int M;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 세로 
		M = sc.nextInt(); // 가로 
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
 		}
		int result = 0; // max 값 갱신 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// case1 : → → →
				if (j+3 < M) {
					int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
					if (result < sum)
						result = sum;
				}
				// case2 : ↓ ↓ ↓
				if (i+3 < N) {
					int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j];
					if (result < sum)
						result = sum;
				}
				// case3 : → ↓ ←
				if (i+1 < N && j+1 < M) {
					int sum = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+1][j];
					if (result < sum)
						result = sum;
				}
				// case4 : ↓ ↓ →
				if (i+2 < N && j+1 < M) {
					int sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+2][j+1];
					if (result < sum)
						result = sum;
				}
				// case5:  → → ↑
				if (i-1 >=0 && j+2 < M) {
					int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i-1][j+2];
					if (result < sum)
						result = sum;
				}
				// case6:  → ↓ ↓
				if (i+2 < N && j+1 < M) {
					int sum = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+2][j+1];
					if (result < sum)
						result = sum;
				}
				// case7:  ↑ → →
				if (i-1 >= 0 && j+2 < M) {
					int sum = map[i][j] + map[i-1][j] + map[i-1][j+1] + map[i-1][j+2];
					if (result < sum)
						result = sum;
				}
				// case8:  ← ↓ ↓
				if (i+2 < N && j-1 >= 0) {
					int sum = map[i][j] + map[i][j-1] + map[i+1][j-1] + map[i+2][j-1];
					if (result < sum)
						result = sum;
				}
				// case9:  → → ↓
				if (i+1 < N && j+2 < M) {
					int sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+2];
					if (result < sum)
						result = sum;
				}
				// case10: → ↑ ↑
				if (i-2 >= 0 && j+1 < M) {
					int sum = map[i][j] + map[i][j+1] + map[i-1][j+1] + map[i-2][j+1];
					if (result < sum)
						result = sum;
				}
				// case11: ↓ → →
				if (i+1 < N && j+2 < M) {
					int sum = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+1][j+2];
					if (result < sum)
						result = sum;
				}
				// case12: ↓ → ↓
				if (i+2 < N && j+1 < M) {
					int sum = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j+1];
					if (result < sum)
						result = sum;
				}
				// case13: → ↑ → 
				if (i-1 >= 0 && j+2 < M) {
					int sum = map[i][j] + map[i][j+1] + map[i-1][j+1] + map[i-1][j+2];
					if (result < sum)
						result = sum;
				}
				// case14: ↓ ← ↓
				if (i+2 < N && j-1 >= 0) {
					int sum = map[i][j] + map[i+1][j] + map[i+1][j-1] + map[i+2][j-1];
					if (result < sum)
						result = sum;
				}
				// case15: → ↓ →
				if (i+1 < N && j+2 < M) {
					int sum = map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+1][j+2];
					if (result < sum)
						result = sum;
				}
				if (j+2 < M) {
					int sum1 = map[i][j] + map[i][j+1] + map[i][j+2];
					// case16: ㅜ
					if (i+1 < N) { 
						int sum2 = sum1 + map[i+1][j+1];
						if (result < sum2)
							result = sum2;
					}
					// case17: ㅗ 
					if (i-1 >= 0) {
						int sum2 = sum1 + map[i-1][j+1];
						if (result < sum2)
							result = sum2;
					}
				}
				if (i+2 < N) {
					int sum1 = map[i][j] + map[i+1][j] + map[i+2][j];
					// case18: ㅏ 
					if (j+1 < M) { 
						int sum2 = sum1 + map[i+1][j+1];
						if (result < sum2)
							result = sum2;
					}
					// case19: ㅓ 
					if (j-1 >= 0) {
						int sum2 = sum1 + map[i+1][j-1];
						if (result < sum2)
							result = sum2;
					}
				}
			}
		}
		System.out.println(result);
	}
}
