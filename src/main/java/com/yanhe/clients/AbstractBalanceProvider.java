package com.yanhe.clients;

import java.util.List;

public abstract class AbstractBalanceProvider<T> implements IBalanceProvider<T> {
	protected abstract T balanceAlgorithm(List<T> items);
	protected abstract List<T> getBalanceItems();

	public T getBalanceItem() {
		return balanceAlgorithm(getBalanceItems());
	}
}
