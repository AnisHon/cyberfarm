package team.light.cyberfarm.tool;

import jakarta.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import team.light.cyberfarm.exception.FileTooLargeException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class FileUploadUtil {
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 2;
    public static List<String> saveImage(Collection<Part> parts, String filePath) throws FileTooLargeException {
        for (Part part : parts) {
            if (part.getSize() > MAX_FILE_SIZE) {
                throw new FileTooLargeException("MAX_FILE_SIZE = 2MB");
            }
        }

        ArrayList<String> arrayList = new ArrayList<>();
        for (Part part : parts) {
            if (part.getSubmittedFileName() != null) {
                String fileName = new Date().getTime() + part.getSubmittedFileName();
                try (FileOutputStream fos = new FileOutputStream(filePath + fileName)) {
                    IOUtils.copy(part.getInputStream() ,fos);
                    arrayList.add(fileName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
       return arrayList;
    }

}
