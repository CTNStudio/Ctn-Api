package ctn.ctnapi.adapter;

/**
 * 无限能量存储适配器类
 * 提供无限能量存储的实现，能量值始终为最大值
 */
public class InfiniteModEnergyStorage extends ModEnergyStorage {
	/**
	 * 构造函数，初始化无限能量存储
	 * 将所有参数设置为Integer.MAX_VALUE
	 */
	public InfiniteModEnergyStorage() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * 获取存储的能量值
	 *
	 * @return Integer.MAX_VALUE (无限能量)
	 */
	@Override
	public int getEnergyStored() {
		return Integer.MAX_VALUE;
	}

	/**
	 * 获取最大能量存储值
	 *
	 * @return Integer.MAX_VALUE (无限容量)
	 */
	@Override
	public int getMaxEnergyStored() {
		return Integer.MAX_VALUE;
	}

	/**
	 * 设置最大能量存储值（无操作）
	 *
	 * @param capacity 容量值
	 */
	@Override
	public void setMaxEnergyStored(int capacity) {
	}

	/**
	 * 获取最大能量提取值
	 *
	 * @return Integer.MAX_VALUE (无限提取)
	 */
	@Override
	public int getMaxExtract() {
		return Integer.MAX_VALUE;
	}

	/**
	 * 设置最大能量提取值（无操作）
	 *
	 * @param maxExtract 最大提取值
	 */
	@Override
	public void setMaxExtract(int maxExtract) {
	}

	/**
	 * 获取最大能量接收值
	 *
	 * @return 0 (不接收能量)
	 */
	@Override
	public int getMaxReceive() {
		return 0;
	}

	/**
	 * 设置最大能量接收值（无操作）
	 *
	 * @param maxReceive 最大接收值
	 */
	@Override
	public void setMaxReceive(int maxReceive) {
	}

	/**
	 * 检查是否可以提取能量
	 *
	 * @return true (始终可以提取)
	 */
	@Override
	public boolean canExtract() {
		return true;
	}

	/**
	 * 检查是否可以接收能量
	 *
	 * @return false (不能接收能量)
	 */
	@Override
	public boolean canReceive() {
		return false;
	}

	/**
	 * 设置能量值（无操作）
	 *
	 * @param energy 能量值
	 */
	@Override
	public void setEnergy(int energy) {
	}
}