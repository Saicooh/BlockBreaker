package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

    private static AssetManager instance;

    private Texture extraLifeTexture;
    private Texture extraLifeNerfTexture;
    private Texture extraBallTexture;
    private Texture doubleScoreTexture;
    private Texture doubleScoreNerfTexture;
    private Texture fireBallTexture;
    private Texture paddleExtenderTexture;
    private Texture paddleExtenderNerfTexture;
    private Texture largePingBallTexture;
    private Texture backgroundTexture;

    public void loadAssets() {
        extraLifeTexture = new Texture("powerups/extralife.png");
        extraLifeNerfTexture = new Texture("powerups/extralifenerf.png");
        extraBallTexture = new Texture("powerups/extraball2.png");
        doubleScoreTexture = new Texture("powerups/doublescore.png");
        doubleScoreNerfTexture = new Texture("powerups/doublescorenerf.png");
        fireBallTexture = new Texture("powerups/fire.png");
        paddleExtenderTexture = new Texture("powerups/paddleextender.png");
        paddleExtenderNerfTexture = new Texture("powerups/paddleextendernerf.png");
        largePingBallTexture = new Texture("powerups/largepingball.png");
        backgroundTexture = new Texture("background.jpg");
    }

    public Texture getExtraLifeTexture() {
        return extraLifeTexture;
    }
    public Texture getExtraLifeNerfTexture() {
        return extraLifeNerfTexture;
    }
    public Texture getExtraBallTexture() {
        return extraBallTexture;
    }
    public Texture getDoubleScoreTexture() {
        return doubleScoreTexture;
    }
    public Texture getDoubleScoreNerfTexture() {
        return doubleScoreNerfTexture;
    }
    public Texture getFireBallTexture() {
        return fireBallTexture;
    }
    public Texture getPaddleExtenderTexture() {
        return paddleExtenderTexture;
    }
    public Texture getPaddleExtenderNerfTexture() {
        return paddleExtenderNerfTexture;
    }
    public Texture getLargePingBallTexture() {
        return largePingBallTexture;
    }
    public Texture getBackgroundTexture() { return backgroundTexture; }

    public void dispose()
    {
        extraLifeTexture.dispose();
        extraLifeNerfTexture.dispose();
        extraBallTexture.dispose();
        doubleScoreTexture.dispose();
        doubleScoreNerfTexture.dispose();
        fireBallTexture.dispose();
        paddleExtenderTexture.dispose();
        paddleExtenderNerfTexture.dispose();
        largePingBallTexture.dispose();
    }

    public static AssetManager getInstance()
    {
        if (instance == null) instance = new AssetManager();
        return instance;
    }
}
