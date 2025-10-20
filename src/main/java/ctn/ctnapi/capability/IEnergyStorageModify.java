package ctn.ctnapi.capability;

/**
 * 能量存储修改接口
 * 定义能量存储变化时的回调方法
 */
public interface IEnergyStorageModify {
	/**
	 * 当能量发生变化时调用
	 */
	void onEnergyChanged();

	/**
	 * 当能量加载时调用
	 */
	void onEnergyLoad();
}