/*
문제 링크 
https://www.acmicpc.net/problem/2193
*/
import java.util.Scanner;

public class bf_1_8_이친수_2193 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[][] d = new long[95][2];
		d[1][0] = 0;
		d[1][1] = 1;
		d[2][0] = 0;
		d[2][1] = 1;
		d[3][0] = 1;
		d[3][1] = 1;
		for (int i = 4; i <= N; i++) {
			d[i][1] = d[i-2][0] + d[i-2][1];
			d[i][0] = d[i-1][0] + d[i-1][1];
		}
		System.out.println(d[N][0] + d[N][1]);
	}
}
