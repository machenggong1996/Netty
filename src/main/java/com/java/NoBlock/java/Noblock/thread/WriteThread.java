package com.java.NoBlock.java.Noblock.thread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @ClassName: Write.java
 * @author Mr.Li
 * @Description: 这是一个专门负责写数据的线程
 * @Date 2018-1-28 下午9:54:13
 * @version V1.0
 */
public class WriteThread implements Runnable {
    /*
     * 接受通道
     */
    private volatile SocketChannel socketChannel;

    public WriteThread(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        /* 创建缓冲，读取数据 */
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int len = 0;
        synchronized (this) {
            try {
                while ((len = socketChannel.read(buf)) > 0) {
                    buf.flip();
                    System.out.println("服务器" + Thread.currentThread().getId()
                            + "做出反应：");
                    System.out.println(new String(new String(buf.array(), 0,
                            len).getBytes("UTF-8")));
                    buf.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
