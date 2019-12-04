package com.cl.chatroom.cilent;

import com.cl.chatroom.common.Constants;
import com.cl.chatroom.common.Message;
import com.cl.chatroom.common.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;
import java.util.Scanner;

public class ClientMsgHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        try {
            Utils.printMsg(msg);
            Scanner scanner = new Scanner(System.in);
            System.out.println("cenlu-client, please input message:");
            String reply = scanner.nextLine();

            Message message = new Message(Constants.CLIENT,new Date(),reply);
            ByteBuf byteBuf = ctx.alloc().buffer();
            String content = message.getUsername() + "~"
                    +(Utils.formatDateTime(message.getSentTime())) + "~"
                    +message.getMsg();
//            String content = Utils.encodeMsg(message);
            byteBuf.writeBytes(content.getBytes());
            ctx.writeAndFlush(byteBuf);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
