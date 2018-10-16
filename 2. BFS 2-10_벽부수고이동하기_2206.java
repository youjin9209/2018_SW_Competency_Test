/*
문제링크 
https://www.acmicpc.net/problem/2206
방문 여부를 따로 체크하지 않는 이유 잘 생각하기 
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Pair {
	int x, y;
	int flag;
	Pair(int x, int y, int flag) {
		this.x = x;
		this.y = y;
		this.flag = flag; // 0: 안깸 , 1: 깸 
	}
}
public class Main {
	static int N; // 세로  
	static int M; // 가로 
	static int[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void bfs(int startX, int startY) {
		int[][][] dist = new int[2][N][M];
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(startX, startY, 0));
		dist[0][startX][startY] = 1;
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x; int y = p.y; int flag = p.flag;
			if (x == N-1 && y == M-1)  { // bfs로 최단거리구하는거니까 목적지 발견하면 끝내버려 
				System.out.println(dist[flag][x][y]);
				System.exit(0);
			}
			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx]; int ny = y + dy[idx];
				int nflag = flag;
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (dist[nflag][nx][ny] == 0) {
						if (map[nx][ny] == 1) { // 벽일 때 
							// 지금까지 한번도 안깼을 때 
							if (nflag == 0) {
								nflag = 1;
								queue.add(new Pair(nx, ny, nflag));
								dist[nflag][nx][ny] = dist[flag][x][y] + 1;
							}
						} else if (map[nx][ny] == 0) { // 벽 아닐 때
							queue.add(new Pair(nx, ny, nflag));
							dist[nflag][nx][ny] = dist[flag][x][y] + 1;
						}
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 1) initialize 
		N = sc.nextInt(); // 세로 
		M = sc.nextInt(); // 가로 
		map = new int[N][M];
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			String[] line = sc.nextLine().split(""); 
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		// 2) process 
		bfs(0, 0);
		System.out.println("-1");
	}
}
