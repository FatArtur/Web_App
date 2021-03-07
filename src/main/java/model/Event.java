package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Events", schema = "Web_app")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data")
    private Date date;
    @ManyToOne (optional=false)
    @JoinColumn (name="file_id")
    private File file;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", file_id=" + file +
                "}";
    }
}
