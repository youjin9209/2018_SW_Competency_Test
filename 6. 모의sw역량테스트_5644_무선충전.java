package sw_test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
	int x, y;
	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Point {
	ArrayList<Integer> area;
	HashMap<Integer, Integer> h;
	int max, second;
	int num;
	public void setPoint() {
		max = 0;
		second = 0;
		num = 0;
		area = new ArrayList<Integer>();
	}

	public void setPoint(int P, int C, int i) {
		boolean flag = false;
		for (int p = 0; p < area.size(); p++) {
			if (area.get(p) == i) {
				flag = true;
				break;
			}
		}
		if (!flag)
			area.add(i);
		if (num == 0) {
			this.max = P;
			this.num += 1;
		} else if (num == 1) {
			if (max < P) {
				second = max;
				max = P;
			} else {
				second = P;
			}
			this.num += 1;
		} else if (num == 2) {
			if (max < P) {
				second = max;
				max = P;
			} else if (max >= P && P > second) {
				second = P;
			}
		}
	}
}

class Main {
	static int[] dirA;
	static int[] dirB;
	static int[] pA;
	static int[] pB;
	static Point[][] map;
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};

	public static void bfs(int row, int col, int C, int P, int bcinfo) {
		Queue<Pair> queue = new LinkedList<Pair>();
		boolean[][] check = new boolean[11][11];
		int[][] dist = new int[11][11];
		queue.add(new Pair(row, col));
		dist[row][col] = 1;
		check[row][col] = true;
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			if (dist[p.x][p.y] > C) break;
			for (int i = 1 ; i <= 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx >= 1 && nx <= 10 && ny >= 1 && ny <= 10) {
					if (check[nx][ny]) continue;
					if (dist[nx][ny] != 0) {
						dist[nx][ny] = dist[p.x][p.y] + 1;
						map[nx][ny].setPoint(P, C, bcinfo);
					} else {
						queue.add(new Pair(nx, ny));
						dist[nx][ny] = dist[p.x][p.y] + 1;
						map[nx][ny].setPoint(P, C, bcinfo);
						check[nx][ny] = true;
					}
				}
			}
		}
//		for (int i = 1; i <= 10; i++) {
//			for (int j = 1; j <= 10; j++) {
//				System.out.print(dist[i][j] + "\t");
//			}
//			System.out.println();
//		}
	}
	public static boolean IsSameRange (int aX, int aY, int bX, int bY) {
		int sizeA = map[aX][aY].area.size();
		int sizeB = map[bX][bY].area.size();
		for (int i = 0; i < sizeA; i++) {
			for (int j = 0; j < sizeB; j++) {
				if (map[aX][aY].area.get(i) ==  map[bX][bY].area.get(j)) {
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int M = sc.nextInt(); // 총 이동시간 
			int A = sc.nextInt(); // AP 개수 
			dirA = new int[M+1];
			dirB = new int[M+1];
			for (int i = 1; i <= M; i++)
				dirA[i] = sc.nextInt();
			for (int i = 1; i <= M; i++)
				dirB[i] = sc.nextInt();
			map = new Point[11][11];
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					map[i][j] = new Point();
					map[i][j].setPoint();
				}
			}
			for (int i = 1; i <= A; i++) {
				int col = sc.nextInt();
				int row = sc.nextInt();
				int C = sc.nextInt();
				int P = sc.nextInt();
				map[row][col].setPoint(P, C, i);
				bfs(row, col, C, P, i);
			}
			int aX = 1;
			int aY = 1;
			int bX = 10;
			int bY = 10;
			pA = new int[M+1];
			pB = new int[M+1];
//			for (int i = 1; i <= 10; i++) {
//				for (int j = 1; j <= 10; j++) {
//					System.out.print(map[i][j].max+","+map[i][j].second + "\t");
//				}
//				System.out.println();
//			}
			for (int m = 0; m <= M; m++) {
				aX += dx[dirA[m]];
				aY += dy[dirA[m]];
				bX += dx[dirB[m]];
				bY += dy[dirB[m]];
				if (IsSameRange(aX, aY, bX, bY)) {
					if (map[aX][aY].num == 1 && map[bX][bY].num == 1) {
						pA[m] = map[aX][aY].max / 2;
						pB[m] = map[bX][bY].max / 2;
					} else if (map[aX][aY].num > 1 && map[bX][bY].num == 1) {
						if (map[aX][aY].max == map[bX][bY].max)
							pA[m] = map[aX][aY].second;
						else 
							pA[m] = map[aX][aY].max;
						pB[m] = map[bX][bY].max;
					} else if (map[aX][aY].num == 1 && map[bX][bY].num > 1) {
						pA[m] = map[aX][aY].max;
						if (map[aX][aY].max == map[bX][bY].max)
							pB[m] = map[bX][bY].second;
						else 
							pB[m] = map[bX][bY].max;
					} else if (map[aX][aY].num > 1 && map[bX][bY].num > 1) {
						if (map[aX][aY].max == map[bX][bY].max) {
							pA[m] = map[aX][aY].max;
							pB[m] = map[bX][bY].second;
						} else {
							pA[m] = map[aX][aY].max;
							pB[m] = map[bX][bY].max;
						}
					}
				} else {
					pA[m] = map[aX][aY].max;
					pB[m] = map[bX][bY].max;
				}
			}
//			for (int m = 0; m <= M; m++) {
//				System.out.print(m+"\t");
//			}
//			System.out.println();
//			for (int m = 0; m <= M; m++) {
//				System.out.print(pA[m]+"\t");
//			}
//			System.out.println();
//			for (int m = 0; m <= M; m++) {
//				System.out.print(pB[m]+"\t");
//			}
//			System.out.println();
			int ans = 0;
			for (int m = 0; m <= M; m++) {
				ans += (pA[m] + pB[m]);
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
