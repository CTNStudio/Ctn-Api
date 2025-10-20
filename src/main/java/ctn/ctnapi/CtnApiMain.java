package ctn.ctnapi;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ctn API 模组主类
 * 这是一个提供各种工具类和API的模组库
 */
@Mod(CtnApiMain.CAID)
public class CtnApiMain {
	/**
	 * 模组ID
	 */
	public static final String CAID = "ctn_api";

	/**
	 * 模组日志记录器
	 */
	public static final Logger LOGGER = LogManager.getLogger(CAID);

	/**
	 * 模组构造函数
	 * 在模组初始化时被调用
	 *
	 * @param modEventBus  模组事件总线
	 * @param modContainer 模组容器
	 */
	public CtnApiMain(IEventBus modEventBus, ModContainer modContainer) {
	}
}