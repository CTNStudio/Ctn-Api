package ctn.ctnapi.util;

import ctn.ctnapi.CtnApiMain;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;

/**
 * 数据组件工具类
 * 提供数据组件与NBT标签之间转换的实用方法
 */
public class DataComponentUtil {
	/**
	 * 将数据组件映射转换为NBT标签
	 *
	 * @param map      数据组件映射
	 * @param provider 注册表访问器
	 * @return 包含所有数据组件的NBT复合标签
	 */
	public static CompoundTag getDataComponentNbt(DataComponentMap map, RegistryAccess provider) {
		var component = new CompoundTag();
		for (var entry : map) {
			getDataComponentNbt(provider, entry, component);
		}
		return component;
	}

	/**
	 * 将单个数据组件条目转换为NBT标签并添加到复合标签中
	 *
	 * @param provider 注册表访问器
	 * @param entry    数据组件条目
	 * @param compound 目标NBT复合标签
	 * @param <T>      数据组件值的类型
	 * @param <D>      数据组件类型的子类型
	 */
	public static <T, D extends DataComponentType<T>> void getDataComponentNbt(RegistryAccess provider, TypedDataComponent<T> entry, CompoundTag compound) {
		var type = (D) entry.type();
		var key = BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(type);
		var tCodec = type.codec();
		if (key == null || tCodec == null) {
			return;
		}
		var value = entry.value();
		try {
			compound.put(key.toString(), tCodec.encodeStart(provider.createSerializationContext(NbtOps.INSTANCE), value).getOrThrow());
		} catch (IllegalStateException e) {
			CtnApiMain.LOGGER.error("Failed to encode DataComponentType", e);
		}
	}
}