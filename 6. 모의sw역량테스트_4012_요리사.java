import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Solution_4012_요리사 {
	static int N;
	static int[][] S;
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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			S = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					S[i][j] = sc.nextInt();
				}
			}
			int[] a = new int[N];
			for (int i = 0; i < N/2; i++) {
				a[i] = 1;
			}
			Arrays.sort(a);
			int min = Integer.MAX_VALUE;
			do {
				ArrayList<Integer> teamA = new ArrayList<Integer>();
				ArrayList<Integer> teamB = new ArrayList<Integer>();
				for (int i = 0; i < N; i++) {
					if (a[i] == 0) {
						teamA.add(i);
					} else {
						teamB.add(i);
					}
				}
				int sumA = 0, sumB = 0;
				for (int i = 0; i < N/2; i++) {
					for (int j = 0; j < N/2; j++) {
						if (i != j) {
							sumA += S[teamA.get(i)][teamA.get(j)];
							sumB += S[teamB.get(i)][teamB.get(j)];
						}
					}
				}
				int dif = sumA - sumB;
				if (dif < 0) dif = -dif;
				if (min > dif)
					min = dif;
			} while(next_permutation(a));
			System.out.println("#"+test_case+" "+min);
		}
	}
}
