/*
문제 링크 
https://www.acmicpc.net/problem/1525
1) 상태 저장 : HashMap  
2) 0 을 9로 저장 : 순열로 표현 가능함  (항상 9자리 숫자가 나오게됨 ) 
ex) 시작: 1 9 3 4 2 5 7 8 6 -> 종료 : 1 2 3 4 5 6 7 8 9 
*/
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bf_2_5_퍼즐_1525 {
	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 3;
		int start = 0;
		// 1) 수 입력받아서 왼쪽으로 차곡 차곡 수 쌓기 (1 9 3 4 2 5 7 8 6)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int temp = sc.nextInt();
				if (temp == 0) {
					temp = 9;
				}
				start = start*10 + temp;
			}
		}
		// 2) 상태 저장 : map, queue
		Queue<Integer> queue = new LinkedList<Integer>();
		HashMap<Integer, Integer> d = new HashMap<Integer, Integer>();
		d.put(start, 0);
		queue.add(start);
		while (!queue.isEmpty()) {
			int now_num = queue.remove();
			String now = Integer.toString(now_num);
			// 3) 9(0) 에 대한 x, y 좌표 
			int z = now.indexOf('9');
			int x = z/3;
			int y = z%3;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					// 4) 9 <-> 다음 숫자 
					StringBuilder next = new StringBuilder(now);
					char temp = next.charAt(x*3 + y);
					next.setCharAt(x*3+y, next.charAt(nx*3 + ny)); // 9 자리에 다음 숫자 넣기 
					next.setCharAt(nx*3 + ny, temp); // 9를 다음 숫자 자리에 넣기 
					// 5) 다음 숫자 hashmap, queue에 넣기 
					int num = Integer.parseInt(next.toString());
					if (!d.containsKey(num)) {
						d.put(num, d.get(now_num) + 1);
						queue.add(num);
					}
				}
			}
		}
		if (d.containsKey(123456789)) {
			System.out.println(d.get(123456789));
		} else {
			System.out.println(-1);
		}
	}
}
