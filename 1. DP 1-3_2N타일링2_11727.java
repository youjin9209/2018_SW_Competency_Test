/*
문제링크 
https://www.acmicpc.net/problem/11727
*/
import java.util.Scanner;

public class bf_1_2_2N타일링2_11727 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] d = new int[1001];
		d[1] = 1;
		d[2] = 3;
		for (int i = 3; i <= N; i++) {
			d[i] = d[i-1] + 2*d[i-2];
			d[i] %= 10007;
		}
		System.out.println(d[N]);
	}
}
