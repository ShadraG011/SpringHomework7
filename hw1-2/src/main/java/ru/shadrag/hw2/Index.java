package ru.shadrag.hw2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class Index {

    private final Random rd = new Random();

    private String load;

    @GetMapping("/index")
    public String index(){
        return "Приветствую, для того, чтобы подбросить монетку введите в поисковую строку слово <em>coin</em> вместо <em>index</em>";
    }

    @GetMapping("/coin")
    public String flipCoin(){
        boolean tails = rd.nextBoolean();
        return (tails ? "<strong>Вам выпала решка!</strong>" : "<strong>Вам выпал орел!</strong>") + "<hr><em>Что подбросить монетку ещё раз перезагрузите страницу.</em>";
    }
}
