package com.yanhe.servers;

public interface IBalanceUpdateProvider {
	public boolean addBalance(Integer step);
	public boolean reduceBalance(Integer step);
}
