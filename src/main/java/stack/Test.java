package stack;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.settings.GameSettings;

public class Test {
    public static class BasicGameApp extends GameApplication {

        @Override
        protected void initSettings(GameSettings settings) {
            settings.setWidth(800);
            settings.setHeight(600);
            settings.setTitle("Basic Game App");
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
