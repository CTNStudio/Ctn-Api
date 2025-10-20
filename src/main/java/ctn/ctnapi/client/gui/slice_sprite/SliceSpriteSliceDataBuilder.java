package ctn.ctnapi.client.gui.slice_sprite;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 切片精灵片段数据构建器类
 * 用于构建切片精灵片段数据对象
 */
@OnlyIn(Dist.CLIENT)
public class SliceSpriteSliceDataBuilder {
	// 使用 ThreadLocal 缓存 builder 实例
	private static final ThreadLocal<SliceSpriteSliceDataBuilder> BUILDER_THREAD_LOCAL =
			ThreadLocal.withInitial(SliceSpriteSliceDataBuilder::new);

	private int width;
	private int height;
	private int uOffset;
	private int vOffset;
	private int uWidth;
	private int vHeight;

	/**
	 * 默认构造函数
	 */
	public SliceSpriteSliceDataBuilder() {
	}

	/**
	 * 获取构建器实例
	 *
	 * @return 切片精灵片段数据构建器实例
	 */
	public static SliceSpriteSliceDataBuilder build() {
		SliceSpriteSliceDataBuilder builder = BUILDER_THREAD_LOCAL.get();
		builder.reset(); // 重置状态
		return builder;
	}

	// 添加 reset 方法用于重置 builder 状态
	private void reset() {
		this.width = 0;
		this.height = 0;
		this.uOffset = 0;
		this.vOffset = 0;
		this.uWidth = 0;
		this.vHeight = 0;
	}

	/**
	 * 设置切片宽度
	 *
	 * @param width 切片宽度
	 * @return 构建器实例
	 */
	public SliceSpriteSliceDataBuilder width(int width) {
		this.width = width;
		return this;
	}

	/**
	 * 设置切片高度
	 *
	 * @param height 切片高度
	 * @return 构建器实例
	 */
	public SliceSpriteSliceDataBuilder height(int height) {
		this.height = height;
		return this;
	}

	/**
	 * 设置纹理U轴偏移量
	 *
	 * @param uOffset 纹理U轴偏移量
	 * @return 构建器实例
	 */
	public SliceSpriteSliceDataBuilder uOffset(int uOffset) {
		this.uOffset = uOffset;
		return this;
	}

	/**
	 * 设置纹理V轴偏移量
	 *
	 * @param vOffset 纹理V轴偏移量
	 * @return 构建器实例
	 */
	public SliceSpriteSliceDataBuilder vOffset(int vOffset) {
		this.vOffset = vOffset;
		return this;
	}

	/**
	 * 设置纹理区域宽度
	 *
	 * @param uWidth 纹理区域宽度
	 * @return 构建器实例
	 */
	public SliceSpriteSliceDataBuilder uWidth(int uWidth) {
		this.uWidth = uWidth;
		return this;
	}

	/**
	 * 设置纹理区域高度
	 *
	 * @param vHeight 纹理区域高度
	 * @return 构建器实例
	 */
	public SliceSpriteSliceDataBuilder vHeight(int vHeight) {
		this.vHeight = vHeight;
		return this;
	}

	/**
	 * 创建切片精灵片段数据对象
	 *
	 * @return 切片精灵片段数据对象
	 */
	public SliceSpriteSliceData createSliceSpriteData() {
		return new SliceSpriteSliceData(width, height, uOffset, vOffset, uWidth, vHeight);
	}
}