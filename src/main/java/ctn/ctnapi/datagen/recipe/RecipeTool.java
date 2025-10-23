package ctn.ctnapi.datagen.recipe;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.Map;

/**
 * @author 尽
 */
@SuppressWarnings("UnusedReturnValue")
public interface RecipeTool {
	/**
	 * 创建剑形配方模式
	 * '#' 主要材料， 'O' 次要材料
	 */
	static ShapedBuilder swordFramePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("#")
				.pattern("#")
				.pattern("O");
	}

	/**
	 * 创建斧形配方模式
	 * '#' 主要材料， 'O' 次要材料
	 */
	static ShapedBuilder axeFramePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("##")
				.pattern("#O")
				.pattern(" O");
	}

	/**
	 * 创建镐形配方模式
	 * '#' 主要材料， 'O' 次要材料
	 */
	static ShapedBuilder pickaxeFramePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("###")
				.pattern(" O ")
				.pattern(" O ");
	}

	/**
	 * 创建锹形配方模式
	 * '#' 主要材料， 'O' 次要材料
	 */
	static ShapedBuilder shovelFramePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("#")
				.pattern("O")
				.pattern("O");
	}

	/**
	 * 创建锄形配方模式
	 * '#' 主要材料， 'O' 次要材料
	 */
	static ShapedBuilder hoeFramePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("##")
				.pattern(" O")
				.pattern(" O");
	}

	/**
	 * 创建头盔配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder helmetPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("###")
				.pattern("# #");
	}

	/**
	 * 创建胸甲配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder chestplatePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("# #")
				.pattern("###")
				.pattern("###");
	}

	/**
	 * 创建护腿配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder leggingsPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("###")
				.pattern("# #")
				.pattern("# #");
	}

	/**
	 * 创建靴子配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder bootsPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("# #")
				.pattern("# #");
	}

	/**
	 * 创建盾牌配方模式
	 * '#' 主要材料， 'O' 中心材料
	 */
	static ShapedBuilder shieldPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("#O#")
				.pattern("###")
				.pattern(" # ");
	}

	/**
	 * 创建核心框架配方模式
	 * '#' 外部材料， 'O' 内部材料
	 */
	static ShapedBuilder coreFramePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("###")
				.pattern("#O#")
				.pattern("###");
	}

	/**
	 * 创建环形框架配方模式
	 * '#' 外部材料
	 */
	static ShapedBuilder circularFramePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("###")
				.pattern("# #")
				.pattern("###");
	}

	/**
	 * 创建台阶配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder singlePlatePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("###");
	}

	/**
	 * 创建杆状配方模式（如长棍）
	 * '#' 主要材料
	 */
	static ShapedBuilder rodPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("#")
				.pattern("#")
				.pattern("#");
	}

	/**
	 * 创建楼梯形配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder stairPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("#  ")
				.pattern("## ")
				.pattern("###");
	}

	/**
	 * 创建栅栏配方模式
	 * '#' 主要材料， 'O' 次要材料
	 */
	static ShapedBuilder fencePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("#O#")
				.pattern("#O#");
	}

	/**
	 * 创建栅栏门配方模式
	 * '#' 主要材料， 'O' 次要材料
	 */
	static ShapedBuilder fenceDoorPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("O#O")
				.pattern("O#O");
	}

	/**
	 * 创建活板门配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder trapdoorPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("###")
				.pattern("###");
	}

	/**
	 * 创建门配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder doorPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("##")
				.pattern("##")
				.pattern("##");
	}

	/**
	 * 创建压力板配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder pressurePlatePattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("##");
	}

	/**
	 * 创建木棍配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder stickPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("#")
				.pattern("#");
	}

	/**
	 * 创建火把配方模式
	 * '#' 可燃物， 'O' 次要材料
	 */
	static ShapedBuilder torchPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("#")
				.pattern("O");
	}

	/**
	 * 创建3x3全包裹式配方模式
	 * '#' 主要材料
	 */
	static ShapedBuilder fullWrapPattern(ShapedBuilder builder) {
		return builder.clearPattern()
				.pattern("###")
				.pattern("###")
				.pattern("###");
	}

	static Ingredient getIngredient(ItemLike item) {
		return Ingredient.of(item);
	}

	static Ingredient getIngredient(TagKey<Item> tag) {
		return Ingredient.of(tag);
	}

	static void requires(ShapedBuilder builder, Map<Character, Ingredient> requires,
	                     String group, RecipeOutput recipeOutput) {
		defineRequires(builder, requires)
				.basicUnlockedBy()
				.group(group)
				.save(recipeOutput);
	}

	/**
	 * 附上材料
	 */
	static ShapedBuilder defineRequires(ShapedBuilder builder,
	                                    Map<Character, Ingredient> requires) {
		requires.forEach(builder::define);
		return builder;
	}
}
