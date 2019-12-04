package com.cl.chatroom.server;

import com.cl.chatroom.common.Message;
import com.cl.chatroom.common.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * 将服务端Message转成Byte
 * 2019-10-17
 *
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        ByteBuf byteBuf = ctx.alloc().buffer();
        String content = Utils.encodeMsg(msg);
        byteBuf.writeBytes(content.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }
}
