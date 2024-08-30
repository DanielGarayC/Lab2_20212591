package com.example.lab2.controller;

import com.example.lab2.model.Configuracion;
import com.example.lab2.model.Tarjeta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class BingoController {
    Configuracion config = new Configuracion();
    @GetMapping("/conf")
    public String configuracion(Model model) {

        model.addAttribute("config", config);
        return "configuracion";
    }

    @PostMapping("/conf/start")
    public String empezarJuego( Model model) {
        int dimension = config.getTamaño();
        int numTarjetas = config.getTarjetas();

        Random random = new Random();
        int i = 0;
        int j = 0;
        Tarjeta[][] tarjetas = new Tarjeta[dimension][dimension];
        int indexCartillas = 0;
        while (i < dimension) {
            while(j<dimension){
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setValor(random.nextInt(100));
                tarjeta.setEstado(false);
                tarjetas[i][j] = tarjeta;
            }

        }
        model.addAttribute("dimension", dimension);


        return "redirect:/inicio";
    }

    @GetMapping("/inicio")
    public String juegoIniciado(Model model) {
        model.addAttribute("config", new Configuracion());
        return "juego";
    }

    @PostMapping("/playing")
    public String duranteJuego(@RequestParam("numero") int numero, @RequestParam("tarjetas") Tarjeta[][]tarjetas) {
        int i = 0;
        int j = 0;
        int dimension = config.getTamaño();
        while (i < dimension) {
            while(j<dimension){
                if(tarjetas[i][j].getValor()==numero){
                    tarjetas[i][j].setEstado(true);
                }
            }

        }
        return "juego";
    }
}
