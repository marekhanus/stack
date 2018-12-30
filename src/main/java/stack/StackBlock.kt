package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class StackBlock(
        private val view: EntityView = EntityView(),
        private var leftCornerX: Double = 0.0,
        private var leftCornerY: Double = 0.0,
        private var topCornerX: Double = 0.0,
        private var topCornerY: Double = 0.0,
        private var rightCornerX: Double = 0.0,
        private var rightCornerY: Double = 0.0,
        private var bottomCornerX: Double = 0.0,
        private var bottomCornerY: Double = 0.0

) {
    companion object {
        const val width: Double = StackScale.stackBlockScale * StackScale.stackBlockWidth
        const val height: Double = StackScale.stackBlockScale * StackScale.stackBlockHeight
        const val thickness: Double = StackScale.stackBlockScale * StackScale.stackBlockThickness
    }

    fun get(): EntityView {
        return this.view
    }

    init {
        val color = Color.GRAY

        leftCornerX = 0.0
        leftCornerY = height / 2.0

        topCornerX = width / 2.0
        topCornerY = 0.0

        rightCornerX = width
        rightCornerY = height / 2.0

        bottomCornerX = width / 2.0
        bottomCornerY = height

        view.addNode(this.getBaseBlock(color))
        view.addNode(this.getBottomLeftBlock(color.darker().darker()))
        view.addNode(this.getBottomRightBlock(color.darker()))
    }

    private fun getBaseBlock(color: Color): Polygon {
        val block = Polygon(
                leftCornerX, leftCornerY,
                topCornerX, topCornerY,
                rightCornerX, rightCornerY,
                bottomCornerX, bottomCornerY
        )

        block.fill = color

        return block
    }

    private fun getBottomLeftBlock(color: Color): Polygon {
        val block = Polygon(
                leftCornerX, leftCornerY, // top left corner
                bottomCornerX, bottomCornerY, // top right corner
                bottomCornerX, bottomCornerY + thickness, // bottom right corner
                leftCornerX, leftCornerY + thickness // bottom left corner
        )

        block.fill = color

        return block
    }

    private fun getBottomRightBlock(color: Color): Polygon {
        val block = Polygon(
                bottomCornerX, bottomCornerY, // top left corner
                rightCornerX, rightCornerY, // top right corner
                rightCornerX, rightCornerY + thickness, // bottom right corner
                bottomCornerX, bottomCornerY + thickness // bottom left corner
        )

        block.fill = color

        return block
    }
}
