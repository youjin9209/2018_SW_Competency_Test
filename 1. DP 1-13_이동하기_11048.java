/* 
문제 링크 
https://www.acmicpc.net/problem/11048
*/
import java.util.Scanner;

public class bf_1_13_이동하기_11048 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N+1][M+1];
		int[][] d = new int[N+1][M+1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				if (i == 0 || j == 0) {
					map[i][j] = 0;
				} else {
					map[i][j] = sc.nextInt();
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				d[i][j] = Math.max(d[i-1][j-1], Math.max(d[i-1][j], d[i][j-1])) + map[i][j];
			}
		}
		System.out.println(d[N][M]);
	}
}
