package ctn.ctnapi.adapter;

import com.google.common.collect.Lists;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 非空物品列表类
 * 扩展了NonNullList，确保列表中不包含空物品栈
 */
public class NonEmptyItemList extends NonNullList<ItemStack> {
	/**
	 * 构造函数
	 *
	 * @param list         物品栈列表
	 * @param defaultValue 默认值
	 */
	protected NonEmptyItemList(List<ItemStack> list, @Nullable ItemStack defaultValue) {
		super(list, defaultValue);
	}

	/**
	 * 创建标准集合
	 *
	 * @return 空的NonEmptyItemList实例
	 */
	public static NonEmptyItemList create() {
		return new NonEmptyItemList(Lists.newArrayList(), null);
	}

	/**
	 * 创建指定容量的集合
	 *
	 * @param initialCapacity 初始容量
	 * @return 指定容量的NonEmptyItemList实例
	 */
	public static NonEmptyItemList createFixedCapacity(int initialCapacity) {
		return new NonEmptyItemList(Lists.newArrayListWithCapacity(initialCapacity), null);
	}

	/**
	 * 创建指定数量的集合并初始
	 *
	 * @param size         集合大小
	 * @param defaultValue 默认物品栈
	 * @return 初始化的NonEmptyItemList实例
	 */
	public static NonEmptyItemList createFixedCapacity(int size, ItemStack defaultValue) {
		Objects.requireNonNull(defaultValue);
		ItemStack[] aobject = new ItemStack[size];
		Arrays.fill(aobject, defaultValue);
		return new NonEmptyItemList(Arrays.asList(aobject), defaultValue);
	}

	/**
	 * 创建包含指定元素的NonEmptyItemList
	 *
	 * @param defaultValue 默认值
	 * @param elements     元素数组
	 * @return 包含指定元素的NonEmptyItemList实例
	 */
	public static NonEmptyItemList create(ItemStack defaultValue, ItemStack... elements) {
		return new NonEmptyItemList(Arrays.asList(elements), defaultValue);
	}

	/**
	 * 从NBT标签加载所有物品
	 *
	 * @param tag           NBT标签
	 * @param items         物品列表
	 * @param levelRegistry 等级注册表提供者
	 */
	public static void loadAllItems(CompoundTag tag, NonNullList<ItemStack> items, HolderLookup.Provider levelRegistry) {
		ListTag listtag = tag.getList("Items", 10);

		for (int i = 0; i < listtag.size(); i++) {
			CompoundTag compoundtag = listtag.getCompound(i);
			if (items.isEmpty()) {
				items.add(ItemStack.EMPTY);
			}
			items.add(ItemStack.parse(levelRegistry, compoundtag).orElse(ItemStack.EMPTY));
		}
	}

	/**
	 * 设置指定索引处的物品栈
	 *
	 * @param index 索引
	 * @param value 物品栈值
	 * @return 之前的物品栈
	 */
	@Override
	public @NotNull ItemStack set(int index, @NotNull ItemStack value) {
		if (index < 0) {
			index = 0;
		}
		ItemStack itemStack = super.set(index, value);
		add(0, ItemStack.EMPTY);
		update();
		return itemStack;
	}

	/**
	 * 在指定索引处添加物品栈
	 *
	 * @param index 索引
	 * @param value 物品栈值
	 */
	@Override
	public void add(int index, ItemStack value) {
		if (index < 0) {
			index = 0;
		}
		super.add(index, value);
		update();
	}

	/**
	 * 更新列表，确保不包含空物品栈
	 */
	public void update() {
		if (!get(0).isEmpty()) {
			add(0, ItemStack.EMPTY);
		}
		for (int i = size() - 1; i >= 1; i--) {
			if (get(i).isEmpty()) {
				remove(i);
			}
		}
	}
}