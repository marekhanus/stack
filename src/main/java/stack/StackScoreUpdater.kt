package stack

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class StackScoreUpdater {
    var highScoreScore: Int? = null
    var highScoreName: String? = null

    @Throws(IOException::class)
    fun sendScore(score: Int, name: String): Boolean {
        val url = URL("http://stack.marekhanus.cz/?update_score=" + score.toString() + "&update_name=" + name)

        val stream = BufferedReader(
                InputStreamReader(url.openStream())
        )

        var inputLine: String?
        while (true) {
            inputLine = stream.readLine()

            if (inputLine == null) {
                break
            }
        }

        return true
    }

    fun downloadHighScore() {
        val url = URL("http://stack.marekhanus.cz/?get_score")

        val stream = BufferedReader(
                InputStreamReader(url.openStream())
        )

        var inputLine: String?
        while (true) {
            inputLine = stream.readLine()

            if (inputLine == null) {
                break
            }

            val line = inputLine.split(';')
            highScoreScore = line[0].toInt()
            highScoreName = line[1]

        }
    }
}
