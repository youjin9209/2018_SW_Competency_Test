/*
문제 풀이 
https://www.acmicpc.net/problem/1463
*/
import java.util.Scanner;

public class bf_1_1_1로만들기_1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] d = new int[1000001];
		//d[0] = d[1] = 0;
		d[2] = 1;
		d[3] = 1;
		for (int i = 4; i <= N; i++) {
			d[i] = i;
			if (i-1 >= 0 && d[i] > d[i-1] + 1)
				d[i] = d[i-1] + 1;
			if (i%2 == 0 && d[i] > d[i/2] + 1)
				d[i] = d[i/2] + 1;
			if (i%3 == 0 && d[i] > d[i/3] + 1)
				d[i] = d[i/3] + 1;
		}
		System.out.println(d[N]);
	}
}
