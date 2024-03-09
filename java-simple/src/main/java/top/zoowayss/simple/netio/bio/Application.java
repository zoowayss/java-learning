package top.zoowayss.simple.netio.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
@Slf4j
public class Application {


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            while (true) {
                Socket socket = serverSocket.accept();

                log.info("A client connect [{}]",socket.getPort());

                handle(socket);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void handle(Socket socket) {

        byte[] bytes = new byte[1024];

        try {
            int read = socket.getInputStream().read(bytes);
            log.info("read data {}",new String(bytes,0,read));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
