/*
문제 링크 
https://www.acmicpc.net/problem/10844
변수가 되는걸 찾기 - N번째 
*/
import java.util.Scanner;

public class bf_1_6_쉬운계단수_10844 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long mod = 1000000000L;
		long[][] d = new long[N+1][10];
		for (int i = 1; i <= 9; i++)
			d[1][i] = 1;
		
		for (int i = 1; i <= N; i++) {
			for(int k = 0; k <= 9; k++) {
				if (k-1 >= 0)
					d[i][k] += d[i-1][k-1];
				if (k+1 <= 9)
					d[i][k] += d[i-1][k+1];
				d[i][k] %= mod;
			}
		}
		long sum = 0;
		for (int i = 0; i <= 9; i++) {
			sum += d[N][i];
			sum %= mod;
		}
		System.out.println(sum);
	}
}
