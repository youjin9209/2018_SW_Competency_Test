/*
문제 링크 
https://www.acmicpc.net/problem/2529
해결 : 자리수 완전탐색으로 다 채우고나서 validate 해준다. 
주의! 완전탐색할때, 019 -> 021  올수있도록 dfs 돌아오고 나서 check false 해줘야한다 
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class bf_3_12_부등호_2529 {
	static char[] op;
	static int k;
	static ArrayList<String> arr;
	static boolean[] check;
	public static boolean check(String s) {
		int len = op.length;
		for (int i = 0; i < len; i++) {
			if (op[i] == '<') {
				if (s.charAt(i) > s.charAt(i+1)) {
					return false;
				}
			} else if (op[i] == '>') {
				if (s.charAt(i) < s.charAt(i+1)) {
					return false;
				}
			}
		}
		return true;
	}
	public static void dfs(int index, String s) {
		if (index == k+1) {
			if(check(s)) {
				arr.add(s);
			}
			return ;
		} else {
			for (int i = 0; i <= 9; i++) {
				if (check[i])
					continue;
				
				if (!check[i]) {
					check[i] = true;
					dfs(index + 1, s+i);
					check[i] = false; // 풀어줘야한다 . 그래야 019 -> 021  갈 수 있지 
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		sc.nextLine();
		op = new char[k];
		String[] line = sc.nextLine().split(" ");
		for (int i = 0; i < k; i++) {
			op[i] = line[i].charAt(0);
		}
		arr = new ArrayList<String>();
		check = new boolean[10];
		dfs(0, "");
		Collections.sort(arr);
		String min = arr.get(0);
		String max = arr.get(arr.size() - 1);
		System.out.println(max);
		System.out.println(min);
	}
}
