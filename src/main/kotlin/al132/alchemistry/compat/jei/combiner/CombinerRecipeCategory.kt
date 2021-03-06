package al132.alchemistry.compat.jei.combiner

import al132.alchemistry.client.GuiChemicalCombiner
import al132.alchemistry.compat.jei.AlchemistryRecipeCategory
import al132.alchemistry.compat.jei.AlchemistryRecipeUID
import al132.alchemistry.compat.jei.Translator
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.item.ItemStack

class CombinerRecipeCategory(guiHelper: IGuiHelper) :
        AlchemistryRecipeCategory<CombinerRecipeWrapper>
        (guiHelper.createDrawable(guiTexture, u, v, 159 - u, 70 - v), "jei.combiner.name") {

    companion object {

        private val INPUT_SIZE = 9
        private val OUTPUT_SLOT = 9

        private val u = 35
        private val v = 10

        private val guiTexture = GuiChemicalCombiner.textureLocation()
    }

    override fun getTitle() = Translator.translateToLocal("jei.combiner.name")

    override fun getUid(): String = AlchemistryRecipeUID.COMBINER

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: CombinerRecipeWrapper, ingredients: IIngredients) {
        val guiItemStacks = recipeLayout.itemStacks
        val startX = 38 - u
        var x = startX
        var y = 13 - v
        var index = 0
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                guiItemStacks.init(index, true, x, y)
                index++
                x += 18
            }
            x = startX
            y += 18
        }


        for (i in 0 until INPUT_SIZE) {
            guiItemStacks.set(i, ingredients.getInputs(ItemStack::class.java)[i])
        }

        x = 139 - u
        y = 32 - v

        guiItemStacks.init(OUTPUT_SLOT, false, x, y)
        //guiItemStacks.set(OUTPUT_SLOT, ingredients.getOutputs(ItemStack::class.java)[0])
        guiItemStacks.set(OUTPUT_SLOT, recipeWrapper.recipe.output)
    }
}