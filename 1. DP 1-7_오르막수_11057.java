/*
문제링크 
https://www.acmicpc.net/problem/11057
*/
import java.util.Scanner;

public class bf_1_7_오르막수_11057 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int mod = 10007;
		int[][] d = new int[N+1][10];
		for (int i = 0; i <= 9; i++) {
			d[1][i] = 1;
		}
		for (int i = 1; i <= N; i++) {
			for (int k = 0; k <= 9; k++) {
				for (int j = 0; j <= k; j++) {
					d[i][k] += d[i-1][j];
				}
				d[i][k] %= mod;
			}
		}
		int sum = 0;
		for (int k = 0; k <= 9; k++) {
			sum += d[N][k];
			sum %= mod;
		}
		System.out.println(sum);
	}
}
