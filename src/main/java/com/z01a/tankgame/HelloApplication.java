package com.z01a.tankgame;

import com.z01a.engine.Engine;
import com.z01a.engine.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Engine m_Engine = null;
    @Override
    public void start(Stage stage) throws IOException {
        m_Engine = new Engine(stage);
        m_Engine.Initialize();
        m_Engine.Start();
    }

    @Override
    public void stop() throws Exception {
        m_Engine.Stop();
        m_Engine.UnInitialize();
        m_Engine = null;
    }

    public static void main(String[] args) {
        launch();
    }
}