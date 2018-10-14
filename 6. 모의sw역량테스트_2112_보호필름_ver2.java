import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int D;
	static int W; 
	static int K;
	static int[][] map;
	static int[] arr = {0, 1};
	static ArrayList<ArrayList<Integer>> alList;
	static ArrayList<Integer> al = new ArrayList<Integer>();
	static int[] permuArr;
	static int mediCnt;
	/*
	next_permutation(): A(0),B(1) 조합 돌려놓은걸 바탕으로 다음 순열을 만든다 
	*/
	public static boolean next_permutation(int[] permuArr) {
		int i = permuArr.length - 1;
		while (i > 0 && permuArr[i-1] >= permuArr[i]) {
			i--;
		}
		if (i <= 0) return false;
		int j = permuArr.length - 1;
		while (permuArr[i-1] >= permuArr[j]) {
			j--;
		}
		int temp = permuArr[i-1];
		permuArr[i-1] = permuArr[j];
		permuArr[j] = temp;
		int k = permuArr.length - 1;
		while (k > i) {
			temp = permuArr[k];
			permuArr[k] = permuArr[i];
			permuArr[i] = temp;
			k--;
			i++;
		}
		return true;
	}
	/*
	go () : 약품처리 횟수만큼 A(0), B(1) 조합 뽑는다 
	*/
	public static void go(int idx, int cnt, int mediCnt) {
		if (cnt == mediCnt) {
			ArrayList<Integer> tempal = new ArrayList<Integer>();
			int len = al.size();
			for (int i = 0; i < len; i++) {
				tempal.add(al.get(i));
			}
			// 조합 리스트에 담아줘야함 
			alList.add(tempal);
			return;
		}
		if (idx >= 2) return;
		al.add(arr[idx]);
		go(idx, cnt + 1, mediCnt);
		al.remove(al.size() - 1);
		go(idx + 1, cnt, mediCnt);
	}
	/*
	examine() : 순열 돌린걸로 약품 처리 - 0, 1 해당하는 행 일괄적으로 약품 처리 후 검사 
	*/
	public static boolean examine(int[] permuArr) {
		do {
			int[][] temp = new int[D][W]; 
			// 해당 행에 약품 처리
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					if (permuArr[i] != 2)
						temp[i][j] = permuArr[i];
					else 
						temp[i][j] = map[i][j];
				}
			}
			// 검사 
			boolean colFlag = true;
			for (int j = 0; j < W; j++) {
				if (!colCheck(j, temp)) {
					colFlag = false;
					break;
				}
			}
			if (colFlag) {
				return true;
			}
		} while(next_permutation(permuArr));
		return false;
	}
	/*
	colCheck() : 각 col에서 K만큼 연속한지 check -> A 먼저 체크하고 안되면 B 로 확인 
	*/
	public static boolean colCheck(int j, int[][] nMap) {
		for (int checkFlag = 0; checkFlag <= 1; checkFlag++) { // A먼저 체크 
			for (int i = 0; i <= D-K; i++) {
				boolean ok = true;
				for (int k = 0; k < K; k++) {
					if (nMap[i+k][j] != checkFlag)
						ok = false;
				}
				if (ok) return true;
			}
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
			// 2) 약품 처리 (0회부터 시작)
			for (mediCnt = 0; mediCnt <= D; mediCnt++) {
				if (mediCnt == 0) {
					boolean colFlag = true;
					for (int j = 0; j < W; j++) {
						if (!colCheck(j, map)) {
							colFlag = false;
							break;
						}
					}
					if (colFlag) {
						break;
					} else
						continue;
				} else if (mediCnt >= 1) {
					boolean ok = false;
					alList = new ArrayList<ArrayList<Integer>>();
					go(0, 0, mediCnt);
					// 조합 담아온거를 순열 돌린다 
					permuArr = new int[D];
					Arrays.fill(permuArr, 2);
					int idx = 0;
					int len = alList.size();
					for (int i = 0; i < len; i++) {
						for (int a : alList.get(i)) {
							permuArr[idx++] = a;
						}
						Arrays.sort(permuArr);
						if (examine(permuArr)) {
							ok = true;
							break;
						}
						idx = 0;
						Arrays.fill(permuArr, 2);
					}
					if (ok)
						break;
				}
			}
			System.out.println("#"+test_case+" "+mediCnt);
		}
	}
}
