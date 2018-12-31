package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class StackBlock(
        private val view: EntityView = EntityView()
) {
    private var leftCornerX: Double = 0.0
    private var leftCornerY: Double = height / 2.0
    private var topCornerX: Double = width / 2.0
    private var topCornerY: Double = 0.0
    private var rightCornerX: Double = width
    private var rightCornerY: Double = height / 2.0
    private var bottomCornerX: Double = width / 2.0
    private var bottomCornerY: Double = height

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

        if (false) {
            leftCornerX += Math.sqrt(2.0 * 10.0 * 10.0)
            leftCornerY += Math.sqrt(2.0 * 10.0 * 10.0) * StackScale.stackBlockHeight

            topCornerX += Math.sqrt(2.0 * 10.0 * 10.0)
            topCornerY += Math.sqrt(2.0 * 10.0 * 10.0) * StackScale.stackBlockHeight
        }

        if (false) { // crop from top right
            topCornerX -= Math.sqrt(2.0 * 10.0 * 10.0)
            topCornerY += Math.sqrt(2.0 * 10.0 * 10.0) * StackScale.stackBlockHeight

            rightCornerX -= Math.sqrt(2.0 * 10.0 * 10.0)
            rightCornerY += Math.sqrt(2.0 * 10.0 * 10.0) * StackScale.stackBlockHeight
        }

        if (false) { // crop from bottom right
            rightCornerX -= Math.sqrt(2.0 * 10.0 * 10.0)
            rightCornerY -= Math.sqrt(2.0 * 10.0 * 10.0) * StackScale.stackBlockHeight

            bottomCornerX -= Math.sqrt(2.0 * 10.0 * 10.0)
            bottomCornerY -= Math.sqrt(2.0 * 10.0 * 10.0) * StackScale.stackBlockHeight
        }

        if (false) { // crop from bottom left
            leftCornerX += Math.sqrt(2.0 * 10.0 * 10.0)
            leftCornerY -= Math.sqrt(2.0 * 10.0 * 10.0) * StackScale.stackBlockHeight

            bottomCornerX += Math.sqrt(2.0 * 10.0 * 10.0)
            bottomCornerY -= Math.sqrt(2.0 * 10.0 * 10.0) * StackScale.stackBlockHeight
        }

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
