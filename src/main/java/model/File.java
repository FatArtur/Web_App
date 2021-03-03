package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Files", schema = "Web_app")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;
    @OneToMany (mappedBy="file_id", fetch=FetchType.LAZY)
    private List<Event> events;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return address;
    }

    public void setName(String name) {
        this.address = name;
    }

    public FileStatus getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", fileStatus=" + fileStatus +
                '}';
    }
}
