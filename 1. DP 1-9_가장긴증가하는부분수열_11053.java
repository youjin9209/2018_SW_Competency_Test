/*
문제 링크 
https://www.acmicpc.net/problem/11053
*/
import java.util.Scanner;

public class bf_1_9_가장긴증가하는부분수열_11053 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		int[] d = new int[N];
		d[0] = 1;
		for (int i = 1; i < N; i++) {
			d[i] = 1;
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && d[i] < d[j] + 1)
					d[i] = d[j] + 1;
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (max < d[i])
				max = d[i];
		}
		System.out.println(max);
	}
}
