/*
문제 링크 
https://www.acmicpc.net/problem/15686
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
치킨 집 M개 pick -> 고른 치킨집을 시작점으로 하여 bfs 탐색하면서 좌표에 치킨 거리 저장 
*/
class Pair {
	int x, y;
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Node {
	int cnt;
	int val;
	public void setNode () {
		this.val = 0;
		this.cnt = 0;
	}
	public void setNode (int val, int cnt){
		this.val = val;
		this.cnt = cnt;
	}
	public void setInitCnt() {
		this.cnt = 0;
	}
	public void setMinCnt (int cnt) {
		if (this.cnt > cnt)
			this.cnt = cnt;
	}
}
public class Main {
	static int N;
	static int M;
	static Node[][] map;
	static ArrayList<Pair> alist;
	static ArrayList<Pair> a;
	static int resultMin = Integer.MAX_VALUE;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	/* bfs() : 치킨집에서 시작하여 각 집까지 탐색 -> 집에 해당하는 좌표에 최소거리 갱신한다 . 
	*/
	public static void bfs(Pair p) {
		Queue<Pair> queue = new LinkedList<Pair>();
		int[][] dist = new int[N][N];
		boolean[][] check = new boolean[N][N];
		queue.add(new Pair(p.x, p.y));
		check[p.x][p.y] = true;
		while (!queue.isEmpty()) {
			Pair np = queue.remove();
			for (int idx = 0; idx < 4; idx++) {
				int nx = np.x + dx[idx];
				int ny = np.y + dy[idx];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (!check[nx][ny]) {
						queue.add(new Pair(nx, ny));
						dist[nx][ny] = dist[np.x][np.y] + 1;
						check[nx][ny] = true;
						if (map[nx][ny].val == 1) {
							if (map[nx][ny].cnt == 0)
								map[nx][ny].cnt = dist[nx][ny];
							else if (map[nx][ny].cnt > 0) {
								// 4. 집에 해당하는 좌표에 최소 치킨거리 갱신 
								// * 주의 : 갱신할때 set함수로 해줘야함 ! 
								map[nx][ny].setMinCnt(dist[nx][ny]);
							} 
						} 
					}
				}
			}
		}
	}
	/* go() : M개의 치킨집을 고른후, alist에 담아서 bfs 돌린다. 
	*/
	public static void go(int idx, int cnt) {
		if (cnt == M) {
			// 3. M개 pick 한 치킨집을 시작점으로 해서 bfs 탐색 
			for (Pair p : a) {
				bfs(p);
			}
			int ans = 0;
			// 5. 모든 M개의 치킨집 bfs 돌린 후 집에 해당하는 좌표의 치킨 거리 합 구한다  
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].val == 1) {
						if (map[i][j].cnt >= 1) {
							ans += map[i][j].cnt;
						}
					}
				}
			}
			if (resultMin > ans)
				resultMin = ans;
			// 6. cnt 초기화 해줘야함 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j].setInitCnt();
				}
			}
			return;
		}
		if (idx >= alist.size()) return;
		a.add(alist.get(idx));
		go(idx + 1, cnt + 1);
		a.remove(a.size() - 1);
		go(idx + 1, cnt);
	}
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	// 1. initialize 
    	N = sc.nextInt();
    	M = sc.nextInt();
    	map = new Node[N][N];
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			map[i][j] = new Node();
    			map[i][j].setNode();
    		}
    	}
    	// 2. M개 치킨집 Pick 한다 
    	alist = new ArrayList<Pair>();
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			int val = sc.nextInt();
    			map[i][j].setNode(val, 0);
    			if (val == 2) {
    				alist.add(new Pair(i, j));
    			}
    		}
    	}
    	a = new ArrayList<Pair>();
    	go(0, 0);
    	System.out.println(resultMin);
    	resultMin = Integer.MAX_VALUE;
    }
}
