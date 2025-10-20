package ctn.ctnapi.client.gui.slice_sprite;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 切片精灵片段数据记录类
 * 用于存储单个切片的渲染数据
 *
 * @param width   切片宽度
 * @param height  切片高度
 * @param uOffset 纹理U轴偏移量
 * @param vOffset 纹理V轴偏移量
 * @param uWidth  纹理区域宽度
 * @param vHeight 纹理区域高度
 */
@OnlyIn(Dist.CLIENT)
public record SliceSpriteSliceData(int width, int height,
                                   int uOffset, int vOffset,
                                   int uWidth, int vHeight) {
	/**
	 * 绘制切片纹理
	 *
	 * @param texture     纹理资源位置
	 * @param guiGraphics GUI图形对象
	 * @param x           绘制X坐标
	 * @param y           绘制Y坐标
	 */
	public void blit(ResourceLocation texture, GuiGraphics guiGraphics, int x, int y) {
		this.blit(texture, guiGraphics, x, y, 256, 256);
	}

	/**
	 * 绘制切片纹理
	 *
	 * @param texture       纹理资源位置
	 * @param guiGraphics   GUI图形对象
	 * @param x             绘制X坐标
	 * @param y             绘制Y坐标
	 * @param textureWidth  纹理宽度
	 * @param textureHeight 纹理高度
	 */
	public void blit(ResourceLocation texture, GuiGraphics guiGraphics, int x, int y,
	                 int textureWidth, int textureHeight) {
		guiGraphics.blit(texture, x, y, width, height, uOffset, vOffset, uWidth, vHeight,
				textureWidth, textureHeight);
	}
}