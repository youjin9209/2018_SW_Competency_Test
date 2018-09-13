import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
// 7 2 3 6 5 4 1
public class Solution_4008_숫자만들기 {
	static int N;
	static int[] op;
	static int[] a;
	public static boolean next_permutation (int[] op) {
		int i = op.length - 1;
		while (i > 0 && op[i-1] >= op[i]) {
			i--;
		}
		if (i <= 0) return false;
		int j = op.length - 1;
		while (op[i-1] >= op[j]) {
			j--;
		}
		int temp = op[j];
		op[j] = op[i-1];
		op[i-1] = temp;
		int k = op.length - 1;
		while (i < k) {
			temp = op[i];
			op[i] = op[k];
			op[k] = temp;
			i++;
			k--;
		}
		return true;
	}
	public static long calculate() {
		long result = a[0];
		for (int i = 1; i < N; i++) {
			if (op[i-1] == 0) { // +
				result += a[i];
			} else if (op[i-1] == 1) { // -
				result -= a[i];
			} else if (op[i-1] == 2) { // *
				result *= a[i];
			} else if (op[i-1] == 3) { // /
				result /= a[i];
			}
		}
		return result;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			op = new int[N-1];
			int idx = 0;
			for (int i = 0; i < 4; i++) {
				int val = sc.nextInt();
				for (int j = 0; j < val; j++) {
					op[idx++] = i;
				}		
			}
			a = new int[N];
			for (int i = 0; i < N; i++) {
				a[i] = sc.nextInt();
			}
			long max = Integer.MIN_VALUE;
			long min = Integer.MAX_VALUE;
			do {
				long ans = 0;
				ans = calculate();
				if (max < ans)
					max = ans;
				if (min > ans)
					min = ans;
			} while(next_permutation(op));
			long result = max - min;
			System.out.println("#"+test_case+" "+result);
		}
	}
}
