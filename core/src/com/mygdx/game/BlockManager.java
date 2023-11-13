package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlockManager
{
    private final List<Block> blocks;

    public BlockManager() { blocks = new ArrayList<>(); }

    public void createBlocks(int rows, int blockWidth, int blockHeight)
    {
        blocks.clear();
        int y = Gdx.graphics.getHeight();
        for (int row = 0; row < rows; row++)
        {
            y -= (blockHeight + 10);

            for (int x = 5; x < Gdx.graphics.getWidth(); x += (blockWidth + 10))
                blocks.add(new Block(x, y, blockWidth, blockHeight));
        }
    }



    public List<Block> getBlocks() { return blocks; }

}
