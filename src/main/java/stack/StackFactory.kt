package stack

import com.almasb.fxgl.entity.*
import stack.control.StackMoveControl

@SetEntityFactory
class StackFactory : EntityFactory {

    @Spawns("Player")
    fun newPlayer(data: SpawnData): Entity {
        return Entities.builder()
                .type(StackType.BLOCK_ELEMENT)
                .from(data)
                .viewFromNode(StackBlock().get())
                .with(StackMoveControl(StackDirection.LEFT))
                .build()
    }
}
