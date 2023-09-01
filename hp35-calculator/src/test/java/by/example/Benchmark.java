package by.example;

import by.example.stack.DynamicStack;
import by.example.stack.StaticStack;
import by.example.stack.Stack;

public class Benchmark {
    public static void bench(int loop, int ops, Stack stack){
        for (int i = 0; i < loop; i++) {
            for (int j = 0; j < ops; j++) {
                stack.push(j);
            }
            for (int l = 0; l < ops; l++) {
                stack.pop();
            }
        }
    }

    public static double time(int loops, int tries, int ops, Stack stack){
        double min = Double.POSITIVE_INFINITY;
        double sum = 0;
        for (int i = 0; i < tries; i++) {
            System.gc();
            double timeStart = System.nanoTime();
            bench(loops, ops, stack);

            double timeEnd = System.nanoTime();
            double t = (timeEnd - timeStart);
            if ( t < min){
                min = t;
                sum+=t;
            }
        }
        return min;
    }

    static void bench(int ops){
            int tries = 10;
            int loop = 10000;

            Stack stat = new StaticStack(ops);
            time(loop * 10, tries, ops, stat);
            double stat_t = time(loop, tries, ops, stat);

            Stack dyna = new DynamicStack();
            time(loop * 10, tries, ops, dyna);
            double dyna_t = time(loop, tries, ops, dyna);
double ratio = dyna_t/stat_t;
            System.out.printf("%d \t %.2f %.2f %.2f\n", ops, (stat_t/ (loop * 1000)), (dyna_t/ (loop * 1000)), ratio);
    }

    public static void main(String args[]){
        System.out.printf("#%s\t%s\t%s\t%s\n", "n", "static", "dynamic", "ratio");
        bench(100);
        bench(200);
        bench(400);
        bench(800);
        bench(1600);
        bench(3200);
    }
}
