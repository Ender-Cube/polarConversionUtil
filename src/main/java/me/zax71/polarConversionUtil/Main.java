package me.zax71.polarConversionUtil;

import com.google.common.io.Files;
import net.hollowcube.polar.AnvilPolar;
import net.hollowcube.polar.PolarWorld;
import net.hollowcube.polar.PolarWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.URISyntaxException;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static Path getPath(String path) {
        try {
            return Path.of(new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath()).getParent().resolve(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("This is a utility to convert Anvil worlds to Polar:");

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        System.out.print("What is the name of the world you would like to convert: ");
        String worldFolder = in.nextLine();

        PolarWorld polarWorld;
        try {
            System.out.println("Converting world...");
            polarWorld = AnvilPolar.anvilToPolar(getPath(worldFolder));
            byte[] polarWorldBytes = PolarWriter.write(polarWorld);
            System.out.println("Writing to " + worldFolder + ".polar");
            Files.write(polarWorldBytes, getPath(worldFolder + ".polar").toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}