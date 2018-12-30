package stack.control

import com.almasb.fxgl.entity.Control
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.component.PositionComponent

class MoveControl : Control() {

    private val position: PositionComponent = PositionComponent(0.0, 0.0)

    override fun onUpdate(p0: Entity?, p1: Double) {
        position.x += 1.0
        position.y += 0.0
    }
}
