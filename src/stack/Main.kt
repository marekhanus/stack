package stack

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.stage.Stage
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Rectangle

class Main : Application() {

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Stack"
        val root = Group()
        val canvas = Canvas((1080 / 2).toDouble(), (1920 / 2).toDouble())
        val gc = canvas.graphicsContext2D
        gc.fill = Color.GREEN
        gc.stroke = Color.BLUE

        root.children.add(canvas)
        primaryStage.scene = Scene(root)
        primaryStage.show()

        val r = Rectangle()
        r.x = 50.0
        r.y = 50.0
        r.width = 200.0
        r.height = 100.0
        r.arcWidth = 20.0
        r.arcHeight = 20.0
        r.fill = Paint.valueOf("blue")

        gc.fillRect(10.0,10.0,10.0,10.0)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
