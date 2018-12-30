package stack

import com.almasb.fxgl.entity.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import stack.control.BlockControl
import stack.control.MoveControl

@SetEntityFactory
class StackFactory : EntityFactory {

    @Spawns("Player")
    fun newPlayer(data: SpawnData): Entity {
        return Entities.builder()
                .type(StackType.BLOCK_ELEMENT)
                .from(data)
                .viewFromNode(StackBlock().get())
                .with(BlockControl())
                .with(MoveControl())
                .build()
    }

    @Spawns("Block")
    fun newBlock(data: SpawnData): Entity {
        return Entities.builder()
                .type(StackType.BLOCK_ELEMENT)
                .from(data)
                .viewFromNodeWithBBox(Rectangle(10.0, 10.0, Color.GREEN))
                .with(BlockControl())
                .build()
    }
}
