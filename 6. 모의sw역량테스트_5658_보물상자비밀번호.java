import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	static int N;
	static int K;
	static char[] a;
	static ArrayList<String> al;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 1. initialize 
			N = sc.nextInt();
			K = sc.nextInt();
			a = new char[N];
			sc.nextLine();
			a = sc.nextLine().toCharArray();
			al = new ArrayList<String>();
			for (int turn = 0; turn < N/4; turn++) {
				// 2. 중복 체크하며 넣기 
				for (int i = 0; i < N; i += N/4) {
					String line = "";
					for (int j = 0; j < N/4 ; j++) {
						line += a[i+j];
					}
					boolean check = false;
					int len = al.size();
					for (int p = 0;  p < len; p++) {
						if (al.get(p).equals(line)) {
							check = true;
							break;
						}
					}
					if (!check)
						al.add(line);
				}
				// 3. 시계방향으로 돌리기 - 배열 주소복사는 위험하니까 그냥 값 넣어줘 
				char[] temp = new char[N];
				for (int i = 0; i < N; i++) {
					temp[i] = a[(i+1)%N];
				}
				for (int i = 0; i < N; i++) {
					a[i] = temp[i];
				}
			}
			Collections.sort(al);
			int idx = al.size() - K;
			String result = al.get(idx);
			System.out.println("#"+test_case+" "+Integer.parseInt(result, 16));
		}
	}
}
