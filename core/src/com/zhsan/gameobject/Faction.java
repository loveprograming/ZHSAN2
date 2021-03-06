package com.zhsan.gameobject;

import com.badlogic.gdx.files.FileHandle;
import com.opencsv.CSVReader;
import com.zhsan.common.exception.FileReadException;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Peter on 14/3/2015.
 */
public class Faction extends GameObject {

    public static final String SAVE_FILE = "Faction.csv";

    private Faction(int id) {
        super(id);
    }

    public static final GameObjectList<Faction> fromCSVQuick(FileHandle root, int version) {
        GameObjectList<Faction> result = new GameObjectList<>();

        FileHandle f = root.child(SAVE_FILE);
        try (CSVReader reader = new CSVReader(new InputStreamReader(f.read()))) {
            String[] line;
            int index = 0;
            while ((line = reader.readNext()) != null) {
                index++;
                if (index == 1) continue; // skip first line.

                Faction t = new Faction(Integer.parseInt(line[0]));
                t.setName(line[3]);
                result.add(t);
            }

            return result;
        } catch (IOException e) {
            throw new FileReadException(f.path(), e);
        }
    }

    public int getId() {
        return super.getId();
    }

}
