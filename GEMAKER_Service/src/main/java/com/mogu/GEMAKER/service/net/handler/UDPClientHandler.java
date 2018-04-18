package com.mogu.GEMAKER.service.net.handler;

import com.mogu.GEMAKER.entity.CommandDo;
import com.mogu.GEMAKER.service.CommandService;
import com.mogu.GEMAKER.service.net.UDPClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class UDPClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger log = LoggerFactory.getLogger(UDPClient.class);


    @Autowired
    private CommandService commandService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg)
            throws Exception {
        String response = msg.content().toString(CharsetUtil.UTF_8);
        log.info(response);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.error(cause.getMessage());
        ctx.close();
    }
}
