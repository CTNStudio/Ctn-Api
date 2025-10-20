package ctn.ctnapi.common.payloads;

import ctn.ctnapi.adapter.ModEnergyStorage;
import ctn.ctnapi.capability.IModEnergyStorage;
import ctn.ctnapi.util.EnergyUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Objects;

/**
 * 能源存储数据包
 * 用于在网络间传输能量存储数据
 */
public abstract class EnergyStorageData implements CustomPacketPayload {
	/**
	 * 方块位置的流编解码器
	 */
	public static final StreamCodec<ByteBuf, BlockPos> POS_STREAM = StreamCodec.composite(
			ByteBufCodecs.VAR_INT, BlockPos::getX,
			ByteBufCodecs.VAR_INT, BlockPos::getY,
			ByteBufCodecs.VAR_INT, BlockPos::getZ,
			BlockPos::new);

	/**
	 * 能量存储的流编解码器
	 */
	public static final StreamCodec<ByteBuf, IModEnergyStorage> ENERGY_STORAGE_STREAM = StreamCodec.composite(
			ByteBufCodecs.VAR_INT, IModEnergyStorage::getMaxEnergyStored,
			ByteBufCodecs.VAR_INT, IModEnergyStorage::getMaxReceive,
			ByteBufCodecs.VAR_INT, IModEnergyStorage::getMaxExtract,
			ByteBufCodecs.VAR_INT, IModEnergyStorage::getEnergyStored,
			ModEnergyStorage::new);

	private final BlockPos pos;
	private final IModEnergyStorage iModEnergyStorage;

	/**
	 * 构造函数
	 *
	 * @param pos               方块位置
	 * @param iModEnergyStorage 能量存储实例
	 */
	public EnergyStorageData(BlockPos pos, IModEnergyStorage iModEnergyStorage) {
		this.pos = pos;
		this.iModEnergyStorage = iModEnergyStorage;
	}

	/**
	 * 发送到服务端
	 *
	 * @param data    能量存储数据
	 * @param context 数据包上下文
	 */
	public static void toServer(final EnergyStorageData data, final IPayloadContext context) {
	}

	/**
	 * 发送到客户端
	 *
	 * @param data    能量存储数据
	 * @param context 数据包上下文
	 */
	public static void toClient(final EnergyStorageData data, final IPayloadContext context) {
		context.enqueueWork(() -> {
			Player player = context.player();
			var capability = player.level().getCapability(Capabilities.EnergyStorage.BLOCK, data.pos, null);
			if (!(capability instanceof IModEnergyStorage iModEnergyStorage)) {
				return;
			}
			EnergyUtil.copyEnergy(iModEnergyStorage, data.iModEnergyStorage());
		});
	}

	/**
	 * 获取最大能量存储值
	 *
	 * @return 最大能量存储值
	 */
	public int maxEnergyStorage() {
		return iModEnergyStorage.getMaxEnergyStored();
	}

	/**
	 * 获取最大接收值
	 *
	 * @return 最大接收值
	 */
	public int maxReceive() {
		return iModEnergyStorage.getMaxReceive();
	}

	/**
	 * 获取最大提取值
	 *
	 * @return 最大提取值
	 */
	public int maxExtract() {
		return iModEnergyStorage.getMaxExtract();
	}

	/**
	 * 获取能量存储值
	 *
	 * @return 能量存储值
	 */
	public int energyStorage() {
		return iModEnergyStorage.getEnergyStored();
	}

	/**
	 * 获取X坐标
	 *
	 * @return X坐标
	 */
	public int getX() {
		return pos.getX();
	}

	/**
	 * 获取Y坐标
	 *
	 * @return Y坐标
	 */
	public int getY() {
		return pos.getY();
	}

	/**
	 * 获取Z坐标
	 *
	 * @return Z坐标
	 */
	public int getZ() {
		return pos.getZ();
	}

	/**
	 * 获取方块位置
	 *
	 * @return 方块位置
	 */
	public BlockPos pos() {
		return pos;
	}

	/**
	 * 获取能量存储实例
	 *
	 * @return 能量存储实例
	 */
	public IModEnergyStorage iModEnergyStorage() {
		return iModEnergyStorage;
	}

	/**
	 * 检查是否与另一个对象相等
	 *
	 * @param obj 比较对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (EnergyStorageData) obj;
		return Objects.equals(this.pos, that.pos) &&
				Objects.equals(this.iModEnergyStorage, that.iModEnergyStorage);
	}

	/**
	 * 获取哈希码
	 *
	 * @return 哈希码
	 */
	@Override
	public int hashCode() {
		return Objects.hash(pos, iModEnergyStorage);
	}

	/**
	 * 转换为字符串表示
	 *
	 * @return 字符串表示
	 */
	@Override
	public String toString() {
		return "EnergyStorageData[" +
				"pos=" + pos + ", " +
				"iModEnergyStorage=" + iModEnergyStorage + ']';
	}

	/**
	 * 获取数据包类型
	 *
	 * @return 数据包类型
	 */
	@Override
	public abstract Type<? extends CustomPacketPayload> type();
}