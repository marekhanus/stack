package stack

import com.almasb.fxgl.entity.view.EntityView
import javafx.scene.Node
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class StackBlock(private val view: EntityView = EntityView(), val size: Int = 50) {

    fun get(): Node? {
        return this.view
    }

    init {
        view.addNode(Rectangle(20.0, 10.0, Color.BLUE))
        view.addNode(Rectangle(10.0, 20.0, Color.RED))
    }
}
