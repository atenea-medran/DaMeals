package com.atenea.dameals.domain.model

import com.atenea.dameals.MealModelDataBuilder
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Test

class MealModelTest {
    private val mealModel = MealModelDataBuilder()


    @Test
    fun `WHEN create model EXPECT not null value`() {
        MatcherAssert.assertThat(mealModel, CoreMatchers.instanceOf(MealModel::class.java))
        MatcherAssert.assertThat(mealModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `WHEN mealModel id is 52772 EXPECT id = 52772`() {
        MatcherAssert.assertThat(mealModel.idMeal, CoreMatchers.`is`("52772"))
    }

    @Test
    fun `WHEN mealModel name is Teriyaki Chicken Casserole EXPECT name = Teriyaki Chicken Casserole`() {
        MatcherAssert.assertThat(mealModel.strMeal, CoreMatchers.`is`("Teriyaki Chicken Casserole"))
    }
}