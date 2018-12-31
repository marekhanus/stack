package stack

import javafx.application.Application
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.inc
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.input.UserAction
import com.almasb.fxgl.settings.GameSettings
import javafx.scene.input.KeyCode
import javafx.scene.text.Text
import stack.control.StackMoveControl

class Stack : GameApplication() {

    private var player: Entity? = null
    private var stackMoveControl: StackMoveControl? = null

    private var cropFromTopLeft: Double = 0.0
    private var cropFromTopRight: Double = 0.0
    private var cropFromBottomRight: Double = 0.0
    private var cropFromBottomLeft: Double = 0.0

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = (StackScale.gameSize * 0.5625).toInt()
            height = (StackScale.gameSize * 1.0).toInt()
            title = "Stack"
            version = "0.1"
            // other settings
        }
    }

    override fun initGameVars(vars: MutableMap<String, Any>?) {
        vars!!["score"] = 0
    }

    override fun initGame() {
        spawnPlayer()
        stackMoveControl?.setCenterPosition()
        stackMoveControl?.releaseBlock()

        spawnPlayer()
    }

    override fun initUI() {
        val textPixels = Text("0")
        textPixels.translateX = 50.0
        textPixels.translateY = 100.0

        gameScene.addUINode(textPixels)
    }

    override fun initInput() {
        input.addAction(object : UserAction("Release Block") {
            override fun onActionBegin() {
                stackMoveControl?.releaseBlock()

                inc("score", +50)

                cropFromTopLeft     = stackMoveControl?.getCropFromBottomRight()!!
                cropFromTopRight    = stackMoveControl?.getCropFromBottomLeft()!!
                cropFromBottomRight = stackMoveControl?.getCropFromTopLeft()!!
                cropFromBottomLeft  = stackMoveControl?.getCropFromTopRight()!!

                spawnPlayer()
                stackMoveControl?.crop(cropFromTopLeft, cropFromTopRight, cropFromBottomRight, cropFromBottomLeft)
            }
        }, KeyCode.SPACE)
    }

    private fun spawnPlayer() {
        player = gameWorld.spawn("Player")
        stackMoveControl = player?.getControl(StackMoveControl::class.java)
    }
}

fun main(args: Array<String>) {
    Application.launch(Stack::class.java, *args)
}
