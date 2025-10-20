package ctn.ctnapi.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * 方块实体工具类
 * 提供方块实体相关的实用方法
 */
public class BlockEntityUtil {
	/**
	 * 从世界中获取指定类型的方块实体
	 *
	 * @param <T>   方块实体的类型
	 * @param level 世界对象
	 * @param pos   方块位置
	 * @param type  方块实体类型
	 * @return 方块实体的可选包装
	 */
	public static <T extends BlockEntity> Optional<T> getBlockEntityFromLevel(Level level,
	                                                                          BlockPos pos,
	                                                                          BlockEntityType<T> type) {
		return level.getBlockEntity(pos, type);
	}

	/**
	 * 从世界中获取指定类型的方块实体，如果不存在则抛出异常
	 *
	 * @param <T>   方块实体的类型
	 * @param level 世界对象
	 * @param pos   方块位置
	 * @param type  方块实体类型
	 * @return 方块实体实例
	 * @throws IllegalStateException 当指定位置不存在方块实体时抛出
	 */
	public static @NotNull <T extends BlockEntity> T getBlockEntity(Level level,
	                                                                BlockPos pos,
	                                                                BlockEntityType<T> type) {
		Optional<T> blockEntity = getBlockEntityFromLevel(level, pos, type);
		if (blockEntity.isEmpty()) {
			throw new IllegalStateException("BlockEntity not found at " + pos);
		}
		return blockEntity.get();
	}

	/**
	 * 从世界中获取方块实体
	 *
	 * @param level 世界对象
	 * @param pos   方块位置
	 * @return 方块实体
	 */
	public static BlockEntity getBlockEntityFromLevel(Level level, BlockPos pos) {
		return level.getBlockEntity(pos);
	}
}