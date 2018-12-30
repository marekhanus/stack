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
        val color = Color.GRAY

        view.addNode(this.getBaseBlock(color))
        view.addNode(this.getBottomLeftBlock(color.darker().darker()))
        view.addNode(this.getBottomRightBlock(color.darker()))
    }

    private fun getBaseBlock(color: Color): Polygon {
        val block = Polygon(
                0.0, height / 2.0, // left corner
                width / 2.0, 0.0, // top corner
                width, height / 2.0, // right corner
                width / 2.0, height // bottom corner
        )

        block.fill = color

        return block
    }

    private fun getBottomLeftBlock(color: Color): Polygon {
        val block = Polygon(
                0.0, height / 2.0, // top left corner
                width / 2.0, height, // top right corner
                width / 2.0, height + thickness, // bottom right corner
                0.0, height / 2.0 + thickness // bottom left corner
        )

        block.fill = color

        return block
    }

    private fun getBottomRightBlock(color: Color): Polygon {
        val block = Polygon(
                width / 2.0, height, // top left corner
                width, height / 2.0, // top right corner
                width, height / 2.0 + thickness, // bottom right corner
                width / 2.0, height + thickness // bottom left corner
        )

        block.fill = color

        return block
    }
}
