package com.github.gauthierj.java.console.tetris.music;

import static com.github.gauthierj.java.console.tetris.music.MusicConstant.*;

public class Korobeiniki extends MusicImpl {

    public Korobeiniki() {
        super(new int[][] {
                      {SILENCE, 48},
                      {E5, 48}, {B4, 24}, {C5, 24},
                      {D5, 48}, {C5, 24}, {B4, 24},
                      {A4, 48}, {A4, 24}, {C5, 24},
                      {E5, 48}, {D5, 24}, {C5, 24},
                      {B4, 60}, {C5, 24},
                      {D5, 48}, {E5, 48},
                      {C5, 48}, {A4, 48},
                      {A4, 48}, {SILENCE, 24}, {D5, 48}, {F5, 24},
                      {A5, 48}, {G5, 24}, {F5, 24},
                      {E5, 60}, {C5, 24}, {E5, 48},
                      {D5, 24}, {C5, 24}, {B4, 48},
                      {B4, 24}, {C5, 24}, {D5, 48},
                      {E5, 48}, {C5, 48}, {A4, 48},
                      {A4, 48}
              });
    }
}
