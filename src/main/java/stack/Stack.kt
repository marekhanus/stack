package stack

import javafx.application.Application
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.input.UserAction
import com.almasb.fxgl.settings.GameSettings
import javafx.scene.input.KeyCode
import stack.control.StackMoveControl

class Stack : GameApplication() {

    private var player: Entity? = null
    private var stackMoveControl: StackMoveControl? = null

    override fun initSettings(settings: GameSettings) {
        with(settings) {
            width = (StackScale.gameSize * 0.5625).toInt()
            height = (StackScale.gameSize * 1.0).toInt()
            title = "Stack"
            version = "0.1"
            // other settings
        }
    }

    override fun initGame() {
        spawnPlayer()
        stackMoveControl?.setCenterPosition()
        stackMoveControl?.releaseBlock()

        spawnPlayer()
    }

    override fun initInput() {
        input.addAction(object : UserAction("Release Block") {
            override fun onActionBegin() {
                stackMoveControl?.releaseBlock()
                spawnPlayer()
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
