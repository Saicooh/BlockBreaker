package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager
{

    private final Sound collisionSound;
    private final Sound gameOverSound;

    public SoundManager()
    {
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("sfx/pop.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sfx/gameover.wav"));
    }

    public void play(String sound)
    {
        switch (sound)
        {
            case "collision": collisionSound.play(); break;
            case "gameover": gameOverSound.play(); break;
        }
    }

    public void dispose()
    {
        collisionSound.dispose();
        gameOverSound.dispose();
    }
}