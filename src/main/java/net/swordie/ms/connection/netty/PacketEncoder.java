/*
    This file is part of Desu: MapleStory v62 Server Emulator
    Copyright (C) 2014  Zygon <watchmystarz@hotmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.swordie.ms.connection.netty;

import net.swordie.ms.connection.crypto.AESCipher;
import net.swordie.ms.connection.crypto.CIGCipher;
import net.swordie.ms.handlers.header.OutHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.swordie.ms.connection.Packet;
import org.apache.log4j.LogManager;
import static net.swordie.ms.connection.crypto.AESCipher.nVersion;
/**
 * Implementation of a Netty encoder pattern so that encryption of MapleStory
 * packets is possible. Follows steps using the special MapleAES as well as
 * ShandaCrypto (which became non-used after v149.2 in GMS).
 *
 * @author Zygon
 */
public final class PacketEncoder extends MessageToByteEncoder<Packet> {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();
    private static final int uSeqBase = (short) ((((0xFFFF - nVersion) >> 8) & 0xFF) | (((0xFFFF - nVersion) << 8) & 0xFF00));

    @Override
    protected void encode(ChannelHandlerContext chc, Packet outPacket, ByteBuf bb) {
        byte[] data = outPacket.getData();
        boolean bEncryptData = true;
        NettyClient c = chc.channel().attr(NettyClient.CLIENT_KEY).get();
        AESCipher ac = chc.channel().attr(NettyClient.AES_CIPHER).get();
        if (c != null) {
            if(!OutHeader.isSpamHeader(OutHeader.getOutHeaderByOp(outPacket.getHeader()))) {
                log.debug("[Out]\t| " + outPacket);
            }
            int uSeqSend = c.getSendIV();
            short uDataLen = (short) (((data.length << 8) & 0xFF00) | (data.length >>> 8));
            short uRawSeq = (short) ((((uSeqSend >> 24) & 0xFF) | (((uSeqSend >> 16) << 8) & 0xFF00)) ^ uSeqBase);
//            ShandaCrypto.encrypt(data); // pre-149

            c.acquireEncoderState();
            try {
                if (bEncryptData) {
                    uDataLen ^= uRawSeq;
                    if (c.getPort() == 8484) {
                        ac.Crypt(data, uSeqSend);
                    } else {
                        CIGCipher.Crypt(data, uSeqSend);
                    }
                }
                c.setSendIV(CIGCipher.InnoHash(uSeqSend, 4, 0));
            } finally {
                c.releaseEncodeState();
            }

            bb.writeShort(uRawSeq);
            bb.writeShort(uDataLen);
            bb.writeBytes(data);
            
        } else {
            log.debug("[PacketEncoder] | Plain sending " + outPacket);
            bb.writeBytes(data);
        }
//        outPacket.release();
//        bb.release();
    }
}
