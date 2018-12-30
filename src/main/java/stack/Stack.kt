package stack

import javafx.application.Application
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.settings.GameSettings
import javafx.scene.shape.Rectangle
import com.almasb.fxgl.entity.Entities
import com.almasb.fxgl.entity.Entity
import javafx.scene.paint.Color


class Stack : GameApplication() {

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = 1080 / 4
            height = 1920 / 4
            title = "Stack"
            version = "0.1"
            // other settings
        }
    }

    private var player: Entity? = null

    override fun initGame() {
        player = Entities.builder()
                .at(100.0, 100.0)
                .viewFromNode(Rectangle(25.0, 25.0, Color.BLUE))
                .buildAndAttach(gameWorld)
    }
}

fun main(args: Array<String>) {
    Application.launch(Stack::class.java, *args)
}
