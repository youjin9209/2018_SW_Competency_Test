import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Node {
	int num;
	int gNum;
	public void setNode(int num) {
		this.num = num; 
		this.gNum = 0;
	}
	public void setgNum(int cnt) {
		this.gNum = cnt;
	}
}
class Pair {
	int x, y;
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int N; // 크기 
	static int L;
	static int R;
	static Node[][] map;
	static Node[][] temp;
	static boolean[][] check;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void bfs(int startX, int startY, int cnt) {
		int n = 0;
		int nSum = 0;
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(startX, startY));
		check[startX][startY] = true;
		n += 1;
		nSum += map[startX][startY].num;
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x; int y = p.y;
			for (int idx = 0; idx < 4; idx++) {
				int nx = x + dx[idx]; int ny = y + dy[idx];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (!check[nx][ny]) {
						int dif = Math.abs(map[x][y].num - map[nx][ny].num);
						if (dif >= L && dif <= R) {
							queue.add(new Pair(nx, ny));
							check[nx][ny] = true;
							map[nx][ny].setgNum(cnt);
							n += 1;
							nSum += map[nx][ny].num;
						}
					}
				}
			}
		}
		int avg = nSum / n;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].gNum == cnt) {
					temp[i][j].num = avg;
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 1) initialize 
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		map = new Node[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node();
				int num = sc.nextInt();
				map[i][j].setNode(num);
			}
		}
		int result = 0;
		while(true) {
			check = new boolean[N][N];
			temp = new Node[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp[i][j] = new Node();
				}
			}
			int cnt = 0;
			// 2) bfs 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!check[i][j]) {
						cnt += 1;
						map[i][j].setgNum(cnt);
						bfs(i, j, cnt);
					}
				}
			}
			if (N*N == cnt) break;
			// 3) map update 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = temp[i][j];
				}
			}
			result++;
		}
		System.out.println(result);
	}
}
