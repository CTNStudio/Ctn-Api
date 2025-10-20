package ctn.ctnapi.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * 模组工具类
 * 提供模组相关的通用实用方法
 */
public class ModUtil {
	/**
	 * 发送游戏内信息覆盖消息
	 *
	 * @param text 信息键值
	 * @param args 参数
	 */
	public static void sendOverlayMessage(String text, Object... args) {
		Minecraft.getInstance().gui.setOverlayMessage(Component.translatable(text, args), false);
	}

	/**
	 * 获取模组资源路径
	 *
	 * @param modId 模组ID
	 * @param name  资源名称
	 * @return 资源路径
	 */
	public static ResourceLocation modRL(String modId, String name) {
		return ResourceLocation.fromNamespaceAndPath(modId, name);
	}
}