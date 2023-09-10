// package by.example.benchmark;
//
// import by.example.DuplicateSearch;
// import by.example.impl.Binary;
// import by.example.impl.Linear;
//
// import java.util.Random;
//
// class ConsoleBenchmark {
//
//     public static void main(String[] arg) {
//
//         DuplicateSearch binary = new Binary();
//         DuplicateSearch linear = new Linear();
//         int[] sizes = { 100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600 };
//
//         System.out.printf("# searching through an array of length n, time in ns %n");
//         System.out.printf("| %-7s | %-10s | %-10s | %-10s | %-10s | %n", "n", "linear", "lin/n", "binary", "bin/lg(n)");
//         for (int n : sizes) {
//
//             int loop = 10000;
//
//             int[] array = sorted(n);
//             int[] keys = keys(loop, n);
//
//             System.out.printf("%8d", n);
//
//             int k = 1000;
//             double min = Double.POSITIVE_INFINITY;
//
//             for (int i = 0; i < k; i++) {
//                 long t0 = System.nanoTime();
//                 linear.search(array, keys);
//                 long t1 = System.nanoTime();
//                 double t = (t1 - t0);
//                 if (t < min) {
//                     min = t;
//                 }
//             }
//
//             System.out.printf("%8.0f | %.2f", (min / loop), (min * 10 / n / loop));
//
//             min = Double.POSITIVE_INFINITY;
//
//             for (int i = 0; i < k; i++) {
//                 long t0 = System.nanoTime();
//                 binary.search(array, keys);
//                 long t1 = System.nanoTime();
//                 double t = (t1 - t0);
//                 if (t < min) {
//                     min = t;
//                 }
//             }
//
//             System.out.printf("%8.0f | %.2f %n", (min / loop), (min * 10 / loop / Math.log(n)));
//
//         }
//     }
//
//     private static int[] sorted(int n) {
//         Random rnd = new Random();
//         int[] array = new int[n];
//         int nxt = rnd.nextInt(10);
//
//         for (int i = 0; i < n; i++) {
//             array[i] = nxt;
//             nxt += rnd.nextInt(10) + 1;
//         }
//         return array;
//     }
//
//     private static int[] keys(int loop, int n) {
//         Random rnd = new Random();
//         int[] indx = new int[loop];
//         for (int i = 0; i < loop; i++) {
//             indx[i] = rnd.nextInt(n * 5);
//         }
//         return indx;
//     }
// }
