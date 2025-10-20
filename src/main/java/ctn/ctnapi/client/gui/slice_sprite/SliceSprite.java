package ctn.ctnapi.client.gui.slice_sprite;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 切片精灵类
 * 用于实现九宫格切片渲染功能，解决原版精灵渲染存在的问题
 *
 * @author 尽
 */
@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unused")
public class SliceSprite {
	/**
	 * 切片精灵数据对象
	 */
	private final SliceSpriteData sliceSpriteData;

	/**
	 * 切片数据二维数组
	 */
	private SliceSpriteSliceData[][] sliceData;

	/**
	 * 构造函数，创建一个切片精灵对象
	 *
	 * @param uWidth  纹理区域宽度
	 * @param vHeight 纹理区域高度
	 * @param width   渲染宽度
	 * @param height  渲染高度
	 * @param left    左侧切片宽度
	 * @param top     顶部切片高度
	 * @param right   右侧切片宽度
	 * @param bottom  底部切片高度
	 */
	public SliceSprite(
			int uWidth, int vHeight,
			int width, int height,
			int left, int top, int right, int bottom) {
		this(uWidth, vHeight, 0, 0, width, height, left, top, right, bottom);
	}

	/**
	 * 构造函数，创建一个切片精灵对象
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
	public SliceSprite(
			int uWidth, int vHeight,
			int uOffset, int vOffset,
			int width, int height,
			int left, int top, int right, int bottom) {
		this(new SliceSpriteData(
				uWidth, vHeight,
				uOffset, vOffset,
				width, height,
				left, top, right, bottom));
	}

	/**
	 * 构造函数，创建一个切片精灵对象
	 *
	 * @param sliceSpriteData 切片精灵数据对象
	 */
	public SliceSprite(SliceSpriteData sliceSpriteData) {
		this.sliceSpriteData = sliceSpriteData;
		this.sliceData = sliceSpriteData.getSliceData();
	}

	/**
	 * 使用九宫格切片方式绘制图像
	 *
	 * @param texture     纹理资源位置
	 * @param guiGraphics GUI图形对象
	 * @param x           绘制X坐标
	 * @param y           绘制Y坐标
	 */
	public void blitNineSliced(ResourceLocation texture, GuiGraphics guiGraphics, int x, int y) {
		blitNineSliced(texture, guiGraphics, x, y, 256, 256);
	}

	/**
	 * 使用九宫格切片方式绘制图像，并支持指定纹理大小
	 *
	 * @param texture       纹理资源位置
	 * @param guiGraphics   GUI图形对象
	 * @param x             绘制X坐标
	 * @param y             绘制Y坐标
	 * @param textureWidth  纹理宽度
	 * @param textureHeight 纹理高度
	 */
	public void blitNineSliced(ResourceLocation texture, GuiGraphics guiGraphics, int x, int y,
	                           int textureWidth, int textureHeight) {
		int sliceX = x;
		for (SliceSpriteSliceData[] sliceDatum : sliceData) {
			int width = sliceDatum[0].width();
			if (width <= 0) {
				continue;
			}
			int sliceY = y;

			for (SliceSpriteSliceData slice : sliceDatum) {
				int height = slice.height();
				if (height <= 0) {
					continue;
				}
				slice.blit(texture, guiGraphics, sliceX, sliceY, textureWidth, textureHeight);
				sliceY += height;
			}

			sliceX += width;
		}
	}

	/**
	 * 获取纹理区域宽度
	 *
	 * @return 纹理区域宽度
	 */
	public int getuWidth() {
		return sliceSpriteData.getuWidth();
	}

	/**
	 * 获取纹理区域高度
	 *
	 * @return 纹理区域高度
	 */
	public int getvHeight() {
		return sliceSpriteData.getvHeight();
	}

	/**
	 * 获取左侧切片宽度
	 *
	 * @return 左侧切片宽度
	 */
	public int getLeft() {
		return sliceSpriteData.getLeft();
	}

	/**
	 * 获取顶部切片高度
	 *
	 * @return 顶部切片高度
	 */
	public int getTop() {
		return sliceSpriteData.getTop();
	}

	/**
	 * 获取右侧切片宽度
	 *
	 * @return 右侧切片宽度
	 */
	public int getRight() {
		return sliceSpriteData.getRight();
	}

	/**
	 * 获取底部切片高度
	 *
	 * @return 底部切片高度
	 */
	public int getBottom() {
		return sliceSpriteData.getBottom();
	}

	/**
	 * 获取切片精灵数据对象
	 *
	 * @return 切片精灵数据对象
	 */
	public SliceSpriteData getSliceSpriteData() {
		return sliceSpriteData;
	}

	/**
	 * 获取渲染宽度
	 *
	 * @return 渲染宽度
	 */
	public int getWidth() {
		return sliceSpriteData.getWidth();
	}

	/**
	 * 设置渲染宽度
	 *
	 * @param renderingWidth 渲染宽度
	 */
	public void setWidth(int renderingWidth) {
		if (getWidth() == renderingWidth) {
			return;
		}
		sliceSpriteData.setWidth(renderingWidth);
		update();
	}

	/**
	 * 获取渲染高度
	 *
	 * @return 渲染高度
	 */
	public int getHeight() {
		return sliceSpriteData.getHeight();
	}

	/**
	 * 设置渲染高度
	 *
	 * @param renderingHeight 渲染高度
	 */
	public void setHeight(int renderingHeight) {
		if (getHeight() == renderingHeight) {
			return;
		}
		sliceSpriteData.setHeight(renderingHeight);
		update();
	}

	/**
	 * 获取纹理U轴偏移量
	 *
	 * @return 纹理U轴偏移量
	 */
	public int getUOffset() {
		return sliceSpriteData.getuOffset();
	}

	/**
	 * 设置纹理U轴偏移量
	 *
	 * @param textureUOffset 纹理U轴偏移量
	 */
	public void setUOffset(int textureUOffset) {
		if (getUOffset() == textureUOffset) {
			return;
		}
		sliceSpriteData.setuOffset(textureUOffset);
		update();
	}

	/**
	 * 获取纹理V轴偏移量
	 *
	 * @return 纹理V轴偏移量
	 */
	public int getVOffset() {
		return sliceSpriteData.getvOffset();
	}

	/**
	 * 设置纹理V轴偏移量
	 *
	 * @param textureVOffset 纹理V轴偏移量
	 */
	public void setVOffset(int textureVOffset) {
		if (getVOffset() == textureVOffset) {
			return;
		}
		sliceSpriteData.setvOffset(textureVOffset);
		update();
	}

	/**
	 * 更新切片数据
	 */
	public void update() {
		this.sliceData = sliceSpriteData.getSliceData();
	}
}