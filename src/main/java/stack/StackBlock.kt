package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class StackBlock(
        private val view: EntityView = EntityView(),
        offsetLeftCornerX: Double = 0.0,
        offsetLeftCornerY: Double = 0.0,
        offsetTopCornerX: Double = 0.0,
        offsetTopCornerY: Double = 0.0,
        offsetRightCornerX: Double = 0.0,
        offsetRightCornerY: Double = 0.0,
        offsetBottomCornerX: Double = 0.0,
        offsetBottomCornerY: Double = 0.0
) {

    private var leftCornerX: Double = 0.0
    private var leftCornerY: Double = 0.0
    private var topCornerX: Double = 0.0
    private var topCornerY: Double = 0.0
    private var rightCornerX: Double = 0.0
    private var rightCornerY: Double = 0.0
    private var bottomCornerX: Double = 0.0
    private var bottomCornerY: Double = 0.0

    companion object {
        const val width: Double = StackScale.stackBlockScale * StackScale.stackBlockWidth
        const val height: Double = StackScale.stackBlockScale * StackScale.stackBlockHeight
        const val thickness: Double = StackScale.stackBlockScale * StackScale.stackBlockThickness
    }

    init {
        leftCornerX   = offsetLeftCornerX   + 0.0
        leftCornerY   = offsetLeftCornerY   + height / 2.0
        topCornerX    = offsetTopCornerX    + width / 2.0
        topCornerY    = offsetTopCornerY    + 0.0
        rightCornerX  = offsetRightCornerX  + width
        rightCornerY  = offsetRightCornerY  + height / 2.0
        bottomCornerX = offsetBottomCornerX + width / 2.0
        bottomCornerY = offsetBottomCornerY + height
    }

    fun get(): EntityView {
        val color = Color.GRAY

        view.addNode(this.getBaseBlock(color))
        view.addNode(this.getBottomLeftBlock(color.darker().darker()))
        view.addNode(this.getBottomRightBlock(color.darker()))

        return this.view
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
