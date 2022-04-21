package org.arya.Ui;

import org.arya.model.Autor;
import org.arya.model.Publisher;
import org.arya.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BukuAction {

    @Autowired
    BukuService bukuService;

    @GetMapping("/beranda/{id}")
    public String beranda(ModelMap kirimData, HttpServletRequest req,
                          @RequestParam(value = "rp") Integer nilai,
                          @PathVariable(value = "id") Integer id){
        String paraNama = req.getParameter("nama");
        String paraDua = req.getParameter("dua");
        kirimData.addAttribute("data", "ini data yang dikirim" +paraNama+" "+paraDua+
                " Rp = "+nilai+" id ===>"+id);
        try{
            kirimData.addAttribute("buku", bukuService.getDataBuku(id));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "home";

    }


    @GetMapping("/api/publisher")
    public ResponseEntity<List<Publisher>>  publisherAll(){
        try{
            return ResponseEntity.ok(bukuService.getPublisherAll());
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/api/editpublisher/{id}")
    public ResponseEntity<Publisher>  publisherAll(@PathVariable Integer id){
        return ResponseEntity.ok(bukuService.getPublisher(id));
    }

    @PostMapping("/api/simpanpublisher")
    public ResponseEntity<Map<String,Object>> savePublisher(@RequestBody Publisher publisher){
        Map<String,Object> map = new HashMap<>();
        map.put("kode", 200);
        map.put("pesan", "simpan data berhasil");
        bukuService.simpanPublisher(publisher);
        return ResponseEntity.ok(map);

@GetMapping GetMapping("/api/author")
        public ResponseEntity<List<Autor>> authorAll(){
            try{
                return ResponseEntity.ok(bukuService.getAuthorAll());
            }catch (SQLException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
    }
}



