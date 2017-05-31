package org.light4j.zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

/**
 * ZooKeeper同步访问测试代码
 * 
 * @author longjiazuo
 * 
 */
public class ZookeeperSynchronize extends AbstractZooKeeper {

	public static void main(String[] args) {
		try {

			latch.await();
			// getChildren("/longjiazuo");
			// exits("/longjiazuo");
			//create("/longjiazuo/springboot", "springboot".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			//getData("/longjiazuo/springboot");
			//setData("/longjiazuo/spring","sp".getBytes(),-1);
			delete("/longjiazuo/spring", -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 同步方式列出子节点
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static List<String> getChildren(String path) throws KeeperException,
			InterruptedException {
		List<String> children = zk.getChildren(path, null);
		for (String node : children) {
			System.out.println(node);
		}
		return children;
	}

	/**
	 * 同步方式判断子节点是否存在
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static Stat exits(String path) throws KeeperException,
			InterruptedException {
		Stat stat = zk.exists(path, null);
		System.out.println(stat);
		return stat;
	}

	/**
	 * 同步方式创建结点
	 * 
	 * @param path
	 * @param data
	 * @param acl
	 * @param createMode
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static String create(String path, byte[] data, List<ACL> acl,
			CreateMode createMode) throws KeeperException, InterruptedException {
		String name = zk.create(path, data, acl, createMode);
		System.out.println(name);
		return name;
	}

	/**
	 * 同步方式获取结点数据
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static String getData(String path) throws KeeperException,
			InterruptedException {
		byte[] data = zk.getData(path, null, null);
		System.out.println(new String(data));
		return new String(data);
	}
	
	/**
	 * 同步方式更新结点数据
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static Stat setData(String path,byte[] data,int version) throws KeeperException,
			InterruptedException {
		Stat stat = zk.setData(path, data, version);
		System.out.println(stat);
		return stat;
	}
	
	/**
	 * 以同步方式删除结点
	 * 
	 * @param path
	 * @param version
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static void delete(String path, int version)
			throws KeeperException, InterruptedException {
		zk.delete(path, version);
		System.out.println(true);
	}
}