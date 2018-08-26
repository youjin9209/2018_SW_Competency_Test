/* 
문제링크 
https://www.acmicpc.net/problem/1697
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bf_2_1_숨바꼭질_1697 {
	static boolean[] check = new boolean[100001];
	static int[] dist = new int[100001];
	static int K;
	public static void bfs (int x) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(x);
		check[x] = true;
		while (!queue.isEmpty()) {
			int node = queue.remove();
			if (node == K) {
				System.out.println(dist[K]);
				System.exit(0);
			}
			if (2*node <= 100000 && !check[2*node]) {
				queue.add(2*node);
				check[2*node] = true;
				dist[2*node] = dist[node] + 1;
			}
			if (node + 1 <= 100000 && !check[node + 1]) {
				queue.add(node + 1);
				check[node + 1] = true;
				dist[node + 1] = dist[node] + 1;
			}
			if (node - 1 >= 0 && !check[node - 1]) {
				queue.add(node - 1);
				check[node - 1] = true;
				dist[node - 1] = dist[node] + 1;
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		K = sc.nextInt();
		bfs(N);
	}
}
