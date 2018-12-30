package stack.control

import com.almasb.fxgl.app.FXGL
import com.almasb.fxgl.entity.Control
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.component.PositionComponent
import stack.StackBlock

class StackMoveControl : Control() {

    private val position: PositionComponent = PositionComponent()
    private var direction: Boolean = true

    override fun onUpdate(p0: Entity?, p1: Double) {
        position.x += 1.0 * (if (direction) 1 else -1)
        position.y += 1.0 * (if (direction) 1 else -1)

        val min = - StackBlock.width / 2.0
        val max = FXGL.getApp().width - StackBlock.width / 2.0

        if (
                position.x == min || position.y == min ||
                position.x == max || position.y == max
        ) {
            direction = !direction
        }
    }
}
