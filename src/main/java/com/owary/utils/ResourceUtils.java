package com.owary.utils;

import com.owary.model.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ResourceUtils {

    private static Map<String, Image> scaledInstances = new HashMap<>();

    public static Image getResourceOf(GameObject go) throws IOException, NullPointerException {
        if (!scaledInstances.containsKey(go.getCharacterImage())) {
            int height = go.getHeight();
            int width = go.getWidth();
            String characterImage = go.getCharacterImage();
//            InputStream imageStream = ResourceUtils.class.getClassLoader().getResourceAsStream(characterImage);
            InputStream imageStream = ClassLoader.getSystemClassLoader().getResourceAsStream(characterImage);

            if (imageStream == null) {
                throw new NullPointerException();
            }

            Image scaledInstance = ImageIO.read(imageStream).getScaledInstance(width, height, 0);
            scaledInstances.put(characterImage, scaledInstance);

            imageStream.close();

            return scaledInstance;
        }
        return scaledInstances.get(go.getCharacterImage());
    }


}
