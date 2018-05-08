package com.mogu.GEMAKER.service.net;

import com.mogu.GEMAKER.model.entity.CommandDo;
import com.mogu.GEMAKER.model.entity.MessageDo;
import com.mogu.GEMAKER.service.net.handler.UDPClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class UDPClient {

    private String hostname;
    private int port;


    public UDPClient(String hostname,int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void run(String msg) throws Exception{
        EventLoopGroup group  = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)//允许广播
                    .handler(new UDPClientHandler());//设置消息处理器
            Channel ch = b.bind(0).sync().channel();
            //向网段内的所有机器广播UDP消息。
            ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8), new InetSocketAddress(hostname,port))).sync();
        } catch (Exception e) {
            group.shutdownGracefully();
        }
    }


    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        for(int i = 0;i < 1;i++){
            System.out.println("no."+i);
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    UDPClient c = new UDPClient("120.27.194.72",3320);
                    try {
                        c.run("TEST");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
