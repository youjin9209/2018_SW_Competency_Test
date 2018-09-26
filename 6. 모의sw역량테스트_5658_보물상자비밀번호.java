import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
 
class Solution_5658_보물상자비밀번호 {
    static int N;
    static int K;
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            K = sc.nextInt();
            sc.nextLine();
            char[] arr = new char[N];
            arr = sc.nextLine().toCharArray();
            ArrayList<String> al = new ArrayList<String>(); 
            for (int k = 0; k < N/4; k++) {
                // 1) arr : 4 등분 자른거 넣어준다 
                for (int i = 0; i < N; i+= N/4) {
                    String line = "";
                    for (int j = 0; j < N/4; j++) {
                        line += arr[i+j];
                    }
                    int len = al.size();
                    boolean flag = true;
                    for (int p = 0; p < len; p++) {
                        if (al.get(p).equals(line)) { // 중복되는건 안 넣는다 
                            flag = false;
                            break;
                        }
                    }
                    if (flag)
                        al.add(line);
                }
                // 2) 시계방향 회전 - 밀어서 임의로 넣어줄 배열 필요함 
                char[] temp = new char[N];
                for (int i = 0; i < N; i++) {
                    temp[(i+1)%N] = arr[i];
                }
                arr = temp;
            }
            int len = al.size();
            Collections.sort(al);
            String result = al.get(len - K);
            int parseInt = Integer.parseInt(result, 16);
            System.out.println("#"+test_case +" "+parseInt);
        }
    }
}
