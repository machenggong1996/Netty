package com.java.NoBlock;


import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * @ClassName: NIODemo3.java
 * @author Mr.Li
 * @Description: 通道之间的数据传输(直接缓冲区)
 */
public class NIODemo3 {

    @Test
    public void test3() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("E:/SpiderImg.zip"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(
                Paths.get("E:/Nio文件复制/Spider3.zip"), StandardOpenOption.WRITE,
                StandardOpenOption.READ, StandardOpenOption.CREATE);

        // inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }
}
