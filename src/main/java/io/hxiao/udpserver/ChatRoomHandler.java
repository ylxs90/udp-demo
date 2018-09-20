package io.hxiao.udpserver;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

public class ChatRoomHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private Map<String, ChatRoom> chatRoomMap = new HashMap<>();

    protected ChatRoomHandler() {
        super();
        System.out.println("init " + this);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println(ctx.channel().localAddress());
        System.out.println(ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String msg = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println(msg);
        System.out.println(packet.sender());
        DatagramPacket resp = new DatagramPacket(Unpooled.copiedBuffer("hello " + msg, CharsetUtil.UTF_8), packet.sender());

        ctx.writeAndFlush(resp);
    }

}
