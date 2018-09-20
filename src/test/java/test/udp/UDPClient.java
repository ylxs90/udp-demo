package test.udp;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class UDPClient {

    @Test
    public void send() throws IOException {
        byte[] msg = "111".getBytes();
        DatagramPacket req = new DatagramPacket(msg, msg.length, InetAddress.getLocalHost(), 9001);

        DatagramSocket socket = new DatagramSocket(9002);
        socket.send(req);
        System.out.println("sent");
        byte data[] = new byte[8192];
        DatagramPacket resp = new DatagramPacket(data, data.length);
        socket.setSoTimeout(2000);
        try {
            socket.receive(resp);
        }catch(SocketTimeoutException e) {
            e.printStackTrace();
        }
        socket.receive(resp);

        System.out.println(new String(resp.getData()));


    }

}
