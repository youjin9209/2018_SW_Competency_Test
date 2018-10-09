import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Node {
	ArrayList<Integer> BC; // BC num 저장 리스트 
	int num; // max, second 갱신 위함 
	int max, second;
	int maxBcNum; // max에 해당하는 BC num
	int secondBcNum; // second에 해당하는 BC num
	public void SetNode() {
		this.BC = new ArrayList<Integer>();
		this.num = 0;
		this.max = 0;
		this.second = 0;
		this.maxBcNum = 0;
		this.secondBcNum = 0;
	}
	/*
	setNode(int range, int charge, int bcNum)
	: 몇개가 들어어던 간에 가장 큰 2개만 안고 간다. 
	: max, second 값이 어느 BC인지도 저장 -> 나중에 max 값 같을 경우 second 값을 누구에게 줄지 비교하기 위함 
	*/
	public void setNode(int range, int charge, int bcNum) {
		BC.add(bcNum);
		if (this.num == 0) {
			this.max = charge;
			this.maxBcNum = bcNum;
			this.num += 1;
		} else if (this.num == 1) {
			if (this.max >= charge) {
				this.second = charge;
				this.secondBcNum = bcNum;
			} else if (this.max < charge) {
				this.second = this.max;
				this.max = charge;
				this.secondBcNum = this.maxBcNum;
				this.maxBcNum = bcNum;
			}
			this.num += 1;
		} else if (this.num == 2) {
			if (charge >= this.max) {
				this.second = this.max;
				this.max = charge;
				this.secondBcNum = this.maxBcNum;
				this.maxBcNum = bcNum;
			} else if (this.max > charge && charge >= this.second) {
				this.second = charge;
				this.secondBcNum = bcNum;
			}
		}
	}
}
class Pair {
	int x, y;
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution {
	static int M;
	static int A;
	static int[] dirA; // A의 이동정보
	static int[] dirB; // B의 이동정보
	static int[] dx = {0, -1, 0, 1, 0}; // 상 우 하 좌
	static int[] dy = {0, 0, 1, 0, -1};
	static Node[][] map = new Node[11][11];
	static int[] tA; // A의 시간당 충전량 
	static int[] tB; // B의 시간당 충전량 
	public static void bfs (int startX, int startY, int range, int charge, int bcNum) {
		Queue<Pair> queue = new LinkedList<Pair>();
		int[][] dist = new int[11][11];
		boolean[][] check = new boolean[11][11];
		queue.add(new Pair(startX, startY));
		check[startX][startY] = true;
		dist[startX][startY] = 0;
		map[startX][startY].setNode(range, charge, bcNum);
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			int x = p.x; int y = p.y;
			if (dist[x][y] >= range)
				break;
			for (int idx = 1; idx <= 4; idx++) {
				int nx = x + dx[idx]; int ny = y + dy[idx];
				if (nx >= 1 && nx <= 10 && ny >= 1 && ny <= 10) {
					if (!check[nx][ny]) {
						queue.add(new Pair(nx, ny));
						dist[nx][ny] = dist[x][y] + 1;
						check[nx][ny] = true;
						map[nx][ny].setNode(range, charge, bcNum);
					}
				}
			}
		}
	}
	/*
	isSameRange()
	: A, B의 위치가 같은 충전 영역에 속해있는 지 확인한다 
	*/
	public static boolean isSameRange(int aX, int aY, int bX, int bY) {
		int aSize = map[aX][aY].BC.size();
		int bSize = map[bX][bY].BC.size();
		for (int i = 0; i < aSize; i++) {
			for (int j = 0; j < bSize; j++) {
				if (map[aX][aY].BC.get(i) == map[bX][bY].BC.get(j))
					return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 1. initialize 
			M = sc.nextInt();
			A = sc.nextInt();
			dirA = new int[M+1];
			dirB = new int[M+1];
			tA = new int[M+1];
			tB = new int[M+1];
			for (int i = 1; i <= M; i++) {
				dirA[i] = sc.nextInt();
			}
			for (int i = 1; i <= M; i++) {
				dirB[i] = sc.nextInt();
			}
			for (int i = 1; i <= 10; i++) {
				for (int j = 1; j <= 10; j++) {
					map[i][j] = new Node();
					map[i][j].SetNode();
				}
			}
			for (int i = 1; i <= A; i++) {
				// 좌표에다가 BC 정보 받은 후 bfs 돌면서 저장
				int startY = sc.nextInt();
				int startX = sc.nextInt();
				int range = sc.nextInt();
				int charge = sc.nextInt();
				int bcNum = i;
				bfs(startX, startY, range, charge, bcNum);
			}
			// 2. process 
			int aX = 1; int aY = 1;
			int bX = 10; int bY = 10;
			for (int p = 0; p <= M; p++) {
				aX += dx[dirA[p]];
				aY += dy[dirA[p]];
				bX += dx[dirB[p]];
				bY += dy[dirB[p]];
				if (isSameRange(aX, aY, bX, bY)) {
					if (map[aX][aY].num == 1 && map[bX][bY].num == 1) { // CASE 1: 같은 영역일때 하나의 BC를 가짐 -> 반띵한다 
						tA[p] = map[aX][aY].max/2;
						tB[p] = map[bX][bY].max/2;
					} else if (map[aX][aY].num == 2 && map[bX][bY].num == 1) { // CASE 2: A,B 둘중 하나가 2개의 BC를 가지고 나머지는 1개의 BC를 가짐 
						if (map[aX][aY].maxBcNum == map[bX][bY].maxBcNum) { // 이때 MAX가 동일한 BC일 경우 
							tA[p] = map[aX][aY].second; 
							tB[p] = map[bX][bY].max; // 하나 가진 놈에게 MAX를 준다 
						} else { // 동일하지 않을 경우에는 걍 다 MAX 줌
							tA[p] = map[aX][aY].max;
							tB[p] = map[bX][bY].max;
						}
					} else if (map[aX][aY].num == 1 && map[bX][bY].num == 2) { // CASE 2
						if (map[aX][aY].maxBcNum == map[bX][bY].maxBcNum) {
							tA[p] = map[aX][aY].max;
							tB[p] = map[bX][bY].second;
						} else {
							tA[p] = map[aX][aY].max;
							tB[p] = map[bX][bY].max;
						}
					} else if (map[aX][aY].num == 2 && map[bX][bY].num == 2) { // CASE 3: A, B 둘다 MAX, SECOND 가질 때 
						if (map[aX][aY].maxBcNum == map[bX][bY].maxBcNum) { // 동일한 BC의 MAX를 가질때
							if (map[aX][aY].max + map[bX][bY].second > map[aX][aY].second + map[bX][bY].max) { // 합이 최대가 되는 경우로 저장
								tA[p] = map[aX][aY].max;
								tB[p] = map[bX][bY].second;
							} else {
								tA[p] = map[aX][aY].second;
								tB[p] = map[bX][bY].max;
							}
						} else {
							tA[p] = map[aX][aY].max;
							tB[p] = map[bX][bY].max;
						}
					}
				} else {
					tA[p] = map[aX][aY].max;
					tB[p] = map[bX][bY].max;
				}
			}
			int result = 0;
			for (int i = 0; i <= M; i++) {
				result += tA[i];
				result += tB[i];
			}
			System.out.println("#"+test_case+" "+result);
		} 
	}
}
