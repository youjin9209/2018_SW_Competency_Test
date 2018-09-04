/* 
문제 링크 
https://www.acmicpc.net/problem/1261
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
class Pair {
	int x, y;
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class bf_2_8_알고스팟_1261 {
	static int M;
	static int N;
	static int[][] map;
	static int[][] dist;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void bfs(int startX, int startY) {
		ArrayDeque<Pair> queue = new ArrayDeque<Pair>();
		queue.add(new Pair(startX, startY));
		dist[startX][startY] = 0;
		
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x;
			int y = p.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (dist[nx][ny] != -1) continue;
					if (map[nx][ny] == 0) {
						queue.addFirst(new Pair(nx, ny));
						dist[nx][ny] = dist[x][y];
					} else if (map[nx][ny] == 1) {
						queue.addLast(new Pair(nx, ny));
						dist[nx][ny] = dist[x][y] + 1;
					}
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line = bf.readLine().split(" ");
		M = Integer.parseInt(line[0]); // 가로 
		N = Integer.parseInt(line[1]); // 세로 
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] token = bf.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token[j]);
			}
		}
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], -1);
		}
		bfs(0, 0);
		System.out.println(dist[N-1][M-1]);
	}
}
