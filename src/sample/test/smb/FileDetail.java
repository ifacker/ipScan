package sample.test.smb;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: $
 * @Author: ifacker
 * @Date: $
 */
public class FileDetail {
    private String FileName;
    private String LastModified;
    private boolean isFile;
    private boolean isFolder;
    private boolean exists;
    private String type;
    private long length;
    private SimpleDateFormat fmt;
    public FileDetail(File file) {
        isFile = file.isFile();
        isFolder = file.isDirectory();
        exists = file.exists();
        if (exists) {
            this.FileName = file.getName();
            fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date(file.lastModified());
            this.LastModified = fmt.format(date);
            this.length = file.length();
            if (isFolder) {
                this.type = "Folder";
            } else
                this.type = String.valueOf(this.length / (long) 1024) + "KB";
        }
    }
    public String getFileName() {
        return FileName;
    }
    public void setFileName(String fileName) {
        FileName = fileName;
    }
    public String getLastModified() {
        return LastModified;
    }
    public void setLastModified(String lastModified) {
        LastModified = lastModified;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public long getLength() {
        return length;
    }
    public void setLength(long length) {
        this.length = length;
    }
}
