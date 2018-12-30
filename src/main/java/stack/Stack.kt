package stack

import javafx.application.Application
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.input.UserAction
import com.almasb.fxgl.settings.GameSettings
import javafx.scene.input.KeyCode

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

    override fun initGame() {
        gameWorld.spawn("Block")
    }

    override fun initInput() {
        input.addAction(object : UserAction("Release Block") {
            override fun onActionBegin() {
                TODO("not implemented")
            }
        }, KeyCode.SPACE)
    }
}

fun main(args: Array<String>) {
    Application.launch(Stack::class.java, *args)
}
