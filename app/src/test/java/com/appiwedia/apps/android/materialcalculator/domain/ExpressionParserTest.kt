package com.appiwedia.apps.android.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`() {
        parser = ExpressionParser("3+5-3x4/3")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Simple expression with parentheses is correctly parsed`() {
        parser = ExpressionParser("(4x2)+1/3-5")

        val actual = parser.parse()

        val expected = listOf(
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

        assertThat(actual).isEqualTo(expected)
    }
}