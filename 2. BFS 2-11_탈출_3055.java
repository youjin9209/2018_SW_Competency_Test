/*
문제 링크 
https://www.acmicpc.net/problem/3055 
조심해야할것 
1) 출발점 여러개일 때 : queue에 다 넣고 시작 
2) string 비교할때 무조건 equals 로 !
3) check 왠만해선 쓰지말고 그냥 dist 로 퉁치자 
*/
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Grid{
	int x, y;
	Grid(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class bf_2_11_탈출_3055 {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		String[][] map = new String[R][C];
		sc.nextLine();
		Queue<Grid> queue = new LinkedList<Grid>();
		int[][] dist_water = new int[R][C];
		int[][] dist_gosm = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(dist_water[i], -1);
			Arrays.fill(dist_gosm[i], -1);
		}
		int p1startX = 0, p1startY = 0, p1endX = 0, p1endY = 0;
		for (int i = 0; i < R; i++) {
			String[] line = sc.nextLine().split("");
			for (int j = 0; j < line.length; j++) {
				map[i][j] = line[j];
				if (map[i][j].equals("*")) {
					queue.add(new Grid(i, j));
					dist_water[i][j] = 0;
				} else if (map[i][j].equals("S")) {
					p1startX = i;
					p1startY = j;
				} else if (map[i][j].equals("D")) {
					p1endX = i;
					p1endY = j;
				}
			}
		}
		// 1) 물부터 채워 놓는다. 조심해야할건 물의 시작위치는 여러개일 수 있으니까 입력받을때 큐에다가 start 다 넣어준다 
		while (!queue.isEmpty()) {
			Grid p = queue.remove();
			int x = p.x;
			int y = p.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
					if (map[nx][ny].equals("D") || map[nx][ny].equals("X")) continue;
					if (dist_water[nx][ny] != -1)  continue;
					queue.add(new Grid(nx, ny));
					dist_water[nx][ny] = dist_water[x][y] + 1;
				}
			}
		}
		// 2) 고슴도치 이동 
		queue.add(new Grid(p1startX, p1startY));
		dist_gosm[p1startX][p1startY] = 0;
		
		while (!queue.isEmpty()) {
			Grid p = queue.remove();
			int x = p.x;
			int y = p.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
					if (map[nx][ny].equals("X")) continue;
					if (dist_gosm[nx][ny] != -1) continue;
					if (dist_water[nx][ny] <= dist_gosm[x][y] + 1 && dist_water[nx][ny] != -1) continue;
					queue.add(new Grid(nx, ny));
					dist_gosm[nx][ny] = dist_gosm[x][y] + 1;
				}
			}
		}
		if (dist_gosm[p1endX][p1endY] == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(dist_gosm[p1endX][p1endY]);
	}
}
