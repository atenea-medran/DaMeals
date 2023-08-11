package com.atenea.dameals.domain.model

import com.atenea.dameals.mealModelDataBuilder
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Test

class MealModelTest {
    private val mealModel = mealModelDataBuilder()


    @Test
    fun `WHEN create model EXPECT not null value`() {
        assertThat(mealModel, CoreMatchers.instanceOf(MealModel::class.java))
        assertThat(mealModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `WHEN mealModel id is 52772 EXPECT id = 52772`() {
        assertThat(mealModel.idMeal, CoreMatchers.`is`("52772"))
    }

    @Test
    fun `WHEN mealModel name is Teriyaki Chicken Casserole EXPECT name = Teriyaki Chicken Casserole`() {
        assertThat(mealModel.strMeal, CoreMatchers.`is`("Teriyaki Chicken Casserole"))
    }
}