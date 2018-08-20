package com.java.NoBlock.java.Noblock.thread;

import com.java.NoBlock.java.Noblock.thread.ClientThreead;
import com.java.NoBlock.java.Noblock.thread.ServerThreead;

/**
 * @ClassName: MainThread.java
 * @author Mr.Li
 * @Description: 主线程：负责启动 因为双行注释在代码着色的时候会把代码也注释了所以不用。
 * @Date 2018-1-28 下午9:33:58
 * @version V1.0
 */
public class MainThread {
    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread threadClient = new Thread(new ClientThreead());
        Thread threadServer = new Thread(new ServerThreead());
        // 启动服务器
        threadServer.start();
        //启动客户端
        threadClient.start();

    }

}

