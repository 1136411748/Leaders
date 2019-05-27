package Server;

import java.util.concurrent.*;

public class ThreadPool{
    private ExecutorService service;
    //创建线程池，将线程空闲时间设为120s，将最大线程数设为maxThreadPool,
    // 将最大等待队列设置为maxWaitQueue
    public ThreadPool(int maxThreadPool,int maxWaitQueue){
        service = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxThreadPool,
                120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(maxWaitQueue));
    }

    public void addThread(java.lang.Runnable thread){
        service.execute(thread);//将线程加入到线程池
    }
}
