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
	/*
	next_permutation() 
	: A(0) or B(1)로 약품처리 할 경우, 나머지를 2 로 초기화함.
	: 만약 약품처리횟수 = 2, D(두께) = 6이라면 [0,0,2,2,2,2] 이렇게 되겠지. 
	: 이 배열을 다음순열로 정렬 -> 원소가 0 인 행을 약품처리해주기 위함. 
	*/
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
	/*
 	check()
 	: 해당 col 에 대해서 K 만큼 연속한지 검사하는 함수. 
 	: A에 대해 검사한지 보고, A에 대해 연속하지 않으면 바로 B가 연속한지 확인하기 위함. 
	*/
	public static boolean check(int col, int checkFlag, int modCount) {
		for (int i = 0; i <= D-K; i++) {
			boolean flag = true;
			for (int k = 1; k <= K; k++) {
				if (modCount == 0) {
					if (map[i + (k-1)][col] != checkFlag) {
						flag = false;
					}
				} else if (modCount >= 1) {
					if (temp[i + (k-1)][col] != checkFlag) {
						flag = false;
					}
				}
			}
			if (flag)
				return true;
		}
		return false;
	}
	/*
	examine()
	: 해당 col 마다 check() 함수를 수행한 후 모든 col이 성능검사를 통과할 수 있는지 여부를 구함  
	*/
	public static boolean examine(int modCount) {
		colCheck = new boolean[W];
		for (int col = 0; col < W; col++) {
			if(check(col, 0, modCount)) {
				colCheck[col] = true;
			} else if (!check(col, 0, modCount)) {
				if (check(col, 1, modCount)) {
					colCheck[col] = true;
				}
			}
		}
		boolean flag = true;
		for (int col = 0; col < W; col++) {
			if (!colCheck[col])
				flag = false;
		}
		return flag;
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
					if (examine(modCount)) {
						result = 0;
						break;
					} else {
						continue;
					}
				} else if (modCount >= 1) {
					// 먼저, A (0) 으로 일괄 약품 처리 
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
						if (examine(modCount)) {
							flagA = true;
							break;
						}
						// 다시 엎어 치기 (복원)
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
						// A로 일괄 약품처리 해도 안됐을 경우, 
						// B (1) 으로 일괄 약품 처리 
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
							if (examine(modCount)) {
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
