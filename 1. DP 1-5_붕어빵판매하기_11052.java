/*
문제링크 
https://www.acmicpc.net/problem/11052
*/
import java.util.Scanner;

public class bf_1_5_붕어빵판매하기_11052 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] P = new int[1001];
		for (int i = 1; i <= N; i++) {
			P[i] = sc.nextInt();
		}
		int[] d = new int[1001];
		for (int i = 1; i <= N; i++) {
			d[i] = P[i];
			for (int j = 1; j <= i; j++) {
				if (d[i] < P[j] + d[i-j])
					d[i] = P[j] + d[i-j];
			}
		}
		System.out.println(d[N]);
	}
}
