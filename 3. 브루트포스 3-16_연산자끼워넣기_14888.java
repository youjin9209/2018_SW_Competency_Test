/* 
문제링크 
https://www.acmicpc.net/problem/14888
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/*
 * 연산자의 우선순위를 무시해도 되므로 
 * +,-,*,/ 을 0, 1, 2, 3 으로 치환해서 op[] 에 담는다.
 * op[] 를 다음순열 돌려서 계속 순회한다. 
 */
public class bf_3_16_연산자끼워넣기_14888 {
	static int N;
	static int[] a;
	static int[] op;
	static ArrayList<Integer> result;
	public static boolean next_permutation (int[] op) {
		// 7 2 3 6 5 4 1
		int i = op.length - 1;
		// 1. 가장 긴 감소수열의 시작점 찾기 : i
		while (i > 0 && op[i-1] >= op[i]) {
			i--;
		}
		if (i <= 0) {
			return false;
		}
		// 2. j >= i 이면서 a[i-1] < a[j] 인 가장 큰 j 찾기 
		int j = op.length - 1;
		while (op[i-1] >= op[j]) {
			j--;
		}
		// 3. swap (a[i-1], a[j])
		int temp = op[j];
		op[j] = op[i-1];
		op[i-1] = temp;
		// 4. a[i] 부터 뒤집기 6 5 3 1 -> 1 3 5 6 
		int k = op.length - 1;
		while (i < k) {
			temp = op[i];
			op[i] = op[k];
			op[k] = temp;
			k--;
			i++;
		}
		return true;
	}
	public static int calculate() {
		int result = a[0];
		for (int i = 1; i < N; i++) {
			if (op[i-1] == 0) { // +
				result += a[i];
			} else if (op[i-1] == 1) { // -
				result -= a[i];
			} else if (op[i-1] == 2) { // *
				result *= a[i];
			} else {
				result /= a[i];
			}
		}
		return result;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		op = new int[N-1];
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			int cnt = sc.nextInt();
			for (int j = 0; j < cnt; j++) {
				op[idx++] = i;
			}
		}
		result = new ArrayList<Integer>();
		do {
			int num = calculate();
			result.add(num);
		} while (next_permutation(op));
		Collections.sort(result);
		int len = result.size();
		int max = result.get(len - 1);
		int min = result.get(0);
		System.out.println(max);
		System.out.println(min);
	}
}
