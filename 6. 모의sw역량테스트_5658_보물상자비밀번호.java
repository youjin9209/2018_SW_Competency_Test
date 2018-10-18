import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt(); // 숫자 개수 
			int K = sc.nextInt(); // 크기 순서 
			sc.nextLine();
			String[] arr = sc.nextLine().split("");
			ArrayList<String> resultList = new ArrayList<String>();
			for (int p = 0; p < N/4; p ++) { // 언제 한바퀴 다 도는지 확인 !!
				// 1) 중복 검사하고 삽입 
				for (int i = 0; i < N; i += N/4) {
					String line = "";
					for (int j = 0; j < N/4; j++) {
						line += arr[i+j];
					}
					boolean flag = false;
					int len = resultList.size();
					for (int k = 0; k < len; k++) {
						if (resultList.get(k).equals(line)) {
							flag = true;
							break;
						}
					}
					if (!flag) resultList.add(line);
				}
				// 2) 시계 방향으로 회전 
				String[] temp = new String[N];
				for (int idx = 0; idx < N; idx++) {
					temp[(idx+1)%N] = arr[idx];
				}
				for (int a = 0; a < N; a++) {
					arr[a] = temp[a];
				}
			}
			Collections.sort(resultList);
			int len = resultList.size() - K;
			String ans = resultList.get(len);
			int result = Integer.parseInt(ans, 16);
			System.out.println("#"+test_case+" "+result);
		}
	}
}
