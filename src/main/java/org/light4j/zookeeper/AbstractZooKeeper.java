package org.light4j.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * ZooKeeper抽象类测试代码
 * 
 * @author longjiazuo
 * 
 */
public abstract class AbstractZooKeeper {
	protected static final String CONNECTION_ADDRESS = "192.168.0.102:2181";// 连接地址
	protected static final int SESSION_TIMEOUT = 5000;// 会话超时时间

	protected static CountDownLatch latch = new CountDownLatch(1);

	protected static ZooKeeper zk = null;
	static {
		try {
			zk = new ZooKeeper(CONNECTION_ADDRESS, SESSION_TIMEOUT,
					new Watcher() {
						@Override
						public void process(WatchedEvent event) {
							if (event.getState() == Event.KeeperState.SyncConnected) {
								latch.countDown();
							}
						}
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
