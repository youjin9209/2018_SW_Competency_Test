/*
문제 링크 
https://www.acmicpc.net/problem/1699
*/
import java.util.Scanner;

public class bf_1_11_제곱수의합_1699 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] d = new int[100002];
		d[1] = 1;
		d[2] = 2;
		d[3] = 3;
		for (int i = 4; i <= N; i++) {
			d[i] = i;
			for (int k = 1; k*k <= i; k++) {
				if (d[i] > d[(int) (i-Math.pow(k, 2))] + 1)
					d[i] = d[(int) (i-Math.pow(k, 2))] + 1;
			}
		}
		System.out.println(d[N]);
	}
}
