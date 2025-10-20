package ctn.ctnapi.capability;

import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

/**
 * 模组能量存储接口
 * 扩展了NeoForge的能量存储接口，提供了更多自定义功能
 */
public interface IModEnergyStorage extends IEnergyStorage {
	/**
	 * 将IEnergyStorage转换为IModEnergyStorage
	 *
	 * @param i IEnergyStorage实例
	 * @return IModEnergyStorage实例，如果转换失败则返回null
	 */
	@Nullable
	static IModEnergyStorage of(IEnergyStorage i) {
		return i instanceof IModEnergyStorage ? (IModEnergyStorage) i : null;
	}

	/**
	 * 设置能量值
	 *
	 * @param energy 能量值
	 */
	void setEnergy(int energy);

	/**
	 * 设置最大能量存储值
	 *
	 * @param capacity 容量值
	 */
	void setMaxEnergyStored(int capacity);

	/**
	 * 获取最大能量提取值
	 *
	 * @return 最大能量提取值
	 */
	int getMaxExtract();

	/**
	 * 设置最大能量提取值
	 *
	 * @param maxExtract 最大提取值
	 */
	void setMaxExtract(int maxExtract);

	/**
	 * 获取最大能量接收值
	 *
	 * @return 最大能量接收值
	 */
	int getMaxReceive();

	/**
	 * 设置最大能量接收值
	 *
	 * @param maxReceive 最大接收值
	 */
	void setMaxReceive(int maxReceive);
}