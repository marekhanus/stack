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

    private var color: Color? = null

    companion object {
        const val width: Double = StackScale.stackBlockScale * StackScale.stackBlockWidth
        const val height: Double = StackScale.stackBlockScale * StackScale.stackBlockHeight
        const val thickness: Double = StackScale.stackBlockScale * StackScale.stackBlockThickness
    }

    init {}

    fun setColor(color: Color?): StackBlock {
        this.color = color
        return this
    }

    fun get(): EntityView {
        crop()

        if (color == null) {
            color = StackColor().get()
        }

        view.addNode(this.getBaseBlock(color!!))
        view.addNode(this.getBottomLeftBlock(color!!.darker().darker()))
        view.addNode(this.getBottomRightBlock(color!!.darker()))

        return this.view
    }

    private fun crop() {
        if (cropFromTopLeft > 0.0) {
            val cropX: Double = Math.sqrt(2.0 * cropFromTopLeft * cropFromTopLeft)
            val cropY: Double = cropX * StackScale.stackBlockHeight

            leftCornerX += cropX
            leftCornerY += cropY

            topCornerX += cropX
            topCornerY += cropY
        }

        if (cropFromTopRight > 0.0) {
            val cropX: Double = Math.sqrt(2.0 * cropFromTopRight * cropFromTopRight)
            val cropY: Double = cropX * StackScale.stackBlockHeight

            topCornerX -= cropX
            topCornerY += cropY

            rightCornerX -= cropX
            rightCornerY += cropY
        }

        if (cropFromBottomRight > 0.0) {
            val cropX: Double = Math.sqrt(2.0 * cropFromBottomRight * cropFromBottomRight)
            val cropY: Double = cropX * StackScale.stackBlockHeight

            rightCornerX -= cropX
            rightCornerY -= cropY

            bottomCornerX -= cropX
            bottomCornerY -= cropY
        }

        if (cropFromBottomLeft > 0.0) {
            val cropX: Double = Math.sqrt(2.0 * cropFromBottomLeft * cropFromBottomLeft)
            val cropY: Double = cropX * StackScale.stackBlockHeight

            leftCornerX += cropX
            leftCornerY -= cropY

            bottomCornerX += cropX
            bottomCornerY -= cropY
        }
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
