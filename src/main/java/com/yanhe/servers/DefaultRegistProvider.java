package com.yanhe.servers;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

public class DefaultRegistProvider implements IRegistProvider {
	public void regist(Object context) throws Exception {
		// 1:path
		// 2:zkClient
		// 3:serverData

		ZooKeeperRegistContext registContext = (ZooKeeperRegistContext) context;
		String path = registContext.getPath();
		ZkClient zc = registContext.getZkClient();

		try {
			zc.createEphemeral(path, registContext.getData());
		} catch (ZkNoNodeException e) {
			// 父节点不存在
			String parentDir = path.substring(0, path.lastIndexOf('/'));
			zc.createPersistent(parentDir, true);
			
			// 重新注册
			regist(registContext);
		}
	}

	public void unRegist(Object context) throws Exception {
		return;
	}
}
