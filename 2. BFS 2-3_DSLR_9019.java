/*
문제 링크 
https://www.acmicpc.net/problem/9019
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bf_2_3_DSLR_9019 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			boolean[] check = new boolean[10001];
			int[] d = new int[10001];
			char[] word = new char[10001];
			int[] from = new int[10001];
			check[A] = true;
			d[A] = 0;
			from[A] = -1;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(A);
			while (!q.isEmpty()) {
				int now = q.remove();
				// 1) D
				int next = (now*2)%10000;
				if (!check[next]) {
					q.add(next);
					check[next] = true;
					d[next] = d[now] + 1;
					from[next] = now;
					word[next] = 'D';
				}
				// 2) S
				next = now - 1;
				if (next == -1)
					next = 9999;
				if (!check[next]) {
					q.add(next);
					check[next] = true;
					d[next] = d[now] + 1;
					from[next] = now;
					word[next] = 'S';
				}
				// 3) L
				next = (now%1000)*10 + now/1000; 
				if (!check[next]) {
					q.add(next);
					check[next] = true;
					d[next] = d[now] + 1;
					from[next] = now;
					word[next] = 'L';
				}
				// 4) R
				next = (now/10) + (now%10)*1000;
				if (!check[next]) {
					q.add(next);
					check[next] = true;
					d[next] = d[now] + 1;
					from[next] = now;
					word[next] = 'R';
				}
			}
			StringBuilder sb = new StringBuilder();
			while (B != A) {
				sb.append(word[B]);
				B = from[B];
			}
			System.out.println(sb.reverse());
		}
	}
}
