/*

*/package sw_test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
	int max, second;
	int num;
	int mC, sC;
	public void setPoint() {
		mC = 0;
		sC = 0;
		max = 0;
		second = 0;
		num = 0;
	}

	public void setPoint(int P, int C) {
		if (num == 0) {
			this.mC = C;
			this.max = P;
			this.num += 1;
		} else if (num == 1) {
			if (max < P) {
				second = max;
				max = P;
				mC = C;
			} else {
				second = P;
				sC = C;
			}
			this.num += 1;
		} else if (num == 2) {
			if (max < P) {
				second = max;
				max = P;
				mC = C;
			} else if (max >= P && P > second) {
				second = P;
				sC = C;
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
	static int[][] dist = new int[11][11];

	public static void bfs(int row, int col, int C, int P) {
		Queue<Pair> queue = new LinkedList<Pair>();
		boolean[][] check = new boolean[11][11];
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
						map[nx][ny].setPoint(P, C);
					} else {
						queue.add(new Pair(nx, ny));
						dist[nx][ny] = dist[p.x][p.y] + 1;
						map[nx][ny].setPoint(P, C);
						check[nx][ny] = true;
					}
				}
			}
		}
	}
	public static boolean IsSameArea (int aX, int aY, int bx, int bY) {
		return true;
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
				map[row][col].setPoint(P, C);
				bfs(row, col, C, P);
			}
			int aX = 1;
			int aY = 1;
			int bX = 10;
			int bY = 10;
			pA = new int[M+1];
			pB = new int[M+1];
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					System.out.print(map[i][j].max+","+map[i][j].second + "  " + map[i][j].mC + " " + map[i][j].sC + "\t");
				}
				System.out.println();
			}

//			for (int m = 0; m <= M; m++) {
//				aX += dx[dirA[m]];
//				aY += dy[dirA[m]];
//				bX += dx[dirB[m]];
//				bY += dy[dirB[m]];
//				if (IsSameArea(aX, aY, bX, bY)) {
//					if (map[aX][aY].num == 1) {
//					}
//					pA[m] = map[aX][aY].max;
//					pB[m] = map[bX][bY].second;
//				} else {
//					pA[m] = map[aX][aY].max;
//					pB[m] = map[bX][bY].max;
//				}
//			}
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
//			int ans = 0;
//			for (int m = 0; m <= M; m++) {
//				ans += (pA[m] + pB[m]);
//			}
//			System.out.println("#"+test_case+" "+ans);
		}
	}
}
