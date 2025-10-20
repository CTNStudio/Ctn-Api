package ctn.ctnapi.common.data_compoent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import ctn.ctnapi.adapter.ModEnergyStorage;
import ctn.ctnapi.capability.IModEnergyStorage;
import ctn.ctnapi.util.EnergyUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * 模组组件能量存储类
 * 基于数据组件实现的能量存储系统
 */
public record ModComponentEnergyStorage(MutableDataComponentHolder parent,
                                        DataComponentType<EnergyStorageData> dataComponentType)
		implements IModEnergyStorage {

	/**
	 * 构造函数
	 *
	 * @param parent            可变数据组件持有者
	 * @param dataComponentType 数据组件类型供应器
	 */
	public ModComponentEnergyStorage(MutableDataComponentHolder parent,
	                                 Supplier<DataComponentType<EnergyStorageData>> dataComponentType) {
		this(parent, dataComponentType.get());
	}

	/**
	 * 获取能量存储数据
	 *
	 * @return 能量存储数据
	 */
	public @NotNull EnergyStorageData getEnergyStorageData() {
		return parent.getOrDefault(dataComponentType, EnergyStorageData.DEFAULT.get());
	}

	/**
	 * 设置能量存储数据
	 *
	 * @param data 能量存储数据
	 */
	public void setEnergyStorageData(EnergyStorageData data) {
		parent.set(dataComponentType, data);
	}

	/**
	 * 设置能量值
	 *
	 * @param energy 能量值
	 */
	@Override
	public void setEnergy(int energy) {
		setEnergyStorageData(getEnergyStorageData().setEnergyStored(energy));
	}

	/**
	 * 获取最大能量提取值
	 *
	 * @return 最大能量提取值
	 */
	@Override
	public int getMaxExtract() {
		return getEnergyStorageData().maxExtract();
	}

	/**
	 * 设置最大能量提取值
	 *
	 * @param maxExtract 最大提取值
	 */
	@Override
	public void setMaxExtract(int maxExtract) {
		setEnergyStorageData(getEnergyStorageData().setMaxExtract(maxExtract));
	}

	/**
	 * 获取最大能量接收值
	 *
	 * @return 最大能量接收值
	 */
	@Override
	public int getMaxReceive() {
		return getEnergyStorageData().maxReceive();
	}

	/**
	 * 设置最大能量接收值
	 *
	 * @param maxReceive 最大接收值
	 */
	@Override
	public void setMaxReceive(int maxReceive) {
		setEnergyStorageData(getEnergyStorageData().setMaxReceive(maxReceive));
	}

	/**
	 * 设置能量存储数据
	 *
	 * @param maxEnergyStored 最大能量存储值
	 * @param maxReceive      最大接收值
	 * @param maxExtract      最大提取值
	 * @param energyStored    能量存储值
	 */
	public void setEnergyStored(int maxEnergyStored, int maxReceive, int maxExtract, int energyStored) {
		setEnergyStorageData(getEnergyStorageData().setEnergyStored(maxEnergyStored, maxReceive, maxExtract, energyStored));
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
		var data = getEnergyStorageData();
		if (!canReceive() || toReceive <= 0) {
			return 0;
		}

		int energyReceived = Mth.clamp(data.maxEnergyStored() - data.energyStored(),
				0, Math.min(data.maxReceive(), toReceive));
		if (!simulate) setEnergy(data.energyStored() + energyReceived);
		return energyReceived;
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
		EnergyStorageData data = getEnergyStorageData();
		if (!canExtract() || toExtract <= 0) {
			return 0;
		}

		int energyExtracted = Math.min(data.energyStored(), Math.min(data.maxExtract(), toExtract));
		if (!simulate) setEnergy(data.energyStored() - energyExtracted);
		return energyExtracted;
	}

	/**
	 * 获取存储的能量值
	 *
	 * @return 存储的能量值
	 */
	@Override
	public int getEnergyStored() {
		return getEnergyStorageData().energyStored();
	}

	/**
	 * 设置能量存储数据
	 *
	 * @param iModEnergyStorage 能量存储实例
	 */
	public void setEnergyStored(IModEnergyStorage iModEnergyStorage) {
		EnergyUtil.copyEnergy(this, iModEnergyStorage);
	}

	/**
	 * 获取最大能量存储值
	 *
	 * @return 最大能量存储值
	 */
	@Override
	public int getMaxEnergyStored() {
		return getEnergyStorageData().maxEnergyStored();
	}

	/**
	 * 设置最大能量存储值
	 *
	 * @param capacity 容量值
	 */
	@Override
	public void setMaxEnergyStored(int capacity) {
		setEnergyStorageData(getEnergyStorageData().setMaxEnergyStored(capacity));
	}

	/**
	 * 检查是否可以提取能量
	 *
	 * @return 是否可以提取能量
	 */
	@Override
	public boolean canExtract() {
		return getMaxExtract() > 0;
	}

	/**
	 * 检查是否可以接收能量
	 *
	 * @return 是否可以接收能量
	 */
	@Override
	public boolean canReceive() {
		return getMaxReceive() > 0;
	}

	/**
	 * 能量存储数据记录类
	 * 用于存储能量存储的相关数据
	 */
	public record EnergyStorageData(int maxEnergyStored, int maxReceive, int maxExtract,
	                                int energyStored) {
		/**
		 * 默认能量存储数据供应器
		 */
		public static final Supplier<EnergyStorageData> DEFAULT = () -> new EnergyStorageData(1000);

		/**
		 * 能量存储数据的流编解码器
		 */
		public static final StreamCodec<ByteBuf, EnergyStorageData> STREAM = StreamCodec.composite(
				ByteBufCodecs.VAR_INT, EnergyStorageData::maxEnergyStored,
				ByteBufCodecs.VAR_INT, EnergyStorageData::maxReceive,
				ByteBufCodecs.VAR_INT, EnergyStorageData::maxExtract,
				ByteBufCodecs.VAR_INT, EnergyStorageData::energyStored,
				EnergyStorageData::new);

		/**
		 * 能量存储数据的编解码器
		 */
		public static final Codec<EnergyStorageData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
						Codec.INT.fieldOf("maxEnergyStored").forGetter(EnergyStorageData::maxEnergyStored),
						Codec.INT.fieldOf("maxReceive").forGetter(EnergyStorageData::maxReceive),
						Codec.INT.fieldOf("maxExtract").forGetter(EnergyStorageData::maxExtract),
						Codec.INT.fieldOf("energyStored").forGetter(EnergyStorageData::energyStored))
				.apply(instance, EnergyStorageData::new));

		/**
		 * 构造函数，使用指定最大能量存储值创建能量存储数据
		 *
		 * @param maxEnergyStored 最大能量存储值
		 */
		public EnergyStorageData(int maxEnergyStored) {
			this(maxEnergyStored, maxEnergyStored);
		}

		/**
		 * 构造函数，使用指定最大能量存储值和最大传输值创建能量存储数据
		 *
		 * @param maxEnergyStored 最大能量存储值
		 * @param maxTransfer     最大传输值
		 */
		public EnergyStorageData(int maxEnergyStored, int maxTransfer) {
			this(maxEnergyStored, maxTransfer, maxTransfer);
		}

		/**
		 * 构造函数，使用指定参数创建能量存储数据
		 *
		 * @param maxEnergyStored 最大能量存储值
		 * @param maxReceive      最大接收值
		 * @param maxExtract      最大提取值
		 */
		public EnergyStorageData(int maxEnergyStored, int maxReceive, int maxExtract) {
			this(maxEnergyStored, maxReceive, maxExtract, 0);
		}

		/**
		 * 构造函数，从IModEnergyStorage创建能量存储数据
		 *
		 * @param energyStorage 能量存储实例
		 */
		public EnergyStorageData(IModEnergyStorage energyStorage) {
			this(energyStorage.getMaxEnergyStored(), energyStorage.getMaxReceive(), energyStorage.getMaxReceive(), energyStorage.getEnergyStored());
		}

		/**
		 * 构造函数，从IEnergyStorage创建能量存储数据
		 *
		 * @param energyStorage 能量存储实例
		 */
		public EnergyStorageData(IEnergyStorage energyStorage) {
			this(energyStorage.getMaxEnergyStored(), energyStorage.getMaxEnergyStored(), energyStorage.getMaxEnergyStored(), energyStorage.getEnergyStored());
		}

		/**
		 * 检查是否与另一个对象相等
		 *
		 * @param obj 比较对象
		 * @return 是否相等
		 */
		@Override
		public boolean equals(Object obj) {
			return switch (obj) {
				case ModEnergyStorage energyStorage -> energyStorage.getEnergyStored() == energyStored() &&
						energyStorage.getMaxEnergyStored() == maxEnergyStored() &&
						energyStorage.getMaxExtract() == maxExtract() &&
						energyStorage.getMaxReceive() == maxReceive();
				case null, default -> false;
			};
		}

		/**
		 * 设置能量存储值
		 *
		 * @param energyStored 能量存储值
		 * @return 新的能量存储数据实例
		 */
		public EnergyStorageData setEnergyStored(int energyStored) {
			return new EnergyStorageData(maxEnergyStored, maxReceive, maxExtract, energyStored);
		}

		/**
		 * 设置最大提取值
		 *
		 * @param maxExtract 最大提取值
		 * @return 新的能量存储数据实例
		 */
		public EnergyStorageData setMaxExtract(int maxExtract) {
			return new EnergyStorageData(maxEnergyStored, maxReceive, maxExtract, energyStored);
		}

		/**
		 * 设置最大接收值
		 *
		 * @param maxReceive 最大接收值
		 * @return 新的能量存储数据实例
		 */
		public EnergyStorageData setMaxReceive(int maxReceive) {
			return new EnergyStorageData(maxEnergyStored, maxReceive, maxExtract, energyStored);
		}

		/**
		 * 设置最大能量存储值
		 *
		 * @param maxEnergyStored 最大能量存储值
		 * @return 新的能量存储数据实例
		 */
		public EnergyStorageData setMaxEnergyStored(int maxEnergyStored) {
			return new EnergyStorageData(maxEnergyStored, maxReceive, maxExtract, energyStored);
		}

		/**
		 * 设置所有能量存储参数
		 *
		 * @param maxEnergyStored 最大能量存储值
		 * @param maxReceive      最大接收值
		 * @param maxExtract      最大提取值
		 * @param energyStored    能量存储值
		 * @return 新的能量存储数据实例
		 */
		public EnergyStorageData setEnergyStored(int maxEnergyStored, int maxReceive,
		                                         int maxExtract, int energyStored) {
			return new EnergyStorageData(maxEnergyStored, maxReceive, maxExtract, energyStored);
		}
	}
}