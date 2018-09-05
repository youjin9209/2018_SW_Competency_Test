/*
문제 링크 
https://www.acmicpc.net/problem/9328
큐 : 총 27개가 필요하다 
1) 일반 적인 큐 
2) 키를 기다리고 있는 문 큐 - 26개  
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class bf_2_13_열쇠_9328 {
	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	public static char[][] a = new char[111][111];
	public static boolean[] key = new boolean[111];
	public static boolean[][] c = new boolean[111][111];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); 
		while(T-- > 0) {
			int n = sc.nextInt();
			int m = sc.nextInt();

			for (int i = 2; i < n+2; i++) {
				String line = sc.next();
				for (int j = 0; j < m; j++) {
					a[i][j+2] = line.charAt(j);
				}
			}
			// 1) 공간 확장 : 맨 가쪽 테두리를 '*' , 그 안쪽으로 '.' 으
			// 시작을 (1,1) 에서 해버리게 ! - 한번에 처리하기 위함 
			n += 4;
			m += 4;
			for (int i = 0; i < n; i++) {
				a[i][0] = '*';
				a[i][1] = '.';
				a[i][m-2] = '.';
				a[i][m-1] = '*';
			}
			for (int j = 1; j < m-1; j++) {
				a[0][j] = '*';
				a[1][j] = '.';
				a[n-2][j] = '.';
				a[n-1][j] = '*';
			}
			Arrays.fill(key,  false);
			String temp = sc.next();
			int len = temp.length();
			if (temp.charAt(0) != '0') {
				for (int i = 0; i < len; i++) {
					key[temp.charAt(i)-'a'] = true;
				}
			}
			int ans = 0;
			for (int i = 0; i < 111; i++) {
				Arrays.fill(c[i], false);
			}
			// 2) 일반적인 큐 1개, 대문자 큐 26 개 
			Queue<Integer> q = new LinkedList<>();
			Queue<Integer>[] door = new LinkedList[26];
			for (int i = 0; i < 26; i++) {
				door[i] = new LinkedList<Integer>();
			}
			// 3) 1,1 에서 시작 
			q.add(1); 
			q.add(1);
			c[1][1] = true;
			while(!q.isEmpty()) {
				int x = q.remove();
				int y = q.remove();
				
				for (int k = 0; k < 4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					if (c[nx][ny]) continue;
					char w = a[nx][ny];
					if (w == '*') continue;
					c[nx][ny] = true;
					if (w == '.') {
						q.add(nx);
						q.add(ny);
					} else if (w == '$') {
						ans += 1;
						q.add(nx);
						q.add(ny);
					} else if (w >= 'A' && w <= 'Z') {
						if (key[w-'A']) { // 키 있으면 일반적인 큐에 담고  
							q.add(nx);
							q.add(ny);
						} else {  // 없으면 대기타는 대문자 큐에 넣는다 
							door[w-'A'].add(nx);
							door[w-'A'].add(ny);
						}
					} else if (w >= 'a' && w <= 'z') {
						q.add(nx);
						q.add(ny);
						if (!key[w-'a']) {
							key[w-'a'] = true;
							//소문자 키 찾았으면 대문자 큐 다 없애준다 
							while (!door[w-'a'].isEmpty()) {
								q.add(door[w-'a'].remove()); 
							}
						}
					}
				}
			}
			System.out.println(ans);
		}
	}
}
