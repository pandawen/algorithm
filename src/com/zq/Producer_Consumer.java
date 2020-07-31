package com.zq;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自己实现的一个生产者消费者问题
 * 关键:缓冲区是共享的,要锁起来
 */
public class Producer_Consumer {

    public static void main(String[] args) {
        Shelf shelf = new Shelf(5);
        ExecutorService executorService1 = Executors.newFixedThreadPool(5);//线程池,只用写runnable往execute里面传就行
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            executorService2.execute(new Producer(shelf,i));//10个生产者10个消费者
            executorService1.execute(new Consumer(shelf,i));
        }
        executorService1.shutdown();
        executorService2.shutdown();

    }
}

/**
 * 货架,即缓冲区(也可以直接在这个类的方法里加锁)
 */
class Shelf{
    private int capacity;
    private int productionNum=0;

    Shelf(int capacity){ this.capacity=capacity;} //设置一个容量
    void put(){ if(productionNum<capacity) productionNum++;}//设置货物数是0-capacity
    void get(){ if(productionNum>0) productionNum--; }
    boolean isEmpty(){if(productionNum==0) return true; else return false;}
    boolean isFull(){if(productionNum==capacity) return true; else return false;}
    int getProductionNum(){
        return productionNum;
    }
}

/**
 * 生产者,生产东西放到货架上
 */
class Producer implements Runnable{

    private Shelf shelf=null;
    private int num=0;

    Producer(Shelf shelf,int num){
        this.shelf=shelf;
        this.num=num;
    }

    void produce() throws InterruptedException {
        synchronized (shelf){//哪个是共享资源就锁住哪个(互斥访问)
            while(shelf.isFull()){//如果满就等待,一定要在while里使用wait和notify,这是因为不用的话当线程被唤醒就会直接往下走
                System.out.println("Producer"+num+"进入等待队列");
                shelf.wait();//把shelf这个货架(锁)释放,或者说等待这个货架其他事件发生(消费货物)
            }
            shelf.put();//不满了就生产一个
            System.out.println("Producer"+num+"生产了一个产品,目前货架上货物数:"+shelf.getProductionNum());
            shelf.notifyAll();//告诉别人这个货架可以消费了(释放这个锁)
        }
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者,从货架上拿东西
 */
class Consumer implements Runnable{

    private Shelf shelf=null;
    private int num=0;

    Consumer(Shelf shelf,int num){
        this.shelf=shelf;
        this.num=num;
    }

    void consume() throws InterruptedException {
        synchronized (shelf){//哪个是共享资源就锁住哪个
            while(shelf.isEmpty()){//如果空就先等待
                System.out.println("Consumer"+num+"进入等待队列");
                shelf.wait();//把shelf这个货架(锁)释放,或者说等待这个货架其他事件发生(上架货物)
            }
            shelf.get();//不空了就取一个
            System.out.println("Consumer"+num+"消费了一个产品,目前货架上货物数:"+shelf.getProductionNum());
            shelf.notifyAll();//告诉别人这个货架可以上架一个了
        }
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}