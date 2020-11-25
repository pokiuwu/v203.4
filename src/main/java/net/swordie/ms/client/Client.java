package net.swordie.ms.client;

import net.swordie.ms.world.Channel;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.netty.NettyClient;
import net.swordie.ms.connection.packet.Login;
import net.swordie.ms.Server;
import net.swordie.ms.world.World;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tim on 2/18/2017.
 */
public class Client extends NettyClient {
	private Char chr;
	private Lock lock;
	private Account account;
	private byte channel;
	private byte worldId;
	private boolean authorized;
	private Channel channelInstance;
	private byte[] machineID;
	private byte oldChannel;

	public Client(io.netty.channel.Channel channel, int sendSeq, int recvSeq) {
		super(channel, sendSeq, recvSeq);
		lock = new ReentrantLock(true);
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Lock getLock() {
		return lock;
	}

	public void write(byte[] data) {
		write(new OutPacket(data));
	}

	public void sendPing() {
		write(Login.sendAliveReq());
	}

	public Account getAccount() {
		return account;
	}

	public void setChannel(byte channel) {
		this.channel = channel;
	}

	public byte getChannel() {
		return channel;
	}

	public byte getWorldId() {
		return worldId;
	}

	public void setWorldId(byte worldId) {
		this.worldId = worldId;
	}

	public Char getChr() {
		return chr;
	}

	public void setChr(Char chr) {
		this.chr = chr;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setChannelInstance(Channel channelInstance) {
		this.channelInstance = channelInstance;
	}

	public Channel getChannelInstance() {
		return channelInstance;
	}

	public World getWorld() {
		return Server.getInstance().getWorldById(getWorldId());
	}

	public void setMachineID(byte[] machineID) {
		this.machineID = machineID;
	}

	public byte[] getMachineID() {
		return machineID;
	}

	public boolean hasCorrectMachineID(byte[] machineID) {
		return Arrays.equals(machineID, getMachineID());
	}

    public void setOldChannel(byte oldChannel) {
        this.oldChannel = oldChannel;
    }

    public byte getOldChannel() {
        return oldChannel;
    }
}
