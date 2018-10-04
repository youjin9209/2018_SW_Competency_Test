import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int D;
	static int W; 
	static int K;
	static int[][] map;
	static int[][] temp;
	static int[] a;
	static int result;
	static boolean[] colCheck;
	public static boolean next_permutation (int[] a) {
		int i = a.length - 1;
		while (i > 0 && a[i-1] >= a[i]) {
			i--;
		}
		if (i <= 0) return false;
		int j = a.length - 1;
		while (a[i-1] >= a[j]) {
			j--;
		}
		int temp = a[i-1];
		a[i-1] = a[j];
		a[j] = temp;
		int k = a.length - 1;
		while (i < k) {
			temp = a[k];
			a[k] = a[i];
			a[i] = temp;
			k--;
			i++;
		}
		return true;
	}
	public static boolean check(int col, int checkFlag) {
		for (int i = 0; i <= D-K; i++) {
			boolean flag = true;
			for (int k = 1; k <= K; k++) {
				if (map[i + (k-1)][col] != checkFlag) {
					flag = false;
				}
			}
			if (flag)
				return true;
		}
		return false;
	}
	public static boolean check2(int col, int checkFlag) {
		for (int i = 0; i <= D-K; i++) {
			boolean flag = true;
			for (int k = 1; k <= K; k++) {
				if (temp[i + (k-1)][col] != checkFlag) {
					flag = false;
				}
			}
			if (flag)
				return true;
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 1) initialize 
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 2) process
			for (int modCount = 0; modCount <= D; modCount++) {
				boolean flagA = false, flagB = false;
				if (modCount == 0) {
					colCheck = new boolean[W];
					for (int col = 0; col < W; col++) {
						if(check(col, 0)) {
							colCheck[col] = true;
						} else if (!check(col, 0)) {
							if (check(col, 1)) {
								colCheck[col] = true;
							}
						}
					}
					boolean flag = true;
					for (int col = 0; col < W; col++) {
						if (!colCheck[col])
							flag = false;
					}
					if (flag) {
						result = 0;
						break;
					} else {
						continue;
					}
				} else if (modCount >= 1) {
					// 1) A (0) 으로 일괄 약품 처리 
					a = new int[D];
					Arrays.fill(a, 2);
					for (int i = 1; i <= modCount; i++) {
						a[i-1] = 0;
					}
					temp = new int[D][W];
					do {
						for (int i = 0; i < D; i++) {
							if (a[i] == 0) {
								for (int j = 0; j < W; j++) {
									temp[i][j] = 0;
								}
							} else {
								for (int j = 0; j < W; j++) {
									temp[i][j] = map[i][j];
								}
							}
						}
						// 2. check 
						colCheck = new boolean[W];
						for (int col = 0; col < W; col++) {
							if(check2(col, 0)) {
								colCheck[col] = true;
							} else if (!check2(col, 0)) {
								if (check2(col, 1)) {
									colCheck[col] = true;
								}
							}
						}
						boolean flag = true;
						for (int col = 0; col < W; col++) {
							if (!colCheck[col]) {
								flag = false;
								break;
							}
						}
						if (flag) {
							flagA = true;
							break;
						}
						// 다시 엎어 치기  
						for (int i = 0; i < D; i++) {
							if (a[i] == 0) {
								for (int j = 0; j < W; j++) {
									temp[i][j] = map[i][j];
								}
							}
						}
					} while (next_permutation(a));
					if (flagA) {
						result = modCount;
						break;
					} else if (!flagA) {
						a = new int[D];
						Arrays.fill(a, 2);
						// 1) B (1) 으로 일괄 약품 처리 
						for (int i = 1; i <= modCount; i++) {
							a[i-1] = 1;
						}
						temp = new int[D][W];
						do {
							for (int i = 0; i < D; i++) {
								if (a[i] == 1) {
									for (int j = 0; j < W; j++) {
										temp[i][j] = 1;
									}
								} else {
									for (int j = 0; j < W; j++) {
										temp[i][j] = map[i][j];
									}
								}
							}
							// 2. check 
							colCheck = new boolean[W];
							for (int col = 0; col < W; col++) {
								if(check2(col, 0)) {
									colCheck[col] = true;
								} else if (!check2(col, 0)) {
									if (check2(col, 1)) {
										colCheck[col] = true;
									}
								}
							}
							boolean flag = true;
							for (int col = 0; col < W; col++) {
								if (!colCheck[col])
									flag = false;
							}
							if (flag) {
								flagB = true;
								break;
							}
							for (int i = 0; i < D; i++) {
								if (a[i] == 1) {
									for (int j = 0; j < W; j++) {
										temp[i][j] = map[i][j];
									}
								}
							}
						} while (next_permutation(a));
						if (flagB) {
							result = modCount;
							break;
						}
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}
	}
}
