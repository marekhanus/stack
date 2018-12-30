package stack

import com.almasb.fxgl.entity.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import stack.control.BlockControl

@SetEntityFactory
class StackFactory : EntityFactory {

    @Spawns("Block")
    fun newBlock(data: SpawnData): Entity {
        return Entities.builder()
                .type(StackType.BLOCK_ELEMENT)
                .from(data)
                .viewFromNodeWithBBox(Rectangle(25.0, 25.0, Color.BLUE))
                .with(BlockControl())
                .build()
    }
}
