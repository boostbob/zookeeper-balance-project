
package com.yanhe.servers;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {
	private final IBalanceUpdateProvider balanceUpdater;
	private static final Integer BALANCE_STEP = 1;

	public ServerHandler(IBalanceUpdateProvider balanceUpdater) {
		this.balanceUpdater = balanceUpdater;
	}

	public IBalanceUpdateProvider getBalanceUpdater() {
		return balanceUpdater;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 连接建立的时候增加负载
		System.out.println("one client connect...");
		balanceUpdater.addBalance(BALANCE_STEP);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// 断开的时候减少负载
		balanceUpdater.reduceBalance(BALANCE_STEP);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
