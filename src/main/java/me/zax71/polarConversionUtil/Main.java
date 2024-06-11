package me.zax71.polarConversionUtil;

import com.google.common.io.Files;
import net.hollowcube.polar.AnvilPolar;
import net.hollowcube.polar.PolarWorld;
import net.hollowcube.polar.PolarWriter;
import net.minestom.server.MinecraftServer;

import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.File;
import java.net.URISyntaxException;

import java.nio.file.Path;
import java.util.Arrays;
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

        System.out.println("Possible world folders:");
        System.out.println("    " + Arrays.toString(getSubdirectories(getPath("").toFile())));

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        System.out.print("What is the name of the world you would like to convert: ");
        String worldFolder = in.nextLine();

        // We need to have a Minestom server running
        MinecraftServer.init();

        PolarWorld polarWorld;
        try {
            System.out.println("Trying to find " +  getPath(worldFolder).resolve("region") + " and does it exist? " + getPath(worldFolder).resolve("region").toFile().exists());
            System.out.println("Converting world...");
            polarWorld = AnvilPolar.anvilToPolar(getPath(worldFolder));
            byte[] polarWorldBytes = PolarWriter.write(polarWorld);
            System.out.println("Writing to " + worldFolder + ".polar");
            Files.write(polarWorldBytes, getPath(worldFolder + ".polar").toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finished!");
        System.exit(0);
    }

    private static String[] getSubdirectories(File file) {
        return file.list((current, name) -> new File(current, name).isDirectory());
    }
}