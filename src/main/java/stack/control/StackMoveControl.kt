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
    private val minPosition: Double = - StackBlock.width / 2.0
    private val maxPosition: Double = FXGL.getApp().width - StackBlock.width / 2.0

    override fun onAdded(entity: Entity?) {
        position.y = initPositionY

        if (direction == StackDirection.LEFT) {
            position.y -= StackScale.stackBlockScale * StackScale.stackBlockHeight / 2.0
        }
    }

    override fun onUpdate(p0: Entity?, p1: Double) {
        if (!isMovable) {
            return
        }

        position.x += StackScale.stackBlockWidth * (if (direction == StackDirection.LEFT) 1 else -1)
        position.y += StackScale.stackBlockHeight * (if (orientation == StackOrientation.DOWN) 1 else -1)

        if (
                position.x == minPosition || position.y == minPosition ||
                position.x == maxPosition || position.y == maxPosition
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
