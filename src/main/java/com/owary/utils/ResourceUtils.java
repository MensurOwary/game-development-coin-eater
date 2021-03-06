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
            double height = go.getHeight();
            double width = go.getWidth();
            String characterImage = go.getCharacterImage();
//            InputStream imageStream = ResourceUtils.class.getClassLoader().getResourceAsStream(characterImage);
            InputStream imageStream = ClassLoader.getSystemClassLoader().getResourceAsStream(characterImage);

            if (imageStream == null) {
                throw new NullPointerException();
            }

            Image scaledInstance = ImageIO.read(imageStream).getScaledInstance((int)width, (int)height, 0);
            scaledInstances.put(characterImage, scaledInstance);

            imageStream.close();

            return scaledInstance;
        }
        return scaledInstances.get(go.getCharacterImage());
    }

    public static Image getResourceOf(String resource, int height, int width) throws IOException {
        if (!scaledInstances.containsKey(resource)) {
            InputStream imageStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resource);

            if (imageStream == null) {
                throw new NullPointerException();
            }

            Image scaledInstance = ImageIO.read(imageStream).getScaledInstance(width, height, 0);
            scaledInstances.put(resource, scaledInstance);

            imageStream.close();

            return scaledInstance;
        }
        return scaledInstances.get(resource);
    }


}
