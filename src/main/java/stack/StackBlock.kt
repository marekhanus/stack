package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class StackBlock(private val view: EntityView = EntityView(), val size: Double = 50.0) {

    fun get(): EntityView {
        return this.view
    }

    init {
        view.addNode(this.getBaseBlock())
    }

    private fun getBaseBlock(): Polygon {
        val block = Polygon(
                0.0, size / 2.0, // left corner
                size / 2.0, 0.0, // top corner
                size, size / 2.0, // right corner
                size / 2.0, size // bottom corner
        )

        block.fill = Color.GRAY

        return block
    }
}
