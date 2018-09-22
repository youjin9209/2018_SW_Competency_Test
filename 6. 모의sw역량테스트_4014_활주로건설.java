import java.util.Scanner;

public class Solution_4014_활주로건설 {
	static int N;
	static int X;
	static int[][] map;
	public static boolean isRowDecreasing (int i, int j) {
		for (int p = 0; p < X; p++) {
			if (j+p < N && map[i][j] != map[i][j+p]) {
				return false;
			}
		}
		return true;
	}
	public static boolean isRowIncreasing (int i, int j) {
		for (int p = 0; p < X; p++) {
			if (j-p >= 0 && map[i][j] != map[i][j-p]) {
				return false;
			}
		}
		return true;
	}
	public static boolean isColDecreasing (int i, int j) {
		for (int p = 0; p < X; p++) {
			if (i+p < N && map[i][j] != map[i+p][j]) {
				return false;
			}
		}
		return true;
	}
	public static boolean isColIncreasing (int i, int j) {
		for (int p = 0; p < X; p++) {
			if (i-p >= 0 && map[i][j] != map[i-p][j]) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			X = sc.nextInt();
			map = new int[N][N];
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 1) row check : -> 
			for (int i = 0; i < N; i++) {
				boolean row = true;
				for (int j = 0; j < N; j++) {
					// 내리막 
					if (j-1 >= 0 && map[i][j-1] == map[i][j] + 1) {
						if (j+(X-1) > N-1) {
							row = false;
							break;
						}
						if (!isRowDecreasing(i, j)) {
							row = false;
							break;
						}
					}
					// 오르막 
					if (j+1 < N && map[i][j] + 1 == map[i][j+1]) {
						if (j-(X-1) < 0) {
							row = false;
							break;
						}
						if (!isRowIncreasing(i, j)) {
							row = false;
							break;
						}
					}
				}
				if (row) {
					result++;
				}
			}
			// 2) col check 
			for (int j = 0; j < N; j++) {
				boolean col = true;
				for (int i = 0; i < N; i++) {
					// 오르막 
					if (i+1 < N && map[i][j] + 1 == map[i+1][j]) { 
						if (i-(X-1) < 0) {
							col = false;
							break;
						}
						if (!isColIncreasing(i, j)) {
							col = false;
							break;
						}
					}
					// 내리막 
					if (i-1 >= 0 && map[i-1][j] == map[i][j] + 1) {
						if (i+(X-1) > N-1) {
							col = false;
							break;
						}
						if (!isColDecreasing(i, j)) {
							col = false;
							break;
						}
					}
				}
				if (col) {
					result++;
				}
			}
			System.out.println("#"+test_case+" "+result);
		}
	}
}
