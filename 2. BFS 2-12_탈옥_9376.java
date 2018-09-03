/*
문제 링크 
https://www.acmicpc.net/problem/9376
왜 Deque를 쓰는가 ?
: 문을 열 때, 안 열 때 -> 2가지 케이스로 나뉨
: 최대한 문을 안 열면서 나가야한다. (최소개수를 구해야하니까) 그래서 문 여는 거를 addLast로 !
*/
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
class P {
	int x, y;
	P (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static char[][] map;
	static int N;
	static int M;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static int[][] bfs(int startX, int startY) {
		int[][] dist = new int[N+2][M+2];
		for (int i = 0; i < N + 2; i++) 
			Arrays.fill(dist[i], -1);
		ArrayDeque<P> deque = new ArrayDeque<P>();
        deque.add(new P(startX, startY));
		dist[startX][startY] = 0;
		while (!deque.isEmpty()) {
			P p = deque.remove();
			int x = p.x;
			int y = p.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx <= N+1 && ny >= 0 && ny <= M+1) {
					if (dist[nx][ny] != -1) continue;
					if (map[nx][ny] == '*') continue;
					if (map[nx][ny] == '#') {
						dist[nx][ny] = dist[x][y] + 1;
						deque.addLast(new P(nx, ny));
					} else {
						dist[nx][ny] = dist[x][y];
						deque.addFirst(new P(nx, ny));
					}
					
				}
			}
		}
		return dist;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T-- > 0) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N+2][M+2];
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < M+2; j++) {
					if (i == 0 || j == 0 || i == N+1 || j == M+1)
						map[i][j] = '.';
				}
			}
			sc.nextLine();
			int cnt = 0;
			int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
			for (int i = 1; i <= N; i++) {
				String line = sc.nextLine();
				for (int j = 1; j <= M; j++) {
					map[i][j] = line.charAt(j-1);
					if (map[i][j] == '$') {
						if (cnt == 0) {
							x1 = i; y1 = j;
							cnt++;
						} else if (cnt == 1) {
							x2 = i; y2 = j;
						}
					}
				}
			}
			int[][] d1 = bfs(x1, y1); // from player 1
			int[][] d2 = bfs(x2, y2); // from player 2 
			int[][] d0 = bfs(0, 0); // from outside 
			int ans = N*M;
			for (int i = 0; i <= N+1; i++) {
				for (int j = 0; j <= M+1; j++) {
					char c = map[i][j];
					if (c == '*') continue;
					int cur = d1[i][j] + d2[i][j] + d0[i][j];
					if (c == '#') cur -= 2; // 동시에 셌을 테니까 
					if (ans > cur)
						ans = cur;
				}
			}
			System.out.println(ans);
		}
	}
}
