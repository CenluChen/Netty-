package com.cl.chatroom.cilent;

import com.cl.chatroom.common.Message;
import com.cl.chatroom.common.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 将Byte转成Message
 * @author cenlu
 * 2019-10-17
 *
 */
public class ClientTransferMsgHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] buff =new byte[2048];
        int length = in.readableBytes();
        in.readBytes(buff,0,length);
        String totalMsg = new String(buff,0,length, StandardCharsets.UTF_8);
        String[] content = totalMsg.split("~");
        out.add(new Message(content[0], Utils.parseDateTime(content[1]),content[2]));
    }
}
