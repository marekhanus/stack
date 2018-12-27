package stack

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.net.URL
import java.util.Objects

class Main : Application() {

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(
            Objects.requireNonNull<URL>(javaClass.classLoader.getResource("stack/view.fxml"))
        )
        primaryStage.title = "Stack"
        primaryStage.scene = Scene(root, (1080 / 2).toDouble(), (1920 / 2).toDouble())
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
