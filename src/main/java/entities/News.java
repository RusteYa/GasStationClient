package entities;

import java.util.Date;

/**
 * Created by Rustem.
 */
public class News {
    private int id;
    private String header;
    private String body;
    private Date date;
    private String photoPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
