package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager
{

    private final Sound collisionSound;
    private final Sound gameOverSound;
    private final Sound paddleHitSound;
    private final Sound paddleHitSound2;
    private final Sound finishSound;

    public SoundManager()
    {
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("sfx/pop.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sfx/gameover.wav"));
        paddleHitSound = Gdx.audio.newSound(Gdx.files.internal("sfx/paddlehit.wav"));
        paddleHitSound2 = Gdx.audio.newSound(Gdx.files.internal("sfx/paddlehit2.wav"));
        finishSound = Gdx.audio.newSound(Gdx.files.internal("sfx/finish.wav"));
    }

    public void play(String sound, float volume)
    {
        switch (sound) {
            case "collision": collisionSound.play(volume); break;
            case "gameover": gameOverSound.play(volume); break;
            case "paddleHit": paddleHitSound.play(volume); break;
            case "paddleHit2": paddleHitSound2.play(volume); break;
            case "finish": finishSound.play(volume); break;
        }
    }

    public void dispose()
    {
        collisionSound.dispose();
        gameOverSound.dispose();
    }
}