package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class StackBlock(private val view: EntityView = EntityView(), val size: Double = 50.0, val height: Double = 5.0) {

    fun get(): EntityView {
        return this.view
    }

    init {
        view.addNode(this.getBaseBlock())
        view.addNode(this.getBottomLeftBlock())
        view.addNode(this.getBottomRightBlock())
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

    private fun getBottomLeftBlock(): Polygon {
        val block = Polygon(
                0.0, size / 2.0, // top left corner
                size / 2.0, size, // top right corner
                size / 2.0, size + height, // bottom right corner
                0.0, size / 2.0 + height // bottom left corner
        )

        block.fill = Color.DARKSLATEGRAY

        return block
    }

    private fun getBottomRightBlock(): Polygon {
        val block = Polygon(
                size / 2.0, size, // top left corner
                size, size / 2.0, // top right corner
                size, size / 2.0 + height, // bottom right corner
                size / 2.0, size + height // bottom left corner
        )

        block.fill = Color.DARKGRAY

        return block
    }
}
