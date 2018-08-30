/*
문제 링크 
https://www.acmicpc.net/problem/1912
*/
import java.util.Scanner;

public class bf_1_10_연속합_1912 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] a = new int[N];
		int[] d = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		d[0] = a[0];
		for (int i = 1; i < N; i++) {
			d[i] = a[i];
			if (a[i] < a[i] + d[i-1])
				d[i] = a[i] + d[i-1];
		}
		int max = d[0];
		for (int i = 0; i < N; i++) {
			if (max < d[i])
				max = d[i];
		}
		System.out.println(max);
	}
}
