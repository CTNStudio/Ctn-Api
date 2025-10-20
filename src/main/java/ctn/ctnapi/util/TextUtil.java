package ctn.ctnapi.util;

/**
 * 文本工具类
 * 提供文本处理相关的实用方法
 */
public class TextUtil {
	/**
	 * 将数字转换为带单位的文本
	 *
	 * @param value 数字
	 * @return 转换后的文本
	 */
	public static String getDigitalText(long value) {
		int valueTextLength = String.valueOf(Math.abs(value)).length();
		if (valueTextLength >= 12) {
			return formatNumber(value / 1000000000000.0, "T");
		} else if (valueTextLength >= 10) {
			return formatNumber(value / 1000000000.0, "G");
		} else if (valueTextLength >= 7) {
			return formatNumber(value / 1000000.0, "M");
		} else if (valueTextLength >= 4) {
			return formatNumber(value / 1000.0, "K");
		}
		return String.valueOf(value);
	}

	/**
	 * 分割数字
	 *
	 * @param value  数字
	 * @param length 分割长度
	 * @param symbol 分割符号
	 * @return 分割后的数字
	 */
	public static String divideDigital(long value, int length, String symbol) {
		final var text = new StringBuilder(String.valueOf(value));
		final int textLength = text.length();
		for (int i = textLength - length; i > 0; i -= length) {
			text.insert(i, symbol);
		}
		return text.toString();
	}

	/**
	 * 格式化数字并添加单位
	 *
	 * @param value 需要格式化的数值
	 * @param unit  要添加的单位字符串
	 * @return 格式化后的带单位字符串
	 */
	public static String formatNumber(double value, String unit) {
		if (Math.abs(value - Math.round(value)) < 1e-10) {
			return String.format("%.0f%s", value, unit);
		}
		String formatted = String.format("%.2f", value);
		formatted = formatted.replaceAll("\\.?0+$", "");
		return formatted + " " + unit;
	}

	/**
	 * 将游戏刻度转换为时间格式
	 * 每20刻度等于1秒
	 *
	 * @param ticks 游戏刻度数
	 * @return 格式化后的时间字符串
	 */
	public static String formatGameTime(long ticks) {
		long seconds = ticks / 20;
		long minutes = seconds / 60;
		long hours = minutes / 60;

		if (hours > 0) {
			return String.format("%dh %dm %ds", hours, minutes % 60, seconds % 60);
		} else if (minutes > 0) {
			return String.format("%dm %ds", minutes, seconds % 60);
		} else {
			return String.format("%ds", seconds);
		}
	}
}