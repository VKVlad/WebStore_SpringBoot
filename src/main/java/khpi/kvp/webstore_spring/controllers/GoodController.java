package khpi.kvp.webstore_spring.controllers;


import khpi.kvp.webstore_spring.models.Good;
import khpi.kvp.webstore_spring.services.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @GetMapping("/")
    public String goods(@RequestParam(name = "nazva", required = false) String nazva, Model model) {
        model.addAttribute("goods", goodService.goodList(nazva));
        return "goods";
    }

    @GetMapping("/good/{id}")
    public String goodInfo(@PathVariable Long id, Model model) {
        model.addAttribute("good", goodService.getGoodById(id));
        return "good-info";
    }

    @PostMapping("/good/create")
    public String createGood(@RequestParam(name = "nazva") String nazva, @RequestParam(name = "category") String category,
                             @RequestParam(name = "article") String article, @RequestParam(name = "price") BigDecimal price,
                             @RequestParam(name = "priceOpt") BigDecimal priceOpt, @RequestParam("imagePath") String imagePath) {
        Good good = new Good(nazva, category, article, price, priceOpt, imagePath);
        goodService.saveGood(good);
        return "redirect:/";
    }

    @PostMapping("/good/delete/{id}")
    public String deleteGood(@PathVariable Long id) {
        goodService.deleteGood(id);
        return "redirect:/";
    }
}
