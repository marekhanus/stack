package stack

import com.almasb.fxgl.app.FXGL
import com.almasb.fxgl.entity.*
import stack.control.StackMoveControl

@SetEntityFactory
class StackFactory : EntityFactory {

    private var position: Double = FXGL.getApp().height.toDouble() * StackScale.stackInitBlockPositionY - FXGL.getApp().width.toDouble() * StackScale.stackBlockHeight / 2.0
    private var direction: StackDirection = StackDirection.LEFT

    @Spawns("Player")
    fun newPlayer(data: SpawnData): Entity {
        position -= StackScale.stackBlockScale * StackScale.stackBlockThickness
        direction = if (direction == StackDirection.LEFT) StackDirection.RIGHT else StackDirection.LEFT
        val color = StackColor().get()

        if (position < StackScale.stackBlockScale * StackScale.stackBlockThickness) {
            position += StackScale.stackBlockScale * StackScale.stackBlockThickness

            FXGL.getApp().gameWorld.entities.forEach {
                it.positionComponent.y += StackScale.stackBlockScale * StackScale.stackBlockThickness
            }
        }

        return Entities.builder()
                .type(StackType.BLOCK_ELEMENT)
                .from(data)
                .viewFromNode(StackBlock().setColor(color).get())
                .with(StackMoveControl(direction, position, color))
                .build()
    }
}
