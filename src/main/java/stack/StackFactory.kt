package stack

import com.almasb.fxgl.entity.*
import stack.control.StackMoveControl

@SetEntityFactory
class StackFactory : EntityFactory {

    private var direction: StackDirection = StackDirection.LEFT

    @Spawns("Player")
    fun newPlayer(data: SpawnData): Entity {
        direction = if (direction == StackDirection.LEFT) StackDirection.RIGHT else StackDirection.LEFT

        return Entities.builder()
                .type(StackType.BLOCK_ELEMENT)
                .from(data)
                .viewFromNode(StackBlock().get())
                .with(StackMoveControl(direction))
                .build()
    }
}
