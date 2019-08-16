package com.netty.hello.telnet;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.util.Date;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/8/7 15:32
 * @ProjectName: wk-project
 * @Version: 1.0.0
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 业务逻辑处理
     * @param ctx
     * @param req
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String req) throws Exception {
        String resp; //返回响应
        boolean close = false;
        if(StringUtils.isEmpty(req)) {
            resp = "please type something. \r\n";
        }else if("bye".equals(req)) {
            resp = "have a good day!\r\n";
            close = true;
        }else {
            resp = "Did you say '" + req + "'?\r\n";
        }
        ChannelFuture future = ctx.write(resp);
        if(close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 建立连接后置节点
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 为新连接发送庆祝
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("It is " + new Date() + " now.\r\n");
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
