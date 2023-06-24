package com.appiwedia.apps.android.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ExpressionEvaluatorTest {

    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `Simple expression properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(4)
    }

    @Test
    fun `Expression with decimal properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(2.5)
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(2.7)
    }

    @Test
    fun `Simple expression with parentheses is correctly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(2.0),
                ExpressionPart.Parentheses(ParenthesesType.Closing),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(1.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(5.0)
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(3.333333333333334)
    }
}