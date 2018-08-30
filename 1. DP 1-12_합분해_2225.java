/*
문제 링크 
https://www.acmicpc.net/problem/2225
*/
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		long mod =  1000000000L;
		int[][] d = new int[K+1][N+1];
		d[0][0] = 1;
		for (int k = 1; k <= K; k++) {
			for (int i = 0; i <= N; i++) {
				for (int l = 0; l <= i; l++) {
					d[k][i] += d[k-1][i-l];
					d[k][i] %= mod;
				}
			}
		}
		System.out.println(d[K][N]);
	}
}
