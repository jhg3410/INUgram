package jik.inu.inugram.designsystem.component.textfield.visualtransformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import jik.inu.inugram.util.Empty

class EmailVisualTransformation : VisualTransformation {

    private companion object {
        const val INU_EMAIL_DOMAIN = "@inu.ac.kr"
    }

    private fun String.toINUEmail() = "$this$INU_EMAIL_DOMAIN"

    override fun filter(text: AnnotatedString): TransformedText {
        val origin = text.text
        val out = origin.toINUEmail()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset > origin.length) return origin.length
                return offset
            }
        }

        return TransformedText(
            text = AnnotatedString(if (origin.isEmpty()) String.Empty else out),
            offsetMapping = offsetMapping
        )
    }
}