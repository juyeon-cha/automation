package utils.Helpers;

import io.appium.java_client.ios.IOSDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Base64;

public class FileHandlers {

    public static String getRootPath(){
        return System.getProperty("user.dir");
    }
    public void stopRecording(IOSDriver iosDriver, String screenName) {
        String rawData = iosDriver.stopRecordingScreen();
        screenName = screenName.substring(screenName.lastIndexOf(".")+1);
        String fileName = "[iOS] BAT" + "_" + screenName;
        saveRecording(rawData, fileName);
    }

    public FileOutputStream saveRecording(String rawData, String fileName) {
        byte[] decodedBytes = Base64.getDecoder().decode(rawData);
        try{
            FileOutputStream mp4File = new FileOutputStream(getRootPath() + "/reports/" + fileName + ".mpr");
            mp4File.write(decodedBytes);
            mp4File.close();
            return mp4File;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
