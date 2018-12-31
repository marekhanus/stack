package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class StackBlock(
        private val view: EntityView = EntityView(),
        private val cropFromTopLeft: Double = 0.0,
        private val cropFromTopRight: Double = 0.0,
        private val cropFromBottomRight: Double = 0.0,
        private val cropFromBottomLeft: Double = 0.0
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

        if (cropFromTopLeft > 0.0) {
            val crop: Double = Math.sqrt(2.0 * cropFromTopLeft * cropFromTopLeft)

            leftCornerX += crop
            leftCornerY += crop * StackScale.stackBlockHeight

            topCornerX += crop
            topCornerY += crop * StackScale.stackBlockHeight
        }

        if (cropFromTopRight > 0.0) {
            val crop: Double = Math.sqrt(2.0 * cropFromTopRight * cropFromTopRight)

            topCornerX -= crop
            topCornerY += crop * StackScale.stackBlockHeight

            rightCornerX -= crop
            rightCornerY += crop * StackScale.stackBlockHeight
        }

        if (cropFromBottomRight > 0.0) {
            val crop: Double = Math.sqrt(2.0 * cropFromBottomRight * cropFromBottomRight)

            rightCornerX -= crop
            rightCornerY -= crop * StackScale.stackBlockHeight

            bottomCornerX -= crop
            bottomCornerY -= crop * StackScale.stackBlockHeight
        }

        if (cropFromBottomLeft > 0.0) {
            val crop: Double = Math.sqrt(2.0 * cropFromBottomLeft * cropFromBottomLeft)

            leftCornerX += crop
            leftCornerY -= crop * StackScale.stackBlockHeight

            bottomCornerX += crop
            bottomCornerY -= crop * StackScale.stackBlockHeight
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
