package top.zoowayss.simple.netio.nio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Description: This is a new io (nio) example program
 * @Author: <a href="https://github.com/zoowayss">zoowayss</a>
 * @Date: 2024/3/9 15:52
 */
@Slf4j
public class App {


    @SneakyThrows
    public static void main(String[] args) {

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.socket().bind(new InetSocketAddress(9000));

        ssc.configureBlocking(false);
        Selector selector = Selector.open();

        ssc.register(selector, SelectionKey.OP_ACCEPT);

        log.info("server start successfully");

        while (true) {

            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    log.info("a client connect");
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    int len = channel.read(buffer);
                    log.info("read data:{} from {}", new java.lang.String(buffer.array()), channel.getRemoteAddress());
                    channel.close();
                }

                iterator.remove();
            }
        }
    }
}
