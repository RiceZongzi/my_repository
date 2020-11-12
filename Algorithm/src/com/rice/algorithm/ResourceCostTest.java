package com.rice.algorithm;

/**
 * @author wgz
 * create date  2020/11/4 22:16
 */
public class ResourceCostTest {

    private static Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        calcSumN();
    }

    private static void calcSumN() {
        System.out.println("-------------------累加法-------------------");
        calc1();
        System.out.println("-------------------高斯求和公式-------------------");
        calc2();
    }

    private static void calc1() {
        long startMs = System.currentTimeMillis();
        long startNs = System.nanoTime();
        int sum = 0;
        int n = 100;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        long endNs = System.nanoTime();
        long endMs = System.currentTimeMillis();
        System.out.println("Σsum = " + sum);
        System.out.println("ns:" + (endNs - startNs));
        System.out.println("ms:" + (endMs - startMs));
    }

    private static void calc2() {
        long startMs = System.currentTimeMillis();
        long startNs = System.nanoTime();
        int sum = 0;
        int n = 100;
        sum = (n + 1) * n / 2;
        long endNs = System.nanoTime();
        long endMs = System.currentTimeMillis();
        System.out.println("Σsum = " + sum);
        System.out.println("ns:" + (endNs - startNs));
        System.out.println("ms:" + (endMs - startMs));
    }

    private static void memory() {
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usableMemory = maxMemory - totalMemory + freeMemory;
        System.out.println("可以获得最大内存是:" + maxMemory/1024/1024 + " M");
        System.out.println("已经分配到的内存大小是:" + totalMemory/1024/1024 + " M");
        System.out.println("所分配内存的剩余大小是:" + freeMemory/1024/1024 + " M");
        System.out.println("最大可用内存大小是:" + usableMemory/1024/1024 + " M");
    }
}
