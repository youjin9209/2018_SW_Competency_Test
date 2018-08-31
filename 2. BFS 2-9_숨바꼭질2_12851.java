/*
문제 링크 
https://www.acmicpc.net/problem/12851
check로 왜안하는지 잘 생각하기 - 경로가 겹칠수도 있으니까 !
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bf_2_9_숨바꼭질2_12851 {

	static boolean[] check = new boolean[100001];
	static int[] dist = new int[100001];
	static int[] back = new int[100001];
	static int N;
	static int K;
	static int[] cnt = new int[100001];
	public static void bfs (int x) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(x);
		check[x] = true;
		cnt[x] = 1;
		while (!queue.isEmpty()) {
			int node = queue.remove();

			if (2*node <= 100000) {
				if (!check[2*node]) {
					queue.add(2*node);
					check[2*node] = true;
					dist[2*node] = dist[node] + 1;
					cnt[2*node] = cnt[node];
				} else if (dist[2*node] == dist[node] + 1) {
					cnt[2*node] += cnt[node];
				}
			}
			if (node - 1 >= 0) {
				if (!check[node - 1]) {
					queue.add(node - 1);
					check[node - 1] = true;
					dist[node - 1] = dist[node] + 1;
					cnt[node - 1] = cnt[node];
				} else if (dist[node - 1] == dist[node] + 1) {
					cnt[node - 1] += cnt[node];
				}
			}
			if (node + 1 <= 100000) {
				if (!check[node + 1]) {
					queue.add(node + 1);
					check[node + 1] = true;
					dist[node + 1] = dist[node] + 1;
					cnt[node + 1] = cnt[node];
				} else if (dist[node + 1] == dist[node] + 1) {
					cnt[node + 1] += cnt[node];
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		bfs(N);
		System.out.println(dist[K]);
		System.out.println(cnt[K]);
	}
}
