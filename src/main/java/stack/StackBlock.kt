package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class StackBlock(
        private val view: EntityView = EntityView(),
        private val width: Double = StackScale.stackBlock * 1.0,
        private val height: Double = StackScale.stackBlock * 0.7,
        private val thickness: Double = StackScale.stackBlock * 0.1
) {

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
                0.0, height / 2.0, // left corner
                width / 2.0, 0.0, // top corner
                width, height / 2.0, // right corner
                width / 2.0, height // bottom corner
        )

        block.fill = Color.GRAY

        return block
    }

    private fun getBottomLeftBlock(): Polygon {
        val block = Polygon(
                0.0, height / 2.0, // top left corner
                width / 2.0, height, // top right corner
                width / 2.0, height + thickness, // bottom right corner
                0.0, height / 2.0 + thickness // bottom left corner
        )

        block.fill = Color.DARKSLATEGRAY

        return block
    }

    private fun getBottomRightBlock(): Polygon {
        val block = Polygon(
                width / 2.0, height, // top left corner
                width, height / 2.0, // top right corner
                width, height / 2.0 + thickness, // bottom right corner
                width / 2.0, height + thickness // bottom left corner
        )

        block.fill = Color.DARKGRAY

        return block
    }
}
