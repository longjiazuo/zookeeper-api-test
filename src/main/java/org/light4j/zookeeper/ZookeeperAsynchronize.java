package org.light4j.zookeeper;

import java.util.List;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

/**
 * ZooKeeper异步访问测试代码
 * 
 * @author longjiazuo
 * 
 */
public class ZookeeperAsynchronize extends AbstractZooKeeper {
	public static void main(String[] args) {
		try {

			latch.await();
			// getChildren("/longjiazuo");
			// exists("/longjiazuo");
		    //create("/longjiazuo/spring", "spring".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			//getData("/longjiazuo");
			//setData("/longjiazuo/spring","spring".getBytes(),-1);
			//delete("/longjiazuo/spring",-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 异步方式列出子节点
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static List<String> getChildren(String path) throws KeeperException,
			InterruptedException {
		zk.getChildren(path, null, new AsyncCallback.ChildrenCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx,
					List<String> children) {
				for (String node : children) {
					System.out.println(node);
				}
			}
		}, null);
		Thread.sleep(Long.MAX_VALUE);// 确保异步回调能够得到执行
		return null;
	}

	/**
	 * 异步方式判断结点是否存在
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static Stat exists(String path) throws KeeperException,
			InterruptedException {
		zk.exists(path, null, new AsyncCallback.StatCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx, Stat stat) {
				System.out.println(stat);
			}
		}, null);
		Thread.sleep(Long.MAX_VALUE);// 确保异步回调能够得到执行
		return null;
	}

	/**
	 * 异步方式判断结点是否存在
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static Stat create(String path, byte[] data, List<ACL> acl,
			CreateMode createMode) throws KeeperException, InterruptedException {
		zk.create(path, data, acl, createMode,
				new AsyncCallback.StringCallback() {
					@Override
					public void processResult(int rc, String path, Object ctx,
							String name) {
						System.out.println(rc);
						System.out.println(name);
					}
				}, null);
		Thread.sleep(Long.MAX_VALUE);// 确保异步回调能够得到执行
		return null;
	}

	/**
	 * 异步方式获取结点数据
	 * 
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static Stat getData(String path) throws KeeperException,
			InterruptedException {
		zk.getData(path, null, new AsyncCallback.DataCallback() {

			@Override
			public void processResult(int rc, String path, Object ctx,
					byte[] data, Stat stat) {
				System.out.println(new String(data));
			}
		}, null);
		Thread.sleep(Long.MAX_VALUE);// 确保异步回调能够得到执行
		return null;
	}
	
	/**
	 * 异步方式更新结点数据
	 * 
	 * @param path
	 * @param data
	 * @param version
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static Stat setData(String path,byte[] data,int version) throws KeeperException,
			InterruptedException {
		zk.setData(path, data,version, new AsyncCallback.StatCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx, Stat stat) {
				System.out.println(stat);
			}
		}, null);
		Thread.sleep(Long.MAX_VALUE);// 确保异步回调能够得到执行
		return null;
	}
	
	/**
	 * 以异步方式删除结点
	 * @param path
	 * @param version
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static Stat delete(String path, int version)
			throws KeeperException, InterruptedException {
		zk.delete(path, version, new AsyncCallback.VoidCallback() {
			
			@Override
			public void processResult(int rc, String path, Object ctx) {
				System.out.println(rc);
			}
		}, null);
		Thread.sleep(Long.MAX_VALUE);// 确保异步回调能够得到执行
		return null;
	}
}