/*
문제 링크 
https://www.acmicpc.net/problem/13549
우선순위 정하기 
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class bf_2_7_숨바꼭질3_13549 {
	static boolean[] check = new boolean[100001];
	static int[] dist = new int[100001];
	static int[] back = new int[100001];
	static int N;
	static int K;

	public static void bfs (int x) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(x);
		check[x] = true;
		
		while (!queue.isEmpty()) {
			int node = queue.remove();
			if (2*node <= 100000) {
				if (!check[2*node]) {
					queue.add(2*node);
					check[2*node] = true;
					dist[2*node] = dist[node];
				}
			}
			// 왜 node-1 이 먼저 가야할까 ? 
			if (node - 1 >= 0) {
				if (!check[node - 1]) {
					queue.add(node - 1);
					check[node - 1] = true;
					dist[node - 1] = dist[node] + 1;
				}
			}
			if (node + 1 <= 100000) {
				if (!check[node + 1]) {
					queue.add(node + 1);
					check[node + 1] = true;
					dist[node + 1] = dist[node] + 1;
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
	}
}
