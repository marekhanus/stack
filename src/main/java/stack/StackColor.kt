package stack

import com.almasb.fxgl.core.math.FXGLMath
import javafx.scene.paint.Color

class StackColor {

    private val colors = listOf<Color>(Color.GRAY, Color.GREEN)

    fun get(): Color {
        return FXGLMath.random(colors).get()
    }
}
