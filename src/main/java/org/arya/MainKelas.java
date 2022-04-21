package org.arya;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;



@SpringBootApplication
public class MainKelas {
    public static void main(String[] args) {
        SpringApplication.run(MainKelas.class, args);
        //===========================
           /* AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.register(config.class);
            ctx.refresh();
            BukuService bs = ctx.getBean(BukuService.class);
        Buku bukuHasil = bs.getDataBukuAll();
        System.out.println("===>"+ bukuHasil.getJudulbuku());


    }*/

    }

}