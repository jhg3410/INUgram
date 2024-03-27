package jik.inu.inugram.designsystem.component.textfield.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class EmailVisualTransformation : VisualTransformation {
    private fun String.toINUEmail() = "$this@inu.ac.kr"

    override fun filter(text: AnnotatedString): TransformedText {
        val origin = text.text
        val out = origin.toINUEmail()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                TODO("Not yet implemented")
            }

            override fun transformedToOriginal(offset: Int): Int {
                TODO("Not yet implemented")
            }
        }

        return TransformedText(
            text = AnnotatedString(out),
            offsetMapping = offsetMapping
        )
    }
}