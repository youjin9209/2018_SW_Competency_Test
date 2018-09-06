/*
문제 링크 
https://www.acmicpc.net/problem/14226
화면에 있는 문자개수 : s
클립보드에 있는 문자개수 : c
d[s][c] = 화면에 있는 문자개수가 s일때 최소 연산 횟수 
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class bf_2_4_이모티콘_14226 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] d = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(d[i], -1);
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		// s = 1, c = 0
		d[1][0] = 0;
		queue.add(1);
		queue.add(0);
		while (!queue.isEmpty()) {
			int s = queue.remove();
			int c = queue.remove();
			// 1) ctrl + c
			if (d[s][s] == -1) {
				d[s][s] = d[s][c] + 1;
				queue.add(s);
				queue.add(s);
			}
			// 2) ctrl + v
			if (s+c <= N && d[s+c][c] == -1) {
				d[s+c][c] = d[s][c] + 1;
				queue.add(s+c);
				queue.add(c);
			}
			// 3) ctrl + d
			if (s-1 >= 0 && d[s-1][c] == -1) {
				d[s-1][c] = d[s][c] + 1;
				queue.add(s-1);
				queue.add(c);
			}
		}
		int ans = -1;
		for (int i = 0; i <= N; i++) {
			if (d[N][i] != -1) {
				if (ans == -1 || ans > d[N][i])
					ans = d[N][i];
			}
		}
		System.out.println(ans);
	}
}
