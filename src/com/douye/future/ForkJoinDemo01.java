package com.douye.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer> {

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= 10) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int middle = (end+begin) >> 1;
            MyTask task1 = new MyTask(begin,middle);
            MyTask task2 = new MyTask(middle+1,end);
            task1.fork(); // 实现递归调用Mytask
            task2.fork();
            result = task1.join()+task2.join();
        }
        return result;
    }
}

public class ForkJoinDemo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(1,100);
        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());
        threadPool.shutdown();
    }

}
