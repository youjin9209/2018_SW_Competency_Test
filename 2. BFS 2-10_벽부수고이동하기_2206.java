/*
문제링크 
https://www.acmicpc.net/problem/2206
방문 여부를 따로 체크하지 않는 이유 잘 생각하기 
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Point {
	int x, y, z;
	Point (int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z; // flag 
	}
}
public class bf_2_10_벽부수고이동하기_2206 {
	static int N;
	static int M;
	static int[][] map;
	static int[][][] dist;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void bfs(int startX, int startY) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(startX, startY, 0));
		dist[startX][startY][0] = 1;
		
		while (!queue.isEmpty()) {
			Point p = queue.remove();
			int x = p.x;
			int y = p.y;
			int z = p.z;
			if (x == N-1 && y == M-1) {
				System.out.println(dist[N-1][M-1][z]);
				System.exit(0);
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z;
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					// 벽 안 뚫을 때 
					if (map[nx][ny] == 0 && dist[nx][ny][nz] == 0) {
						queue.add(new Point(nx, ny, nz));
						dist[nx][ny][nz] = dist[x][y][z] + 1;
					}
					// 벽 뚫을 때 
					if (map[nx][ny] == 1 && z == 0 && dist[nx][ny][nz] == 0) {
						queue.add(new Point(nx, ny, 1));
						dist[nx][ny][1] = dist[x][y][z] + 1;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		dist = new int[N][M][2];
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			String[] line = sc.nextLine().split("");
			for (int j = 0; j < line.length; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		bfs(0, 0);
		System.out.println(-1);
	}	
}
