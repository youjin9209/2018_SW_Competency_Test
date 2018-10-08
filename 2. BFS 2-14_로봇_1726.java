import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Node {
    int x, y;
    int dir;
    int cnt;
    Node (int x, int y, int dir, int cnt) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cnt = cnt;
    }
}
public class Main {
    static int M;
    static int N;
    static int[][] map;
    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int endX;
    static int endY;
    static int endDir;
    static boolean[][][] check;
    public static void bfs(int startX, int startY, int startDir) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(startX, startY, startDir, 0));
        check[startDir][startX][startY] = true;
        while(!queue.isEmpty()) {
            Node n = queue.remove();
            int x = n.x; int y = n.y; int dir = n.dir; int cnt = n.cnt;
            if (x == endX && y == endY && dir == endDir) {
                System.out.println(cnt);
                System.exit(0);
            }
            for (int cmd = 0; cmd < 5; cmd++) {
                if (cmd == 0) { // go 3
                    int nx = 0, ny = 0;
                    int ndir = dir;
                    int tempX = x;
                    int tempY = y;
                    boolean impossible = false;
                    for (int i = 1; i <= 3; i++) {
                        tempX += dx[dir];
                        tempY += dy[dir];
                        if (tempX < 0 || tempX > M || tempY < 0 || tempY > N) {
                        	impossible = true;
                            break;
                        }
                        if (map[tempX][tempY] == 1) {
                            impossible = true;
                            break;
                        }
                    }
                    if (impossible) continue;
                    nx = tempX; ny = tempY;
                    if (nx >= 1 && nx <= M && ny >= 1 && ny <= N) {
                        if (!check[ndir][nx][ny] && map[nx][ny] == 0) {
                            int nCnt = cnt + 1;
                            queue.add(new Node(nx, ny, ndir, nCnt));
                            check[ndir][nx][ny] = true;
                        }
                    }
                } else if (cmd == 1) { // go 2
                    int nx = 0, ny = 0;
                    int ndir = dir;
                    int tempX = x;
                    int tempY = y;
                    boolean impossible = false;
                    for (int i = 1; i <= 2; i++) {
                        tempX += dx[dir];
                        tempY += dy[dir];
                        if (tempX < 0 || tempX > M || tempY < 0 || tempY > N) {
                        	impossible = true;
                            break;
                        }
                        if (map[tempX][tempY] == 1) {
                            impossible = true;
                            break;
                        }
                    }
                    if (impossible) continue;
                    nx = tempX; ny = tempY;
                    if (nx >= 1 && nx <= M && ny >= 1 && ny <= N) {
                    	if (!check[ndir][nx][ny] && map[nx][ny] == 0) {
                            int nCnt = cnt + 1;
                            queue.add(new Node(nx, ny, ndir, nCnt));
                            check[ndir][nx][ny] = true;
                        }
                    }
                } else if (cmd == 2) { // go 1
                    int nx = 0, ny = 0;
                    int ndir = dir;
                    int tempX = x;
                    int tempY = y;
                    for (int i = 1; i <= 1; i++) {
                        nx = tempX + dx[dir];
                        ny = tempY + dy[dir];
                    }
                    if (nx >= 1 && nx <= M && ny >= 1 && ny <= N) {
                    	if (!check[ndir][nx][ny] && map[nx][ny] == 0) {
                            int nCnt = cnt + 1;
                            queue.add(new Node(nx, ny, ndir, nCnt));
                            check[ndir][nx][ny] = true;
                        }
                    }
                } else if (cmd == 3) { // turn left
                    int nx = x; int ny = y;
                    int ndir = 0;
                    if (dir == 1) ndir = 4;
                    else if (dir == 2) ndir = 3;
                    else if (dir == 3) ndir = 1;
                    else if (dir == 4) ndir = 2;
                    if (nx >= 1 && nx <= M && ny >= 1 && ny <= N) {
                    	if (!check[ndir][nx][ny] && map[nx][ny] == 0) {
                            int nCnt = cnt + 1;
                            queue.add(new Node(nx, ny, ndir, nCnt));
                            check[ndir][nx][ny] = true;
                        }
                    }
                } else if (cmd == 4) { // turn right 
                    int nx = x; int ny = y;
                    int ndir = 0;
                    if (dir == 4) ndir = 1;
                    else if (dir == 1) ndir = 3;
                    else if (dir == 3) ndir = 2;
                    else if (dir == 2) ndir = 4;
                    if (nx >= 1 && nx <= M && ny >= 1 && ny <= N) {
                    	if (!check[ndir][nx][ny] && map[nx][ny] == 0) {
                            int nCnt = cnt + 1;
                            queue.add(new Node(nx, ny, ndir, nCnt));
                            check[ndir][nx][ny] = true;
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        map = new int[M+1][N+1];
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == 0 || j == 0)
                    map[i][j] = 1;
                else 
                    map[i][j] = sc.nextInt();
            }
        }
        int startX = sc.nextInt();
        int startY = sc.nextInt();
        int startDir = sc.nextInt();
        endX = sc.nextInt();
        endY = sc.nextInt();
        endDir = sc.nextInt();
        check = new boolean[5][M+1][N+1];
        bfs(startX, startY, startDir);
    }
}
