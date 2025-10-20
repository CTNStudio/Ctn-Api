package ctn.ctnapi.client.gui.slice_sprite;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 切片精灵数据类
 * 用于存储九宫格切片渲染所需的参数和配置信息
 *
 * @author 尽
 * @since 1.0
 */
@OnlyIn(Dist.CLIENT)
public final class SliceSpriteData {

	/**
	 * 左侧切片宽度
	 */
	private final int left;

	/**
	 * 顶部切片高度
	 */
	private final int top;

	/**
	 * 右侧切片宽度
	 */
	private final int right;

	/**
	 * 底部切片高度
	 */
	private final int bottom;

	/**
	 * 纹理区域宽度
	 */
	private int uWidth;

	/**
	 * 纹理区域高度
	 */
	private int vHeight;

	/**
	 * 纹理U轴偏移量
	 */
	private int uOffset;

	/**
	 * 纹理V轴偏移量
	 */
	private int vOffset;

	/**
	 * 渲染宽度
	 */
	private int width;

	/**
	 * 渲染高度
	 */
	private int height;

	/**
	 * 构造函数，初始化切片精灵数据
	 *
	 * @param uWidth  纹理区域宽度
	 * @param vHeight 纹理区域高度
	 * @param uOffset 纹理U轴偏移量
	 * @param vOffset 纹理V轴偏移量
	 * @param width   渲染宽度
	 * @param height  渲染高度
	 * @param left    左侧切片宽度
	 * @param top     顶部切片高度
	 * @param right   右侧切片宽度
	 * @param bottom  底部切片高度
	 */
	public SliceSpriteData(
			int uWidth, int vHeight,
			int uOffset, int vOffset,
			int width, int height,
			int left, int top, int right, int bottom) {
		this.uWidth = uWidth;
		this.vHeight = vHeight;
		this.width = width;
		this.height = height;
		this.uOffset = uOffset;
		this.vOffset = vOffset;
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	/**
	 * 获取切片数据数组
	 * 返回一个3x3的二维数组，包含九宫格切片的各个部分数据
	 *
	 * @return 3x3的切片数据数组
	 */
	public SliceSpriteSliceData[][] getSliceData() {
		SliceSpriteSliceData[][] data = new SliceSpriteSliceData[3][3];
		data[0][0] = getUpBuilder(getLeft(), 0, getLeft());
		data[1][0] = getUpBuilder(getCenterUWidth(), getLeft(), getCenterWidth());
		data[2][0] = getUpBuilder(getRight(), getLeft() + getCenterUWidth(), getRight());

		data[0][1] = getCentreBuilder(getLeft(), 0, getLeft());
		data[1][1] = getCentreBuilder(getCenterUWidth(), getLeft(), getCenterWidth());
		data[2][1] = getCentreBuilder(getRight(), getLeft() + getCenterUWidth(), getRight());

		data[0][2] = getDownBuilder(getLeft(), 0, getLeft());
		data[1][2] = getDownBuilder(getCenterUWidth(), getLeft(), getCenterWidth());
		data[2][2] = getDownBuilder(getRight(), getLeft() + getCenterUWidth(), getRight());
		return data;
	}

	/**
	 * 创建顶部切片数据构建器
	 *
	 * @param uWidth  纹理宽度
	 * @param uOffset U轴偏移
	 * @param width   渲染宽度
	 * @return 切片数据
	 */
	private SliceSpriteSliceData getUpBuilder(int uWidth, int uOffset, int width) {
		return getImageDataBuilder()
				.uWidth(uWidth)
				.vHeight(getTop())
				.uOffset(getuOffset() + uOffset)
				.vOffset(getvOffset())
				.width(width)
				.height(getTop())
				.createSliceSpriteData();
	}

	/**
	 * 获取切片数据构建器实例
	 *
	 * @return 切片数据构建器
	 */
	private SliceSpriteSliceDataBuilder getImageDataBuilder() {
		return SliceSpriteSliceDataBuilder.build();
	}

	/**
	 * 创建中间切片数据构建器
	 *
	 * @param uWidth  纹理宽度
	 * @param uOffset U轴偏移
	 * @param width   渲染宽度
	 * @return 切片数据
	 */
	private SliceSpriteSliceData getCentreBuilder(int uWidth, int uOffset, int width) {
		return getImageDataBuilder()
				.uWidth(uWidth)
				.vHeight(getCenterUHeight())
				.uOffset(getuOffset() + uOffset)
				.vOffset(getvOffset() + getTop())
				.width(width)
				.height(getCenterHeight())
				.createSliceSpriteData();
	}

	/**
	 * 获取中间部分渲染高度
	 *
	 * @return 中间部分渲染高度
	 */
	private int getCenterHeight() {
		return getHeight() - getTop() - getBottom();
	}

	/**
	 * 获取中间部分纹理高度
	 *
	 * @return 中间部分纹理高度
	 */
	public int getCenterUHeight() {
		return getvHeight() - getTop() - getBottom();
	}

	/**
	 * 创建底部切片数据构建器
	 *
	 * @param uWidth  纹理宽度
	 * @param uOffset U轴偏移
	 * @param width   渲染宽度
	 * @return 切片数据
	 */
	private SliceSpriteSliceData getDownBuilder(int uWidth, int uOffset, int width) {
		return getImageDataBuilder()
				.uWidth(uWidth)
				.vHeight(getBottom())
				.uOffset(getuOffset() + uOffset)
				.vOffset(getvOffset() + getTop() + getCenterUHeight())
				.width(width)
				.height(getBottom())
				.createSliceSpriteData();
	}

	/**
	 * 获取中间部分渲染宽度
	 *
	 * @return 中间部分渲染宽度
	 */
	private int getCenterWidth() {
		return getWidth() - getLeft() - getRight();
	}

	/**
	 * 获取中间部分纹理宽度
	 *
	 * @return 中间部分纹理宽度
	 */
	public int getCenterUWidth() {
		return getuWidth() - getLeft() - getRight();
	}

	/**
	 * 获取纹理区域宽度
	 *
	 * @return 纹理区域宽度
	 */
	public int getuWidth() {
		return uWidth;
	}

	/**
	 * 设置纹理区域宽度
	 *
	 * @param uWidth 纹理区域宽度
	 */
	public void setuWidth(int uWidth) {
		this.uWidth = uWidth;
	}

	/**
	 * 获取纹理区域高度
	 *
	 * @return 纹理区域高度
	 */
	public int getvHeight() {
		return vHeight;
	}

	/**
	 * 设置纹理区域高度
	 *
	 * @param vHeight 纹理区域高度
	 */
	public void setvHeight(int vHeight) {
		this.vHeight = vHeight;
	}

	/**
	 * 获取渲染宽度
	 *
	 * @return 渲染宽度
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 设置渲染宽度
	 *
	 * @param width 渲染宽度
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 获取渲染高度
	 *
	 * @return 渲染高度
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 设置渲染高度
	 *
	 * @param height 渲染高度
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 获取纹理U轴偏移量
	 *
	 * @return 纹理U轴偏移量
	 */
	public int getuOffset() {
		return uOffset;
	}

	/**
	 * 设置纹理U轴偏移量
	 *
	 * @param uOffset 纹理U轴偏移量
	 */
	public void setuOffset(int uOffset) {
		this.uOffset = uOffset;
	}

	/**
	 * 获取纹理V轴偏移量
	 *
	 * @return 纹理V轴偏移量
	 */
	public int getvOffset() {
		return vOffset;
	}

	/**
	 * 设置纹理V轴偏移量
	 *
	 * @param vOffset 纹理V轴偏移量
	 */
	public void setvOffset(int vOffset) {
		this.vOffset = vOffset;
	}

	/**
	 * 获取左侧切片宽度
	 *
	 * @return 左侧切片宽度
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * 获取顶部切片高度
	 *
	 * @return 顶部切片高度
	 */
	public int getTop() {
		return top;
	}

	/**
	 * 获取右侧切片宽度
	 *
	 * @return 右侧切片宽度
	 */
	public int getRight() {
		return right;
	}

	/**
	 * 获取底部切片高度
	 *
	 * @return 底部切片高度
	 */
	public int getBottom() {
		return bottom;
	}
}