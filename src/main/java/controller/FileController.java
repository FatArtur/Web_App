package controller;

import model.File;
import model.FileStatus;
import repository.FileRepository;
import repository.hibernate.HibernateFileRepository;

import java.util.List;

public class FileController {
    private FileRepository repo = new HibernateFileRepository();

    public File create(String val1, String val2) throws Exception {
        File file = new File();
        file.setFileStatus(FileStatus.ACTIVE);
        file.setName(val1);
        file.setAddress(val2);
        return repo.save(file);
    }

    public void delete(String val) throws Exception {
        repo.deleteById(Long.parseLong(val));
    }

    public File getByID(String val) throws Exception {
        File file = repo.getByID(Long.parseLong(val));
        if (file == null) {
            System.out.println("Введено не корректное значение");
        }
        return file;
    }

    public List<File> getAll() throws Exception {
        List<File> list = repo.getAll();
        list.stream().forEach(s -> System.out.println("ID = " + s.getId() + " File = " + s.getName() +
               " ADDRESS = "+ s.getAddress() + " STATUS: " + s.getFileStatus()));
        return list;
    }

    public File update(String val1, String val2, String val3,FileStatus val4) throws Exception {
        File file = new File();
        file.setId(Long.parseLong(val1));
        file.setName(val2);
        file.setAddress(val3);
        file.setFileStatus(val4);
        return repo.update(file);
    }
}
