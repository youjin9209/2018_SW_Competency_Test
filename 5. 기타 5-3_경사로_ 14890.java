/*
문제 링크 
https://www.acmicpc.net/problem/14890
*/
import java.util.Scanner;

public class bf_5_3_경사로_14890 {
	static int N;
	static int L;
	static int[][] map;
	static boolean[][] rowFlag;
	static boolean[][] colFlag;
	public static boolean isRowDecending(int i, int j) {
		for (int p = 0; p < L; p++) {
			if (j+p < N && (map[i][j] != map[i][j+p] || rowFlag[i][j+p]))
				return false;
		}
		return true;
	}
	public static boolean isRowAsending(int i, int j) {
		for(int p = 0; p < L; p++) {
			if (j-p >= 0 && (map[i][j] != map[i][j-p] || rowFlag[i][j-p]))
				return false;
		}
		return true;
	}
	public static boolean isColDecending(int i, int j) {
		for (int p = 0; p < L; p++) {
			if (i+p < N && (map[i][j] != map[i+p][j] || colFlag[i+p][j]))
				return false;
		}
		return true;
	}
	public static boolean isColAsending(int i, int j) {
		for(int p = 0; p < L; p++) {
			if (i-p >= 0 && (map[i][j] != map[i-p][j] || colFlag[i-p][j]))
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int result = 0;
		// 1) row check 
		rowFlag = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			for (int j = 0; j < N; j++) {
				// descending
				if (j-1 >= 0 && map[i][j-1] > map[i][j] + 1) { // 차이가 1 초과 
					flag = false;
					break;
				}
				if (j-1 >= 0 && map[i][j-1] == map[i][j] + 1) { // 차이가 1 
					// 경사로 놓다가 범위 벗어남 
					if (j + (L-1) > N-1) {
						flag = false;
						break;
					}
					if (isRowDecending(i, j)) { // 경사로 놓을 수 있는 경우 
						for (int p = 0; p < L; p++) {
							rowFlag[i][j+p] = true; // 설치했다고 표시 
						}
					} else if (!isRowDecending(i, j)) {
						flag = false;
						break;
					}
				}
				// ascending
				if (j+1 < N && map[i][j+1] > map[i][j] + 1) {
					flag = false;
					break;
				}
				if (j+1 < N && map[i][j+1] == map[i][j] + 1) {
					if (j - (L-1) < 0) {
						flag = false;
						break;
					}
					if (isRowAsending(i, j)) {
						for (int p = 0; p < L; p++) {
							rowFlag[i][j-p] = true; // 설치했다고 표시 
						}
					} else if (!isRowAsending(i, j)) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				result++;
			}
		}
		// 2) col check
		colFlag = new boolean[N][N];
		for (int j = 0; j < N; j++) {
			boolean flag = true;
			for (int i = 0; i < N; i++) {
				// descending
				if (i-1 >= 0 && map[i-1][j] > map[i][j] + 1) { // 차이가 1 초과 
					flag = false;
					break;
				}
				if (i-1 >= 0 && map[i-1][j] == map[i][j] + 1) { // 차이가 1 
					// 경사로 놓다가 범위 벗어남 
					if (i + (L-1) > N-1) {
						flag = false;
						break;
					}
					if (isColDecending(i, j)) { // 경사로 놓을 수 있는 경우 
						for (int p = 0; p < L; p++) {
							colFlag[i+p][j] = true; // 설치했다고 표시 
						}
					} else if (!isColDecending(i, j)) {
						flag = false;
						break;
					}
				}
				// ascending
				if (i+1 < N && map[i+1][j] > map[i][j] + 1) {
					flag = false;
					break;
				}
				if (i+1 < N && map[i+1][j] == map[i][j] + 1) {
					if (i - (L-1) < 0) {
						flag = false;
						break;
					}
					if (isColAsending(i, j)) {
						for (int p = 0; p < L; p++) {
							colFlag[i-p][j] = true; // 설치했다고 표시 
						}
					} else if (!isColAsending(i, j)) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				result++;
			}
		}
		System.out.println(result);
	}
}
