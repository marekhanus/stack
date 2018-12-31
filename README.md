# Stack Game

### Technologies

- Kotlin/JVM
- FXGL (using Maven)
- written in IntelliJ IDEA

### Environment

- fake 3D using 3x 2D polygons for blocks

### Run

- Using `main(...)` function in `src/main/java/stack/Stack.kt` (click play button in IntelliJ IDEA)

### Control

- Spacebar: for releasing enemies
- Keyboard & mouse

### Score

- Is sent to server after name is filled
- High score is downloaded from remote server

### Known bugs

- Using FXGL version 0.4.0 instead of newer for bad compatibility reasons (stack factory problems)
- Stack blocks are increasing size if placed behind previous block (all logic should be rewritten or I do not know)
