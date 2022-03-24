import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    static StringBuilder log = new StringBuilder();

    public static void main(String[] args) {
        GameProgress gameOne = new GameProgress(98, 3, 4, 5);
        GameProgress gameTwo = new GameProgress(28, 76, 3, 4);
        GameProgress gameTree = new GameProgress(36, 64, 3, 4);
        String strGameOne = "C:\\Game\\savegames\\gameOne.dat";
        String strGameTwo = "C:\\Game\\savegames\\gameTwo.dat";
        String strGameTree = "C:\\Game\\savegames\\gameTree.dat";
        saveGames(strGameOne, gameOne);
        saveGames(strGameTwo, gameTwo);
        saveGames(strGameTree, gameTree);
        List<String> gameList = new ArrayList<>();
        gameList.add(strGameOne);
        gameList.add(strGameTwo);
        gameList.add(strGameTree);
        String zipArr = "C:\\Game\\savegames\\ZipArr.zip";
        zipFiles(zipArr, gameList);
        File one = new File(strGameOne);
        deleteFiles(one);
        log.append("Название файла gameOne.dat.\n");
        File two = new File(strGameTwo);
        deleteFiles(two);
        log.append("Название файла gameTwo.dat.\n");
        File tree = new File(strGameTree);
        deleteFiles(tree);
        log.append("Название файла gameTree.dat.\n");
        System.out.println(log.toString());
    }

    public static void saveGames(String string, GameProgress gameProgress) {
        try (FileOutputStream serFile = new FileOutputStream(string);
             ObjectOutputStream oos = new ObjectOutputStream(serFile)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String string, List<String> list) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(string))) {
            for (String item : list) {
                try (FileInputStream fiz = new FileInputStream(item)) {
                    ZipEntry zipE = new ZipEntry(item);
                    zip.putNextEntry(zipE);
                    byte[] buff = new byte[fiz.available()];
                    fiz.read(buff);
                    zip.write(buff);
                    zip.closeEntry();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFiles(File file) {
        if (file.delete())
            log.append("Файл удален. ");
    }
}

