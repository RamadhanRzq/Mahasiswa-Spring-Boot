package com.mahasiswa.springboot.service;

import java.util.List;

import com.mahasiswa.springboot.model.Mahasiswa;
import org.springframework.data.domain.Page;

public interface MahasiswaService {
    List<Mahasiswa> getAllMahasiswa();
    void addMahasiswa(Mahasiswa mahasiswa);
    Mahasiswa getMahasiswaById(long id);
    void deleteMahasiswaById(long id);
    Page<Mahasiswa> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
