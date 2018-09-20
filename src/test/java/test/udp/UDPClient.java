package test.udp;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.util.stream.IntStream;

public class UDPClient {

    @Test
    public void send() throws IOException {
        byte[] msg = "444".getBytes();
        DatagramPacket req = new DatagramPacket(msg, msg.length, InetAddress.getByName("hxiao.bid"), 9001);

        DatagramSocket socket = new DatagramSocket(9002);
        socket.send(req);
        System.out.println("sent");
        byte data[] = new byte[8192];
        DatagramPacket resp = new DatagramPacket(data, data.length);
        socket.receive(resp);
        System.out.println(new String(resp.getData()));

    }

    public void send2(int it) throws IOException {
        byte[] msg = ("no" + it).getBytes();
        DatagramPacket req = new DatagramPacket(msg, msg.length, InetAddress.getByName("hxiao.bid"), 9001);

        DatagramSocket socket = new DatagramSocket(9000 + it);
        socket.send(req);
        System.out.println("sent");
        byte data[] = new byte[8192];
        DatagramPacket resp = new DatagramPacket(data, data.length);
        socket.receive(resp);
        System.out.println(new String(resp.getData()));

    }

    @Test
    public void run10() throws InterruptedException {
        IntStream.range(0, 10).forEach(it -> {
            System.out.println(it);
            Thread thread = new Thread(() -> {
                try {
                    send2(it);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();

        });

        Thread.sleep(100000);
    }

}
