package ctn.ctnapi.adapter;

import ctn.ctnapi.capability.IEnergyStorageModify;
import ctn.ctnapi.capability.IModEnergyStorage;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.energy.EnergyStorage;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 模组能量存储适配器类
 * 扩展了NeoForge的能量存储实现，提供了更多自定义功能
 */
public class ModEnergyStorage extends EnergyStorage implements IModEnergyStorage {
	@Nullable
	protected IEnergyStorageModify onContentsChanged;

	/**
	 * 构造函数，使用指定容量创建能量存储
	 *
	 * @param capacity 能量存储容量
	 */
	public ModEnergyStorage(int capacity) {
		super(capacity);
	}

	/**
	 * 构造函数，使用指定容量和最大传输量创建能量存储
	 *
	 * @param capacity    能量存储容量
	 * @param maxTransfer 最大传输量
	 */
	public ModEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}

	/**
	 * 构造函数，使用指定容量、最大接收量和最大提取量创建能量存储
	 *
	 * @param capacity   能量存储容量
	 * @param maxReceive 最大接收量
	 * @param maxExtract 最大提取量
	 */
	public ModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}

	/**
	 * 构造函数，使用指定容量、最大接收量、最大提取量和初始能量创建能量存储
	 *
	 * @param capacity   能量存储容量
	 * @param maxReceive 最大接收量
	 * @param maxExtract 最大提取量
	 * @param energy     初始能量值
	 */
	public ModEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

	/**
	 * 设置存储的能量值
	 *
	 * @param energy 能量值
	 */
	@Override
	public void setEnergy(@Nonnegative int energy) {
		this.energy = energy;
		onChanged();
	}

	/**
	 * 设置最大能量存储容量
	 *
	 * @param capacity 容量值
	 */
	@Override
	public void setMaxEnergyStored(@Nonnegative int capacity) {
		this.capacity = capacity;
		onChanged();
	}

	/**
	 * 获取最大能量提取量
	 *
	 * @return 最大能量提取量
	 */
	@Override
	@Nonnegative
	public int getMaxExtract() {
		return maxExtract;
	}

	/**
	 * 设置最大能量提取量
	 *
	 * @param maxExtract 最大提取量
	 */
	@Override
	public void setMaxExtract(@Nonnegative int maxExtract) {
		this.maxExtract = maxExtract;
		onChanged();
	}

	/**
	 * 获取最大能量接收量
	 *
	 * @return 最大能量接收量
	 */
	@Override
	@Nonnegative
	public int getMaxReceive() {
		return maxReceive;
	}

	/**
	 * 设置最大能量接收量
	 *
	 * @param maxReceive 最大接收量
	 */
	@Override
	public void setMaxReceive(@Nonnegative int maxReceive) {
		this.maxReceive = maxReceive;
		onChanged();
	}

	/**
	 * 接收能量
	 *
	 * @param toReceive 要接收的能量值
	 * @param simulate  是否为模拟操作
	 * @return 实际接收的能量值
	 */
	@Override
	public int receiveEnergy(int toReceive, boolean simulate) {
		int energy = super.receiveEnergy(toReceive, simulate);
		if (!simulate) onChanged();
		return energy;
	}

	/**
	 * 提取能量
	 *
	 * @param toExtract 要提取的能量值
	 * @param simulate  是否为模拟操作
	 * @return 实际提取的能量值
	 */
	@Override
	public int extractEnergy(int toExtract, boolean simulate) {
		int energy = super.extractEnergy(toExtract, simulate);
		if (!simulate) onChanged();
		return energy;
	}

	/**
	 * 序列化为NBT标签
	 *
	 * @param provider 数据提供者
	 * @return NBT标签
	 */
	@Override
	@Nonnull
	public Tag serializeNBT(HolderLookup.Provider provider) {
		CompoundTag nbt = new CompoundTag(4);
		nbt.putInt("energy", energy);
		nbt.putInt("capacity", capacity);
		nbt.putInt("maxReceive", maxReceive);
		nbt.putInt("maxExtract", maxExtract);
		return nbt;
	}

	/**
	 * 从NBT标签反序列化
	 *
	 * @param provider 数据提供者
	 * @param nbt      NBT标签
	 */
	@Override
	public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {
		if (!(nbt instanceof CompoundTag compoundTag)) {
			throw new IllegalArgumentException("Can not deserialize to an instance that isn't the " +
					"default implementation");
		}
		energy = compoundTag.getInt("energy");
		capacity = compoundTag.getInt("capacity");
		maxReceive = compoundTag.getInt("maxReceive");
		maxExtract = compoundTag.getInt("maxExtract");
		onLoad();
	}

	/**
	 * 转换为字符串表示
	 *
	 * @return 字符串表示
	 */
	@Override
	public String toString() {
		return "EnergyStorage:{" +
				"energy=" + energy +
				"capacity=" + capacity +
				"maxReceive=" + maxReceive +
				"maxExtract=" + maxExtract + "}";
	}

	/**
	 * 获取能量存储百分比
	 *
	 * @return 能量存储百分比
	 */
	public int getPercentage() {
		return Math.round((float) energy / capacity * 100.0f);
	}

	/**
	 * 设置能量变化监听器
	 *
	 * @param onContentsChanged 能量变化监听器
	 */
	public void setOn(IEnergyStorageModify onContentsChanged) {
		this.onContentsChanged = onContentsChanged;
	}

	/**
	 * 当能量变化时调用
	 */
	public void onChanged() {
		if (onContentsChanged != null) onContentsChanged.onEnergyChanged();
	}

	/**
	 * 当能量加载时调用
	 */
	public void onLoad() {
		if (onContentsChanged != null) onContentsChanged.onEnergyLoad();
	}
}