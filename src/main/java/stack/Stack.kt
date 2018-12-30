package stack

import javafx.application.Application
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.settings.GameSettings

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
    }
}

fun main(args: Array<String>) {
    Application.launch(Stack::class.java, *args)
}
