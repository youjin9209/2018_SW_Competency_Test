import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int N; // 지도 크기 
	static int X; // 경사로 크기 
	static int[][] map; // 지도 
	static boolean[][] check; // 경사로 설치 여부 
	public static boolean rowDecending(int x, int y) {
		for (int p = 0; p < X; p++) {
			if (check[x][y+p] || map[x][y] != map[x][y+p]) // 이미 경사로가 설치 되어있거나 연속하지 않을 경우 false return 
				return false;
		}
		return true;
	}
	public static boolean rowAscending(int x, int y) {
		for (int p = 0; p < X; p++) {
			if (check[x][y-p] || map[x][y] != map[x][y-p]) 
				return false;
		}
		return true;
	}
	public static boolean colDecending(int x, int y) {
		for (int p = 0; p < X; p++) {
			if (check[x+p][y] || map[x+p][y] != map[x][y])
				return false;
		}
		return true;
	}
	public static boolean colAscending(int x, int y) {
		for (int p = 0; p < X; p++) {
			if (check[x-p][y] || map[x][y] != map[x-p][y]) 
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 1) initialize 
			N = sc.nextInt();
			X = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 2) process 
			int result = 0;
			// 2-1) row check 
			check = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				boolean rowFlag = true;
				for (int j = 0; j < N; j++) {
					// row decending check 
					if (j-1 >= 0) {
						if (map[i][j-1] > map[i][j] + 1) {
							rowFlag = false; break;
						}
						if (map[i][j-1] == map[i][j] + 1) {
							if (j+X > N) {
								rowFlag = false; break;
							}
							boolean rowDecendingRslt = rowDecending(i, j);
							if (rowDecendingRslt) {
								for (int p = 0; p < X; p++) {
									check[i][j+p] = true;
								}
							} else if (!rowDecendingRslt) {
								rowFlag = false; break;
							}
						}
					}
					// row ascending check  
					if (j+1 < N) {
						if (map[i][j+1] > map[i][j] + 1) {
							rowFlag = false; break;
						}
						if (map[i][j+1] == map[i][j] + 1) {
							if (j-X < -1) {
								rowFlag = false; break;
							}
							boolean rowAscendingRslt = rowAscending(i, j);
							if (rowAscendingRslt) {
								for (int p = 0; p < X; p++) {
									check[i][j-p] = true;
								}
							} else if (!rowAscendingRslt) {
								rowFlag = false; break;
							}
						}
					}
				}
				if (rowFlag) result++;
			}
			// 2-2) col check 
			check = new boolean[N][N]; 
			for (int j = 0; j < N; j++) {
				boolean colFlag = true;
				for (int i = 0; i < N; i++) {
					// col decending check 
					if (i-1 >= 0) {
						if (map[i-1][j] > map[i][j] + 1) {
							colFlag = false; break;
						}
						if (map[i-1][j] == map[i][j] + 1) {
							if (i+X > N) {
								colFlag = false; break;
							}
							boolean colDecendingRslt = colDecending(i, j);
							if (colDecendingRslt) {
								for (int p = 0; p < X; p++) {
									check[i+p][j] = true;
								}
							} else if (!colDecendingRslt) {
								colFlag = false; break;
							}
						}
					}
					// col ascending check  
					if (i+1 < N) {
						if (map[i+1][j] > map[i][j] + 1) {
							colFlag = false; break;
						}
						if (map[i+1][j] == map[i][j] + 1) {
							if (i-X < -1) {
								colFlag = false; break;
							}
							boolean colAscendingRslt = colAscending(i, j);
							if (colAscendingRslt) {
								for (int p = 0; p < X; p++) {
									check[i-p][j] = true;
								}
							} else if (!colAscendingRslt) {
								colFlag = false; break;
							}
						}
					}
				}
				if (colFlag) result++;
			}
			// 3) get answer 
			System.out.println("#"+test_case+" "+result);
		}
	}
}
