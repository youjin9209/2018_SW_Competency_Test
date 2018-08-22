/*
문제 링크
https://www.acmicpc.net/problem/6603
*/
/* 
go (a, index, cnt)
a : 입력으로 주어진 수 
index : 선택할지 말지 결정해야 하는 인덱스 
cnt : 현재까지 포함한 수의 개수 
*/
import java.util.*;

public class bf_3_15_로또_6603 {
    static ArrayList<Integer> lotto = new ArrayList<>();
	public static void go (int[] a, int index, int cnt) {
    	if (cnt == 6) {
    		for (int num : lotto) {
    			System.out.print(num + " ");
    		}
    		System.out.println();
    		return;
    	}
    	int n = a.length;
    	if (n == index)
    		return;
    	lotto.add(a[index]);
    	go(a, index + 1, cnt + 1);
    	lotto.remove(lotto.size() - 1);
    	go(a, index + 1, cnt);
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) 
            	break;
            int[] a = new int[n];
            for (int i=0; i<n; i++) {
                a[i] = sc.nextInt();
            }
            go (a, 0, 0);
            System.out.println();
        }
    }
}
