package com.github.gauthierj.java.console.tetris.music;

import javax.sound.midi.*;

import static com.github.gauthierj.java.console.tetris.music.MusicConstant.*;

public class MusicImpl implements Music {

    private final int[][] notes;

    private Sequencer sequencer;
    private boolean playing = false;

    public MusicImpl(int[][] notes) {
        this.notes = notes;
    }

    @Override
    public void init() {
        try {
            this.sequencer = MidiSystem.getSequencer();
            sequencer.open();
            // noire = 24
            Sequence sequence = new Sequence(Sequence.PPQ, 24);
            Track track = sequence.createTrack();

            // Définit l’instrument (piano) sur le canal 0 (instrument #1 = Acoustic Grand Piano)
            ShortMessage programChange = new ShortMessage();
            programChange.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 1, 0);
            track.add(new MidiEvent(programChange, 0));

            int tempoBPM = 240;
            MetaMessage tempoMessage = new MetaMessage();
            int tempo = 60000000 / tempoBPM;
            byte[] data = {
                    (byte) ((tempo >> 16) & 0xFF),
                    (byte) ((tempo >> 8) & 0xFF),
                    (byte) (tempo & 0xFF)
            };
            tempoMessage.setMessage(A5, data, 3);
            track.add(new MidiEvent(tempoMessage, 0));

            int tick = 0;
            for (int[] note : notes) {
                int noteNumber = note[0];
                int duration = note[1];

                if (noteNumber >= 0) {
                    // Note ON
                    ShortMessage on = new ShortMessage();
                    on.setMessage(ShortMessage.NOTE_ON, 0, noteNumber, 100);
                    track.add(new MidiEvent(on, tick));

                    // Note OFF
                    ShortMessage off = new ShortMessage();
                    off.setMessage(ShortMessage.NOTE_OFF, 0, noteNumber, 100);
                    track.add(new MidiEvent(off, tick + duration));
                }

                tick += duration;
            }

            // Charge la séquence dans le séquenceur et joue
            sequencer.setSequence(sequence);
            sequencer.setLoopStartPoint(0);         // début de la boucle au tick 0
            sequencer.setLoopEndPoint(sequencer.getSequence().getTickLength() - 1); // fin de la boucle
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            throw new IllegalStateException("Could not init MIDI sequencer", e);
        }
    }

    @Override
    public void play() {
        this.sequencer.start();
        this.playing = true;
    }

    @Override
    public void pause() {
        this.sequencer.stop();
        this.playing = false;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void close() {
        this.sequencer.close();
    }
}