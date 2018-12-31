package stack.control

import com.almasb.fxgl.app.FXGL
import com.almasb.fxgl.entity.Control
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.component.PositionComponent
import stack.StackBlock
import stack.StackDirection
import stack.StackOrientation
import stack.StackScale

class StackMoveControl(
        private var direction: StackDirection,
        private var initPositionY: Double
) : Control() {

    private var isMovable: Boolean = true
    private val position: PositionComponent = PositionComponent()
    private var initPositionX: Double = (FXGL.getApp().width - StackScale.stackBlockScale * StackScale.stackBlockWidth) / 2.0
    private var orientation: StackOrientation = StackOrientation.DOWN

    private val leftCornerPositionX: Double = - StackBlock.width / 2.0
    private val rightCornerPositionX: Double = FXGL.getApp().width - StackBlock.width / 2.0
    private val leftCornerPositionY: Double = - StackBlock.height / 2.0
    private val rightCornerPositionY: Double = FXGL.getApp().height - StackBlock.height / 2.0

    private var offsetX: Double = 0.0
    private var offsetY: Double = 0.0

    override fun onAdded(entity: Entity?) {
//        position.x = initPositionX
        position.y = initPositionY
    }

    override fun onUpdate(p0: Entity?, p1: Double) {
        if (!isMovable) {
            return
        }

        offsetX += StackScale.stackBlockWidth * (if (direction == StackDirection.RIGHT) 1 else -1)
        offsetY += StackScale.stackBlockHeight * (if (orientation == StackOrientation.DOWN) 1 else -1)

        position.x = initPositionX + offsetX
        position.y = initPositionY + offsetY

        if (
                position.x <= leftCornerPositionX || position.y <= leftCornerPositionY ||
                position.x >= rightCornerPositionX || position.y >= rightCornerPositionY
        ) {
            direction = if (direction == StackDirection.LEFT) StackDirection.RIGHT else StackDirection.LEFT
            orientation = if (orientation == StackOrientation.DOWN) StackOrientation.UP else StackOrientation.DOWN
        }
    }

    fun setCenterPosition() {
        position.x += (FXGL.getApp().width - StackScale.stackBlockScale * StackScale.stackBlockWidth) / 2.0
        position.y -= ((FXGL.getApp().width - StackScale.stackBlockScale * StackScale.stackBlockWidth) / 2.0 - position.x) * StackScale.stackBlockHeight
    }

    fun releaseBlock() {
        val view = this.entity.view
        view.clearChildren()

        StackBlock(
                this.entity.view,
                getCropFromTopLeft(),
                getCropFromTopRight(),
                getCropFromBottomRight(),
                getCropFromBottomLeft()
        ).get()

        isMovable = false
    }

    fun getCropFromTopLeft(): Double {
        if ((
                (orientation == StackOrientation.UP && direction == StackDirection.LEFT) ||
                (orientation == StackOrientation.DOWN && direction == StackDirection.RIGHT)
        ) && offsetX < 0) {
            return Math.sqrt(offsetX * offsetX / 2.0)
        }

        return 0.0
    }

    fun getCropFromTopRight(): Double {
        if ((
                (orientation == StackOrientation.UP && direction == StackDirection.RIGHT) ||
                (orientation == StackOrientation.DOWN && direction == StackDirection.LEFT)
        ) && offsetX > 0) {
            return Math.sqrt(offsetX * offsetX / 2.0)
        }

        return 0.0
    }

    fun getCropFromBottomRight(): Double {

        if ((
                (orientation == StackOrientation.UP && direction == StackDirection.LEFT) ||
                (orientation == StackOrientation.DOWN && direction == StackDirection.RIGHT)
        ) && offsetX > 0) {
            return Math.sqrt(offsetX * offsetX / 2.0)
        }

        return 0.0
    }

    fun getCropFromBottomLeft(): Double {
        if ((
                (orientation == StackOrientation.UP && direction == StackDirection.RIGHT) ||
                (orientation == StackOrientation.DOWN && direction == StackDirection.LEFT)
        ) && offsetX < 0) {
            return Math.sqrt(offsetX * offsetX / 2.0)
        }

        return 0.0
    }
}
