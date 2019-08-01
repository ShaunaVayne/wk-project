package com.netty.hello.telnet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/8/1 18:09
 * @ProjectName: wk-project
 * @Version: 1.0.0
 */
public class TelnetServer {

    public static void main(String[] args) throws InterruptedException {
        // Configure Server
        // 创建两个EventLoopGroup对象
        // 创建bossGroup线程组,用于服务端接受客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 创建workerGroup线程组, 用于进行SocketChannel的数据读写
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建ServerBootstrap对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置要使用的EventLoopGroup
            serverBootstrap.group(bossGroup,workerGroup)
                    // 设置要被实例化的NioServerSocketChannel类
                    .channel(NioServerSocketChannel.class)
                    // 设置NioServerSocketChannel的处理器
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 设置连入服务端的client的SocketChannel的处理器
                    .childHandler(new TelnetServerInitializer());
            // 绑定端口
            ChannelFuture bind = serverBootstrap.bind(8080);
            // 监听服务端关闭, 并阻塞线程等待
            bind.channel().closeFuture().sync();
        }finally {
            // 关闭两个EventLoopGroup对象
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
