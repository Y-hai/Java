package Algorithm.数据结构;

import java.io.*;

public class 滑动窗口 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int N = (int) 1e6 + 10;
    static int n, k, tt, hh, a[] = new int[N], q[] = new int[N];

    public static void main(String[] args) throws IOException {
        String[] arr = in.readLine().split(" ");
        n = Integer.parseInt(arr[0]);
        k = Integer.parseInt(arr[1]);
        arr = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(arr[i]);
        }
        // 遍历两遍，先求最小队列，维护队列单调不减且不溢出
        // 为什么队列存下标，因为存数字无法看出窗口跨度大小
        for (int i = 0; i < n; i++) {
            if (hh < tt && q[hh] < i - k + 1) hh++; // 如果队列非空并且下标跨度过大
            while (hh < tt && a[q[tt - 1]] > a[i]) tt--; // 如果队列非空并且队尾元素大于下一个元素
            q[tt++] = i;
            if (i >= k - 1) {
                out.write(Integer.toString(a[q[hh]]));
                out.write(" ");
            }
        }
        out.write('\n');
        hh = tt = 0;
        // 维护单调不增队列
        for (int i = 0; i < n; i++) {
            if (hh < tt && q[hh] < i - k + 1) hh++;
            while (hh < tt && a[q[tt - 1]] < a[i]) tt--;
            q[tt++] = i;
            if (i >= k - 1) {
                out.write(Integer.toString(a[q[hh]]));
                out.write(" ");
            }
        }
        out.flush();
        // 严格来说这道题用的数据结构不是队列，另外注意队列里维护的是下标
    }
}
