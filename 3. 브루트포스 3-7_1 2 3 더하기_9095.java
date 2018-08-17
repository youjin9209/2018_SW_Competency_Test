/*
그냥 DP
*/
import java.util.Scanner;

public class bf_3_7_123더하기_9095 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] d = new int[12];
		d[1] = 1;
		d[2] = 2;
		d[3] = 4;
		for (int i = 4; i <= 11; i++) {
			d[i] = d[i-3] + d[i-2] + d[i-1];
		}
		while (T-- > 0) {
			int N = sc.nextInt();
			System.out.println(d[N]);
		}
	}
}
