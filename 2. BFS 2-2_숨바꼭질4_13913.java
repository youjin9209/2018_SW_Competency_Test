/*
문제링크 
https://www.acmicpc.net/problem/13913
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class bf_2_2_숨바꼭질4_13913 {
	static boolean[] check = new boolean[100001];
	static int[] dist = new int[100001];
	static int[] back = new int[100001];
	static int N;
	static int K;
	static Stack<Integer> stack;
	public static void backtrack(int x) {
		if (x == N) {
			stack.push(N);
			return ;
		}
		stack.push(x);
		x = back[x];
		backtrack(x);
	}
	public static void bfs (int x) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(x);
		check[x] = true;
		
		while (!queue.isEmpty()) {
			int node = queue.remove();
		
			if (2*node <= 100000 && !check[2*node]) {
				queue.add(2*node);
				check[2*node] = true;
				dist[2*node] = dist[node] + 1;
				back[2*node] = node;
			}
			if (node + 1 <= 100000 && !check[node + 1]) {
				queue.add(node + 1);
				check[node + 1] = true;
				dist[node + 1] = dist[node] + 1;
				back[node + 1] = node;
			}
			if (node - 1 >= 0 && !check[node - 1]) {
				queue.add(node - 1);
				check[node - 1] = true;
				dist[node - 1] = dist[node] + 1;
				back[node - 1] = node;
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		bfs(N);
		System.out.println(dist[K]);
		stack = new Stack<Integer>();
		backtrack(K);
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb);
	}
}
