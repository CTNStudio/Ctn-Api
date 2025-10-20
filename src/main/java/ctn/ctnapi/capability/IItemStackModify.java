package ctn.ctnapi.capability;

/**
 * 物品栈修改接口
 * 定义物品栈变化时的回调方法
 */
public interface IItemStackModify {
	/**
	 * 当物品发生变化时调用
	 *
	 * @param slot 槽位索引
	 */
	void onItemChanged(int slot);

	/**
	 * 当物品加载时调用
	 */
	void onItemLoad();
}