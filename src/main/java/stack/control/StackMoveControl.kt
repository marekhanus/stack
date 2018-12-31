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
        private val initPositionY: Double
) : Control() {

    private var isMovable: Boolean = true
    private val position: PositionComponent = PositionComponent()
    private var orientation: StackOrientation = StackOrientation.DOWN

    private val leftCornerPositionX: Double = - StackBlock.width / 2.0
    private val rightCornerPositionX: Double = FXGL.getApp().width - StackBlock.width / 2.0
    private val leftCornerPositionY: Double = - StackBlock.height / 2.0
    private val rightCornerPositionY: Double = FXGL.getApp().height - StackBlock.height / 2.0

    override fun onAdded(entity: Entity?) {
        position.y = initPositionY

        if (direction == StackDirection.RIGHT) {
            position.y -= StackScale.stackBlockScale * StackScale.stackBlockHeight / 2.0
        }
    }

    override fun onUpdate(p0: Entity?, p1: Double) {
        if (!isMovable) {
            return
        }

        position.x += StackScale.stackBlockWidth * (if (direction == StackDirection.RIGHT) 1 else -1)
        position.y += StackScale.stackBlockHeight * (if (orientation == StackOrientation.DOWN) 1 else -1)

        if (
                position.x <= leftCornerPositionX || position.y <= leftCornerPositionY ||
                position.x >= rightCornerPositionX || position.y >= rightCornerPositionY
        ) {
            direction = if (direction == StackDirection.LEFT) StackDirection.RIGHT else StackDirection.LEFT
            orientation = if (orientation == StackOrientation.DOWN) StackOrientation.UP else StackOrientation.DOWN
        }
    }

    fun setCenterPosition() {
        val oldPositionX = position.x
        position.x += (FXGL.getApp().width - StackScale.stackBlockScale * StackScale.stackBlockWidth) / 2.0
        position.y -= (oldPositionX - position.x) * StackScale.stackBlockHeight
    }

    fun releaseBlock() {
        isMovable = false
    }
}
