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
        var direction: StackDirection,
        private var orientation: StackOrientation = StackOrientation.DOWN
) : Control() {

    private var isMovable: Boolean = true
    private val position: PositionComponent = PositionComponent()
    private val minPosition: Double = - StackBlock.width / 2.0
    private val maxPosition: Double = FXGL.getApp().width - StackBlock.width / 2.0

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

    fun releaseBlock() {
        isMovable = false
    }
}
