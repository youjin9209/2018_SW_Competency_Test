/*
문제 링크 
https://www.acmicpc.net/problem/1339
해결 :  alphabet에 숫자 부여해서 다음 순열 돌린다. 
*/
import java.util.*;
public class bf_3_13_단어수학_1339 {
	static int[] alpha = new int[256];
    static boolean next_permutation(int[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }
        if (i <= 0) {
            return false;
        }
        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }
        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;
        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }
    
    static int calc(String[] a, Character[] letters, int[] d) {
        int m = letters.length;
        int sum = 0;
        for (int i=0; i<m; i++) {
        	//5. 알파벳 배열에 각 알파벳에 부여된 숫자 넣어준다 
        	//i = 0 : d[i] = 3 -> letters[0] = A -> 65 / alpha[65] = 3;
            alpha[letters[i]] = d[i]; 
        }
        for (String s : a) {
            int now = 0;
            for (char x : s.toCharArray()) {
                now = now * 10 + alpha[x]; // 맨처음 들어온게 1의 자리! 계속 나중에 들어올수록 10, 100 ..의 자리
            }
            sum += now;
        }
        return sum;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] a = new String[n];
        //a[] : GCF, ACDEB
        HashSet<Character> s = new HashSet<>();
        // 1. GCF, ACDEB를 중복 없이 A, B, C, D, E, F, G 로 펼쳐 넣는다 
        /// s : A, B, C, D, E, F, G 
        for (int i = 0; i < n; i++) {
            a[i] = sc.next();
            for (char x : a[i].toCharArray()) {
                s.add(x);
            }
        }
        // 2. char 형으로 바꿔 주고 !
        //letters[] : A, B, C, D, E, F, G
        Character[] letters = s.toArray(new Character[s.size()]);
        int m = letters.length;
        // 3. 숫자를 부여해주는데 최대값을 구해야하니까 9부터 ! 
        int[] d = new int[m];
        for (int i = 0; i < m; i++) {
            d[i] = 9-i;
        }
        // 4. 다음순열 돌리면서 할꺼니까 일단 정렬 : d[] : 3, 4, 5, 6, 7, 8, 9
        Arrays.sort(d);
        int ans = 0;
        do {
            int now = calc(a, letters, d);
            if (ans < now) {
                ans = now;
            }
        } while(next_permutation(d));
        System.out.println(ans);
    }
}
