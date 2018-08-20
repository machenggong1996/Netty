package com.java.NoBlock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TestBlockingNIO2 {
    /*
     * 带反馈
     *
     * */
    //客户端建立连接
    @Test
    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9899));

        FileChannel inChannel = FileChannel.open(Paths.get("E:/SpiderImg.zip"), StandardOpenOption.READ);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (inChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
            System.out.println("ok");
        }
        System.out.println("进入shutdown");
        sChannel.shutdownOutput();

        //接收服务端的反馈
        int len = 0;
        System.out.println("进入反馈");
        System.out.println(sChannel.read(buf));
//       while ((len = sChannel.read(buf)) != -1) {
//            buf.flip();
//            System.out.println(new String(buf.array(), 0, len));
//            buf.clear();
//        }
        System.out.println("复制完毕");
        inChannel.close();
        sChannel.close();
    }

    //服务端开放端口
    @Test
    public void server() throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("E:/Nio文件复制/Spiderserver2.zip"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        ssChannel.bind(new InetSocketAddress(9899));

        SocketChannel sChannel = ssChannel.accept();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        //发送反馈给客户端
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        sChannel.write(buf);

        sChannel.close();
        outChannel.close();
        ssChannel.close();
    }

}

