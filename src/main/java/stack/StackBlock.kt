package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class StackBlock(private val view: EntityView = EntityView(), val size: Int = 50) {

    fun get(): EntityView {
        return this.view
    }

    init {
        view.addNode(this.getBaseBlock())
    }

    private fun getBaseBlock(): Polygon {
        val block = Polygon(
                10.0, 10.0,
                10.0, 20.0,
                20.0, 20.0,
                20.0, 10.0
        )

        block.fill = Color.GRAY

        return block
    }
}
