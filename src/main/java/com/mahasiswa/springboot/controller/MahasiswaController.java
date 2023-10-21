package com.mahasiswa.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mahasiswa.springboot.model.Mahasiswa;
import com.mahasiswa.springboot.service.MahasiswaService;

import java.util.List;

@Controller
public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "nim","asc", model);
    }
    @GetMapping("/showNewMahasiswaForm")
    public String showNewMahasiswaForm(Model model) {
     Mahasiswa mahasiswa = new Mahasiswa();
     model.addAttribute("mahasiswa", mahasiswa);
     return "new_mahasiswa";
    }
    @PostMapping("/addMahasiswa")
    public String saveMahasiswa(@ModelAttribute("mahasiswa") Mahasiswa mahasiswa) {
     mahasiswaService.addMahasiswa(mahasiswa);
     return "redirect:/";
    }
    @GetMapping("/showFormUpdateMahasiswa/{id}")
    public String showFormUpdateMahasiswa(@PathVariable (value = "id") long id, Model model){
        Mahasiswa mahasiswa = mahasiswaService.getMahasiswaById(id);
        model.addAttribute("mahasiswa",mahasiswa);
        return "update_mahasiswa";
    }
    @GetMapping("/deleteMahasiswa/{id}")
    public String deleteEMahasiswa(@PathVariable (value = "id") long id) {
        this.mahasiswaService.deleteMahasiswaById(id);
        return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 20;

        Page< Mahasiswa > page = mahasiswaService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List< Mahasiswa > listMahasiswa = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listMahasiswa", listMahasiswa);
        return "index";
    }
}
