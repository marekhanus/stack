package stack

import com.almasb.fxgl.app.FXGL
import javafx.application.Application
import com.almasb.fxgl.app.GameApplication
import com.almasb.fxgl.app.geti
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
    private var textPixels: Text? = null

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
        textPixels = Text(geti("score").toString())
        textPixels!!.translateX = FXGL.getApp().width - StackScale.scorePositionOffsetXY
        textPixels!!.translateY = StackScale.scorePositionOffsetXY

        gameScene.addUINode(textPixels)
    }

    override fun initInput() {
        input.addAction(object : UserAction("Release Block") {
            override fun onActionBegin() {
                if (stackMoveControl?.isGameOver()!!) {
                    val stackScoreUpdater = StackScoreUpdater()
                    stackScoreUpdater.downloadHighScore()

                    display.showMessageBox("Game over!\nYour score: " + geti("score").toString() + "\n" +
                        "High score: " + stackScoreUpdater.highScoreScore + "\n" +
                        "Master: " + stackScoreUpdater.highScoreName)

                    display.showInputBox("Your name:") {
                        answer -> stackScoreUpdater.sendScore(geti("score"), answer)
                    }

                    gameScene.clear()
                }

                stackMoveControl?.releaseBlock()

                inc("score", +1)

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
        textPixels?.text = geti("score").toString()
        player = gameWorld.spawn("Player")
        stackMoveControl = player?.getControl(StackMoveControl::class.java)
    }
}

fun main(args: Array<String>) {
    Application.launch(Stack::class.java, *args)
}
