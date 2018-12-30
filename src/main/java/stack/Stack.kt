package stack

import javafx.application.Application
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.settings.GameSettings

class Stack : GameApplication() {

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = 800
            height = 600
            title = "Basic Game App"
            version = "0.1"
            // other settings
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(Stack::class.java, *args)
}
