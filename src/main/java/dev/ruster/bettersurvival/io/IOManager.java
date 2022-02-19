package dev.ruster.bettersurvival.io;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IOManager {

    private IOManager() {
        throw new IllegalStateException("Utility Class");
    }

    public static void write(@NotNull String path, String content) {
        File f = new File(path);

        /*if(!f.exists()) {
            throw new NullPointerException("File " + f + " doesn't exists");
        }*/
        try(FileWriter fw = new FileWriter(f)) {
            fw.write(content);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
