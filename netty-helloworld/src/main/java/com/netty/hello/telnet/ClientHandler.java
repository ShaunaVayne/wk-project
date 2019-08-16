package com.netty.hello.telnet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/8/12 10:45
 * @ProjectName: wk-project
 * @Version: 1.0.0
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    //打印读取到的数据
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }

    //异常数据捕获
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
